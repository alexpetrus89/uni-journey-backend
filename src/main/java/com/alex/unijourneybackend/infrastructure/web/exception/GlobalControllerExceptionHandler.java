package com.alex.unijourneybackend.infrastructure.web.exception;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.MethodNotAllowed;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.alex.unijourneybackend.common.domain.exception.BusinessException;
import com.alex.unijourneybackend.common.domain.exception.DataAccessServiceException;
import com.alex.unijourneybackend.common.domain.exception.DuplicateFiscalCodeException;
import com.alex.unijourneybackend.common.domain.exception.DuplicateUsernameException;
import com.alex.unijourneybackend.common.domain.exception.InvalidDomainStateException;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

/**
 * Global Exception Handling
 *
 * Un controller advice consente di utilizzare esattamente le stesse tecniche
 * di gestione delle eccezioni, ma di applicarle all'intera applicazione, non
 * solo a un singolo controller.
 * È possibile considerarle come un intercettore guidato dall'annotazione.
 *
 * Qualsiasi classe annotata con @ControllerAdvice diventa un controller-advice
 * e sono supportati tre tipi di metodo:
 *
 * 1) Metodi di gestione delle eccezioni annotati con @ExceptionHandler.
 * 2) Metodi di miglioramento del modello (per aggiungere dati aggiuntivi
 *    al modello) annotati con @ModelAttribute.
 *
 * Un controller advice consente di utilizzare esattamente le stesse tecniche
 * di gestione delle eccezioni, ma di applicarle all'intera applicazione, non
 * solo a un singolo controller.
 * È possibile considerarle come un intercettore guidato dall'annotazione.
 *
 * Qualsiasi classe annotata con @ControllerAdvice diventa un controller-advice
 * e sono supportati tre tipi di metodo:
 *
 * 1) Metodi di gestione delle eccezioni annotati con @ExceptionHandler.
 *
 * 2) Metodi di miglioramento del modello (per aggiungere dati aggiuntivi
 *    al modello) annotati con @ModelAttribute.
 *
 * Nota che questi attributi non sono disponibili per le viste di gestione
 * delle eccezioni.
 *
 * 3) Metodi di inizializzazione del binder (utilizzati per configurare la
 *    gestione dei form) annotati con @InitBinder.
 *
 * Ci occuperemo solo della gestione delle eccezioni: per maggiori informazioni
 * sui metodi @ControllerAdvice, consultate il manuale online.
 *
 *
 * Tutti i gestori di eccezioni che hai visto sopra possono essere definiti su
 * una classe controller-advice, ma ora si applicano alle eccezioni generate da
 * qualsiasi controller. Ecco un semplice esempio:
 */


@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    public static final String VALIDATION_FAILED = "Validation failed";

    // =========================
    // Error response record
    // =========================
    public record ErrorResponse(
        int status,
        String error,
        String message,
        String path,
        Instant timestamp,
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        Map<String, String> errors
    ) {}


    // =========================
    // 400 — Bad Request
    // =========================
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
        IllegalArgumentException ex,
        HttpServletRequest req
    ) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), req, Map.of());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
        MethodArgumentNotValidException ex,
        HttpServletRequest req
    ) {
        LinkedHashMap<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(fe ->
            errors.putIfAbsent(normalizeField(fe.getField()),
                fe.getDefaultMessage() != null ? fe.getDefaultMessage() : "Invalid value")
        );

        ex.getBindingResult().getGlobalErrors().forEach(oe ->
            errors.putIfAbsent("request",
                oe.getDefaultMessage() != null ? oe.getDefaultMessage() : VALIDATION_FAILED)
        );

        String message = errors.values().stream().findFirst().orElse(VALIDATION_FAILED);

        return build(HttpStatus.BAD_REQUEST, message, req, errors);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
        ConstraintViolationException ex,
        HttpServletRequest req
    ) {
        LinkedHashMap<String, String> errors = new LinkedHashMap<>();

        ex.getConstraintViolations().forEach(cv -> {
            String path  = cv.getPropertyPath().toString();
            String field = path.contains(".") ? path.substring(path.lastIndexOf('.') + 1) : path;
            errors.putIfAbsent(field, cv.getMessage());
        });

        String message = errors.values().stream().findFirst().orElse(VALIDATION_FAILED);

        return build(HttpStatus.BAD_REQUEST, message, req, errors);
    }


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(
        BusinessException ex,
        HttpServletRequest req
    ) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), req, Map.of());
    }


    // =========================
    // 401 — Unauthorized
    // =========================
    @ExceptionHandler(Unauthorized.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(
        Unauthorized ex,
        HttpServletRequest req
    ) {
        return build(HttpStatus.UNAUTHORIZED, ex.getMessage(), req, Map.of());
    }


    // =========================
    // 403 — Forbidden
    // =========================
    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ErrorResponse> handleLocked(
        LockedException ex,
        HttpServletRequest req
    ) {
        return build(HttpStatus.FORBIDDEN, "Account locked", req, Map.of());
    }


    // =========================
    // 404 — Not Found
    // =========================
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(
        UsernameNotFoundException ex,
        HttpServletRequest req
    ) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), req, Map.of());
    }


    // =========================
    // 405 — Method Not Allowed
    // =========================
    @ExceptionHandler(MethodNotAllowed.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
        MethodNotAllowed ex,
        HttpServletRequest req
    ) {
        return build(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage(), req, Map.of());
    }


    // =========================
    // 409 — Conflict
    // =========================
    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateUsername(
        DuplicateUsernameException ex,
        HttpServletRequest req
    ) {
        return build(HttpStatus.CONFLICT, ex.getMessage(), req, Map.of());
    }

    @ExceptionHandler(DuplicateFiscalCodeException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateFiscalCode(
        DuplicateFiscalCodeException ex,
        HttpServletRequest req
    ) {
        return build(HttpStatus.CONFLICT, ex.getMessage(), req, Map.of());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalState(
        IllegalStateException ex,
        HttpServletRequest req
    ) {
        return build(HttpStatus.CONFLICT, ex.getMessage(), req, Map.of());
    }


    // =========================
    // 422 — Unprocessable Entity
    // =========================
    @ExceptionHandler(InvalidDomainStateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDomain(
        InvalidDomainStateException ex,
        HttpServletRequest req
    ) {
        return build(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), req, Map.of());
    }


    // =========================
    // 503 — Service Unavailable
    // =========================
    @ExceptionHandler(DataAccessServiceException.class)
    public ResponseEntity<ErrorResponse> handleDataAccess(
        DataAccessServiceException ex,
        HttpServletRequest req
    ) {
        // Non esporre dettagli interni del DB al client
        return build(HttpStatus.SERVICE_UNAVAILABLE, "Database error, please try again later", req, Map.of());
    }


    // =========================
    // 500 — Fallback generico
    // =========================
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
        Exception ex,
        HttpServletRequest req
    ) {
        // Mai esporre ex.getMessage() in produzione
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", req, Map.of());
    }


    // =========================
    // Builder
    // =========================
    private ResponseEntity<ErrorResponse> build(
        HttpStatus status,
        String message,
        HttpServletRequest req,
        Map<String, String> errors
    ) {
        return ResponseEntity
            .status(status.value())
            .body(new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message,
                req.getRequestURI(),
                Instant.now(),
                errors
            ));
    }


    private String normalizeField(String fieldName) {
        if (fieldName == null || fieldName.isBlank()) return "request";
        return fieldName.contains(".") ? fieldName.substring(0, fieldName.indexOf('.')) : fieldName;
    }


}





