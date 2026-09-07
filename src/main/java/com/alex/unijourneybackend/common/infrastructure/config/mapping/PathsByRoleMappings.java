package com.alex.unijourneybackend.common.infrastructure.config.mapping;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;


@ConfigurationProperties
@Validated
public record PathsByRoleMappings(
    @NotEmpty List<String> publicUrls,
    @NotEmpty List<String> adminUrls,
    @NotEmpty List<String> studentUrls,
    @NotEmpty List<String> professorUrls
) {
    public String[] publicUrlsArray() { return publicUrls.toArray(String[]::new); }
    public String[] adminUrlsArray() { return adminUrls.toArray(String[]::new); }
    public String[] studentUrlsArray() { return studentUrls.toArray(String[]::new); }
    public String[] professorUrlsArray() { return professorUrls.toArray(String[]::new); }

    public List<String> getPublicUrls() { return publicUrls; }
    public List<String> getAdminUrls() { return adminUrls; }
    public List<String> getStudentUrls() { return studentUrls; }
    public List<String> getProfessorUrls() { return professorUrls; }


}

