package com.alex.unijourneybackend.modules.password.application.command.reset;

public record ResetPasswordCommand(
    String token,
    String newPassword
) {}
