package com.alex.unijourneybackend.modules.user.web.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.unijourneybackend.modules.user.application.bus.Command;
import com.alex.unijourneybackend.modules.user.application.bus.CommandBus;
import com.alex.unijourneybackend.modules.user.web.dto.request.RegistrationRequest;
import com.alex.unijourneybackend.modules.user.web.dto.response.RegistrationResponse;
import com.alex.unijourneybackend.modules.user.web.mapper.RegistrationMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private final CommandBus commandBus;
    private final RegistrationMapper mapper;

    public RegistrationController(CommandBus commandBus, RegistrationMapper mapper) {
        this.commandBus = commandBus;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<RegistrationResponse> register(
        @Valid @RequestBody RegistrationRequest request
    ) {
        Command<RegistrationResponse> command = switch (request.role()) {
            case ADMIN     -> mapper.toAdminCommand(request);
            case STUDENT   -> mapper.toStudentCommand(request);
            case PROFESSOR -> mapper.toProfessorCommand(request);
        };

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commandBus.dispatch(command));
    }


}