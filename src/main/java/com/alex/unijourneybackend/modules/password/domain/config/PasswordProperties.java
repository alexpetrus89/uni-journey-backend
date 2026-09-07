package com.alex.unijourneybackend.modules.password.domain.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.alex.unijourneybackend.modules.password.domain.policy.PasswordRule;
import com.alex.unijourneybackend.modules.password.domain.policy.PasswordRuleEngine;

@Component
@ConfigurationProperties(prefix = "password")
public class PasswordProperties {

    private int minLength;
    private Map<String,String> pattern;
    private String specialDescription;
    private Blacklist blacklist = new Blacklist();

    public int getMinLength() { return minLength; }
    public Map<String,String> getPattern() { return pattern; }
    public Blacklist getBlacklist() { return blacklist; }
    public String getSpecialDescription() { return specialDescription; }

    public void setMinLength(int minLength) { this.minLength = minLength; }
    public void setPattern(Map<String,String> pattern) { this.pattern = pattern; }
    public void setBlacklist(Blacklist blacklist) { this.blacklist = blacklist; }
    public void setSpecialDescription(String specialDescription) { this.specialDescription = specialDescription; }

    public static class Blacklist {

        private boolean enabled;
        private String file;
        private long expectedSize;
        private double falsePositiveRate;

        public boolean isEnabled() { return enabled; }
        public String getFile() { return file; }
        public long getExpectedSize() { return expectedSize; }
        public double getFalsePositiveRate() { return falsePositiveRate; }

        public void setEnabled(String enabled) {
            if (enabled != null)
                this.enabled = Boolean.parseBoolean(enabled);
        }
        public void setFile(String file) { this.file = file; }
        public void setExpectedSize(long expectedSize) { this.expectedSize = expectedSize; }
        public void setFalsePositiveRate(double falsePositiveRate) { this.falsePositiveRate = falsePositiveRate; }

    }


    @Bean
    @SuppressWarnings("unused")
    PasswordRuleEngine passwordRuleEngine(List<PasswordRule> rules) {
        return new PasswordRuleEngine(rules);
    }


}
