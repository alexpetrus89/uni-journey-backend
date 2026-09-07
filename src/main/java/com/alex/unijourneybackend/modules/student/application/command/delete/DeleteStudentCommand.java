package com.alex.unijourneybackend.modules.student.application.command.delete;

import com.alex.unijourneybackend.modules.user.application.bus.Command;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;
import com.alex.unijourneybackend.modules.user.web.dto.response.DeletionResponse;

public record DeleteStudentCommand(UserId id) implements Command<DeletionResponse> {}
