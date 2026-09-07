package com.alex.unijourneybackend.modules.admin.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alex.unijourneybackend.common.domain.pagination.PageQuery;
import com.alex.unijourneybackend.common.domain.pagination.PageResult;
import com.alex.unijourneybackend.modules.admin.application.command.delete.DeleteAdminCommand;
import com.alex.unijourneybackend.modules.admin.application.command.delete.DeleteAdminHandler;
import com.alex.unijourneybackend.modules.admin.application.query.getadmins.GetAdminsHandler;
import com.alex.unijourneybackend.modules.admin.application.query.getadmins.GetAdminsQuery;
import com.alex.unijourneybackend.modules.admin.application.query.getbyid.GetAdminByIdHandler;
import com.alex.unijourneybackend.modules.admin.application.query.getbyid.GetAdminByIdQuery;
import com.alex.unijourneybackend.modules.admin.web.dto.AdminResponse;
import com.alex.unijourneybackend.modules.user.domain.valueobject.UserId;


@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {

    private final GetAdminsHandler getAdminsHandler;
    private final GetAdminByIdHandler getByIdHandler;
    private final DeleteAdminHandler deleteHandler;

    public AdminController(
        GetAdminsHandler getAdminsHandler,
        GetAdminByIdHandler getByIdHandler,
        DeleteAdminHandler deleteHandler
    ) {
        this.getAdminsHandler = getAdminsHandler;
        this.getByIdHandler = getByIdHandler;
        this.deleteHandler = deleteHandler;
    }

    @GetMapping
    public ResponseEntity<PageResult<AdminResponse>> getAll(
        @RequestParam int page,
        @RequestParam int size
    ) {
        PageQuery query = new PageQuery(page, size);
        return ResponseEntity.ok(getAdminsHandler.handle(new GetAdminsQuery(query)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponse> getById(@PathVariable UserId id) {
        return ResponseEntity.ok(getByIdHandler.handle(new GetAdminByIdQuery(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UserId id) {
        deleteHandler.handle(new DeleteAdminCommand(id));
        return ResponseEntity.noContent().build();
    }


}
