package com.alex.unijourneybackend.modules.professor.application.command.delete;

import com.alex.unijourneybackend.modules.user.application.bus.Command;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;
import com.alex.unijourneybackend.modules.user.web.dto.response.DeletionResponse;

public record DeleteProfessorCommand(UserId id) implements Command<DeletionResponse> {}
