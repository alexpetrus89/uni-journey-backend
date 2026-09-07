# start-backend.ps1
# Avvia Postgres, Redis, Kafka (+ Kafka UI), Nginx e lo stack di observability
# (Loki, Promtail, Prometheus, cAdvisor, Grafana) via Docker (se non già attivi),
# verifica che siano davvero pronti, poi avvia Spring Boot con Maven

$scriptDir = $PSScriptRoot
$maxRetries = 10
$retryDelaySeconds = 2

# 🔹 Carica le variabili dal .env nella sessione corrente
#    (necessario sia per i controlli PowerShell qui sotto, sia perché Maven/Spring
#     le legga correttamente all'avvio dell'app)
$envFile = "$scriptDir\.env"
if (Test-Path $envFile) {
    Get-Content $envFile | ForEach-Object {
        if ($_ -match '^\s*([^#][^=]*)=(.*)$') {
            $name = $matches[1].Trim()
            $value = $matches[2].Trim()
            [System.Environment]::SetEnvironmentVariable($name, $value, "Process")
        }
    }
    Write-Host "✅ Loaded environment variables from .env"
} else {
    Write-Host "⚠️  .env file not found at $envFile"
}

function Ensure-ContainerRunning {
    param(
        [string]$ContainerName,
        [string]$ComposeFile,
        [string]$ServiceName
    )

    $running = docker ps --filter "name=$ContainerName" --filter "status=running" --format "{{.Names}}"

    if ($running -eq $ContainerName) {
        Write-Host "✅ $ContainerName is already up and running."
    } else {
        if (-not (Test-Path $ComposeFile)) {
            Write-Host "❌ Compose file not found: $ComposeFile"
            exit 1
        }

        Write-Host "🟢 Starting $ContainerName..."
        docker compose --env-file "$scriptDir\.env" -f $ComposeFile up -d $ServiceName
        if ($LASTEXITCODE -ne 0) {
            Write-Host "❌ docker compose failed for $ContainerName (exit code $LASTEXITCODE)."
            exit 1
        }
        Write-Host "✅ $ContainerName started."
    }
}

function Wait-ForHealthy {
    param(
        [string]$Label,
        [scriptblock]$Check
    )

    Write-Host "⏳ Waiting for $Label to be ready..."

    for ($i = 1; $i -le $maxRetries; $i++) {
        if (& $Check) {
            Write-Host "✅ $Label is ready."
            return $true
        }
        Start-Sleep -Seconds $retryDelaySeconds
    }

    Write-Host "❌ $Label did not become ready after $($maxRetries * $retryDelaySeconds)s."
    return $false
}

Write-Host "🚀 Checking infrastructure containers..."

Ensure-ContainerRunning -ContainerName "unijourney-postgres" -ComposeFile "$scriptDir\docker\postgres\docker-compose.yml" -ServiceName "postgres"
Ensure-ContainerRunning -ContainerName "unijourney-redis" -ComposeFile "$scriptDir\docker\redis\docker-compose.yml" -ServiceName "redis"
Ensure-ContainerRunning -ContainerName "unijourney-kafka" -ComposeFile "$scriptDir\docker\kafka\docker-compose.yml" -ServiceName "kafka"
Ensure-ContainerRunning -ContainerName "kafka-kafka-ui-1" -ComposeFile "$scriptDir\docker\kafka\docker-compose.yml" -ServiceName "kafka-ui"
Ensure-ContainerRunning -ContainerName "unijourney-nginx" -ComposeFile "$scriptDir\docker\nginx\docker-compose.yml" -ServiceName "nginx"

Write-Host ""
Write-Host "🚀 Checking observability containers..."

$observabilityCompose = "$scriptDir\docker\observability\docker-compose.yml"

Ensure-ContainerRunning -ContainerName "unijourney-loki" -ComposeFile $observabilityCompose -ServiceName "loki"
Ensure-ContainerRunning -ContainerName "unijourney-promtail" -ComposeFile $observabilityCompose -ServiceName "promtail"
Ensure-ContainerRunning -ContainerName "unijourney-prometheus" -ComposeFile $observabilityCompose -ServiceName "prometheus"
Ensure-ContainerRunning -ContainerName "unijourney-cadvisor" -ComposeFile $observabilityCompose -ServiceName "cadvisor"
Ensure-ContainerRunning -ContainerName "unijourney-grafana" -ComposeFile $observabilityCompose -ServiceName "grafana"

Write-Host ""
Write-Host "🔎 Running health checks..."

$postgresOk = Wait-ForHealthy -Label "Postgres" -Check {
    $status = docker exec unijourney-postgres pg_isready -U $env:DB_USERNAME 2>$null
    return $status -match "accepting connections"
}

$redisOk = Wait-ForHealthy -Label "Redis" -Check {
    $pong = docker exec unijourney-redis redis-cli ping 2>$null
    return $pong -eq "PONG"
}

$kafkaOk = Wait-ForHealthy -Label "Kafka" -Check {
    $result = Test-NetConnection -ComputerName localhost -Port 29092 -WarningAction SilentlyContinue
    return $result.TcpTestSucceeded
}

$nginxOk = Wait-ForHealthy -Label "Nginx" -Check {
    try {
        $response = Invoke-WebRequest -Uri "http://localhost" -UseBasicParsing -TimeoutSec 3
        return $response.StatusCode -lt 500
    } catch {
        return $_.Exception.Response -ne $null
    }
}

$lokiOk = Wait-ForHealthy -Label "Loki" -Check {
    try {
        $response = Invoke-WebRequest -Uri "http://localhost:3100/ready" -UseBasicParsing -TimeoutSec 3
        return $response.StatusCode -eq 200
    } catch {
        return $false
    }
}

$prometheusOk = Wait-ForHealthy -Label "Prometheus" -Check {
    try {
        $response = Invoke-WebRequest -Uri "http://localhost:9091/-/ready" -UseBasicParsing -TimeoutSec 3
        return $response.StatusCode -eq 200
    } catch {
        return $false
    }
}

$cadvisorOk = Wait-ForHealthy -Label "cAdvisor" -Check {
    try {
        $response = Invoke-WebRequest -Uri "http://localhost:8085/healthz" -UseBasicParsing -TimeoutSec 3
        return $response.StatusCode -eq 200
    } catch {
        return $false
    }
}

$grafanaOk = Wait-ForHealthy -Label "Grafana" -Check {
    try {
        $response = Invoke-WebRequest -Uri "http://localhost:3000/api/health" -UseBasicParsing -TimeoutSec 3
        return $response.StatusCode -eq 200
    } catch {
        return $false
    }
}

# Promtail non espone una porta pubblicata di default nel compose: qui ci limitiamo
# a verificare che il container sia effettivamente in stato "running" via Docker.
$promtailOk = Wait-ForHealthy -Label "Promtail" -Check {
    $running = docker ps --filter "name=unijourney-promtail" --filter "status=running" --format "{{.Names}}"
    return $running -eq "unijourney-promtail"
}

if (-not ($postgresOk -and $redisOk -and $kafkaOk -and $nginxOk -and $lokiOk -and $prometheusOk -and $cadvisorOk -and $grafanaOk -and $promtailOk)) {
    Write-Host ""
    Write-Host "🛑 One or more services are not healthy. Aborting before starting Spring Boot."
    exit 1
}

Write-Host ""
Write-Host "🟢 All infrastructure is healthy. Starting Spring Boot..."
mvn clean spring-boot:run -X -e -debug