package com.alex.unijourneybackend.modules.user.web.dto.response;

public record RegistrationResponse(
    String id,
    String username,
    String role,
    String message,
    String referenceCode
) {}
