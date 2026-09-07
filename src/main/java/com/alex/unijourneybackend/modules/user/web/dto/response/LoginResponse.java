package com.alex.unijourneybackend.modules.user.web.dto.response;

public record LoginResponse (
    String username,
    String role,
    String message
) {}