package com.alex.unijourneybackend.auth.web.util;

import jakarta.servlet.http.HttpServletRequest;

public final class HttpRequestUtils {

    private HttpRequestUtils() {}

    public static String getClientIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        return (forwarded != null && !forwarded.isBlank())
            ? forwarded.split(",")[0].trim()
            : request.getRemoteAddr();
    }
}
