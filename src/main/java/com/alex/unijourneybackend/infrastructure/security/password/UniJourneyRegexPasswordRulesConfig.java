package com.alex.unijourneybackend.infrastructure.security.password;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alex.unijourneybackend.modules.password.domain.config.PasswordProperties;
import com.alex.unijourneybackend.modules.password.domain.policy.PasswordRule;
import com.alex.unijourneybackend.modules.password.domain.rule.RegexPasswordRule;

@Configuration
public class UniJourneyRegexPasswordRulesConfig {


    @Bean
    @SuppressWarnings("unused")
    List<PasswordRule> passwordRules(PasswordProperties props) {
        return List.of(

            new RegexPasswordRule(
                props.getPattern().get("uppercase"),
                "Password must contain at least one uppercase letter",
                10
            ),

            new RegexPasswordRule(
                props.getPattern().get("lowercase"),
                "Password must contain at least one lowercase letter",
                11
            ),

            new RegexPasswordRule(
                props.getPattern().get("digit"),
                "Password must contain at least one digit",
                12
            ),

            new RegexPasswordRule(
                props.getPattern().get("special"),
                "Password must contain at least one special character "
                    + props.getSpecialDescription(),
                13
            )
        );
    }


}

