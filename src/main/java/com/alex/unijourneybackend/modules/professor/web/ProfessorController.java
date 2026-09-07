package com.alex.unijourneybackend.modules.professor.web;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.professor.application.command.delete.DeleteProfessorCommand;
import com.alex.unijourneybackend.modules.professor.application.command.delete.DeleteProfessorHandler;
import com.alex.unijourneybackend.modules.professor.application.command.register.RegisterProfessorCommand;
import com.alex.unijourneybackend.modules.professor.application.command.register.RegisterProfessorHandler;
import com.alex.unijourneybackend.modules.professor.application.query.getadmins.GetProfessorsHandler;
import com.alex.unijourneybackend.modules.professor.application.query.getadmins.GetProfessorsQuery;
import com.alex.unijourneybackend.modules.professor.application.query.getbyid.GetProfessorByIdHandler;
import com.alex.unijourneybackend.modules.professor.application.query.getbyid.GetProfessorByIdQuery;
import com.alex.unijourneybackend.modules.professor.web.dto.ProfessorResponse;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;
import com.alex.unijourneybackend.modules.user.web.dto.response.RegistrationResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/professors")
public class ProfessorController {

    private final RegisterProfessorHandler registerHandler;
    private final DeleteProfessorHandler deleteHandler;
    private final GetProfessorsHandler getProfessorsHandler;
    private final GetProfessorByIdHandler getByIdHandler;

    public ProfessorController(
        RegisterProfessorHandler registerHandler,
        DeleteProfessorHandler deleteHandler,
        GetProfessorsHandler getProfessorsHandler,
        GetProfessorByIdHandler getByIdHandler
    ) {
        this.registerHandler = registerHandler;
        this.deleteHandler = deleteHandler;
        this.getProfessorsHandler = getProfessorsHandler;
        this.getByIdHandler = getByIdHandler;
    }

    @GetMapping
    public ResponseEntity<PageResult<ProfessorResponse>> getAll(
        @RequestParam int page,
        @RequestParam int size
    ) {
        PageQuery query = new PageQuery(page, size);
        return ResponseEntity.ok(getProfessorsHandler.handle(new GetProfessorsQuery(query)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponse> getById(@PathVariable UserId id) {
        return ResponseEntity.ok(getByIdHandler.handle(new GetProfessorByIdQuery(id)));
    }

    @PostMapping
    public ResponseEntity<RegistrationResponse> register(
        @Valid @RequestBody RegisterProfessorCommand command
    ) {
        RegistrationResponse response = registerHandler.handle(command);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.id())
            .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UserId id) {
        deleteHandler.handle(new DeleteProfessorCommand(id));
        return ResponseEntity.noContent().build();
    }


}
