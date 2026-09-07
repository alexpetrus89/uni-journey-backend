package com.alex.unijourneybackend.modules.notification.web;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.unijourneybackend.modules.notification.application.service.NotificationService;
import com.alex.unijourneybackend.modules.notification.web.dto.NotificationDto;


@RestController
@RequestMapping(path = "api/v1/notification")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }


    /**
     * This method is used to get the notifications of a student
     * @param student
     * @return a list of OutcomeNotificationDto
     */
    @GetMapping("/read/all")
    public List<NotificationDto> getAllUserNotifications(Principal principal) {
        return service
            .getActiveNotifications(principal.getName())
            .stream()
            .map(NotificationDto::toDto)
            .toList();
    }


    /**
     * This method is used to mark a notification as read
     * @param id
     * @return a ResponseEntity
     */
    @PostMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        service.markAsRead(id);
        return ResponseEntity.ok().build();
    }


}
