package com.alex.unijourneybackend.modules.password.application.command.send;

public record SendPasswordResetCommand(
    String email
) {}
