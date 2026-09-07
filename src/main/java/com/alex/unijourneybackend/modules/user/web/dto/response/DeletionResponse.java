package com.alex.unijourneybackend.modules.user.web.dto.response;

public record DeletionResponse(
    String id,
    String username,
    String role,
    String message,
    String referenceCode
) {}
