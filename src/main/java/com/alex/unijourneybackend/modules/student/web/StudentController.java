package com.alex.unijourneybackend.modules.student.web;

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
import com.alex.unijourneybackend.modules.student.application.command.delete.DeleteStudentCommand;
import com.alex.unijourneybackend.modules.student.application.command.delete.DeleteStudentHandler;
import com.alex.unijourneybackend.modules.student.application.command.register.RegisterStudentCommand;
import com.alex.unijourneybackend.modules.student.application.command.register.RegisterStudentHandler;
import com.alex.unijourneybackend.modules.student.application.query.getbyid.GetStudentByIdHandler;
import com.alex.unijourneybackend.modules.student.application.query.getbyid.GetStudentByIdQuery;
import com.alex.unijourneybackend.modules.student.application.query.getstudents.GetStudentsHandler;
import com.alex.unijourneybackend.modules.student.application.query.getstudents.GetStudentsQuery;
import com.alex.unijourneybackend.modules.student.web.dto.response.StudentResponse;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;
import com.alex.unijourneybackend.modules.user.web.dto.response.RegistrationResponse;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;


@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    private final RegisterStudentHandler registerHandler;
    private final DeleteStudentHandler deleteHandler;
    private final GetStudentsHandler getStudentsHandler;
    private final GetStudentByIdHandler getByIdHandler;

    public StudentController(
        RegisterStudentHandler registerHandler,
        DeleteStudentHandler deleteHandler,
        GetStudentsHandler getStudentsHandler,
        GetStudentByIdHandler getByIdHandler
    ) {
        this.registerHandler = registerHandler;
        this.deleteHandler = deleteHandler;
        this.getStudentsHandler = getStudentsHandler;
        this.getByIdHandler = getByIdHandler;
    }

    @Operation(summary = "Retrieve all students")
    @GetMapping
    public ResponseEntity<PageResult<StudentResponse>> getAll(
        @RequestParam int page,
        @RequestParam int size
    ) {
        PageQuery query = new PageQuery(page, size);
        return ResponseEntity.ok(getStudentsHandler.handle(new GetStudentsQuery(query)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getById(@PathVariable UserId id) {
        return ResponseEntity.ok(getByIdHandler.handle(new GetStudentByIdQuery(id)));
    }

    @PostMapping
    public ResponseEntity<RegistrationResponse> register(
        @Valid @RequestBody RegisterStudentCommand command
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
        deleteHandler.handle(new DeleteStudentCommand(id));
        return ResponseEntity.noContent().build();
    }


}
