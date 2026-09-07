package com.alex.unijourneybackend.modules.password.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alex.unijourneybackend.modules.password.application.command.reset.ResetPasswordCommand;
import com.alex.unijourneybackend.modules.password.application.command.reset.ResetPasswordHandler;
import com.alex.unijourneybackend.modules.password.application.command.send.SendPasswordResetCommand;
import com.alex.unijourneybackend.modules.password.application.command.send.SendPasswordResetHandler;


@RestController
@RequestMapping("/api/v1/password")
public class PasswordResetController {

    private final ResetPasswordHandler resetHandler;
    private final SendPasswordResetHandler sendHandler;

    public PasswordResetController(
        ResetPasswordHandler resetHandler,
        SendPasswordResetHandler sendHandler
    ) {
        this.resetHandler = resetHandler;
        this.sendHandler = sendHandler;
    }

    @PostMapping("/forgot")
    public void requestReset(@RequestParam String email) {
        sendHandler.handle(new SendPasswordResetCommand(email));
    }

    @PostMapping("/reset")
    public void reset(
        @RequestParam String token,
        @RequestParam String password
    ) {
        resetHandler.handle(new ResetPasswordCommand(token, password));
    }


}

