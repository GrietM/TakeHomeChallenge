package com.miempresa.miaplicacion.notifications.controller;

import com.miempresa.miaplicacion.notifications.dto.NotificationCreateRequestDTO;
import com.miempresa.miaplicacion.notifications.dto.NotificationResponseDTO;
import com.miempresa.miaplicacion.notifications.model.Notification;
import com.miempresa.miaplicacion.notifications.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

  private final NotificationService notificationService;

  public NotificationController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @GetMapping
  public ResponseEntity<List<NotificationResponseDTO>> getNotifications() {
    List<NotificationResponseDTO> notificationsList = notificationService.getAllNotifications();
    return ResponseEntity.ok(notificationsList);
  }

  @PostMapping
  public ResponseEntity<NotificationResponseDTO> postNotification(@RequestBody NotificationCreateRequestDTO notification) {
    NotificationResponseDTO createdNotification= notificationService.createNotification(notification);
    return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<NotificationResponseDTO> putNotification(@PathVariable Long id, @RequestBody NotificationCreateRequestDTO notification) {
      return notificationService.updateNotification(id,notification)
              .map(ResponseEntity::ok)
              .orElseGet(()->ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
    notificationService.deleteNotificationById(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<NotificationResponseDTO> getNotificationById(@PathVariable Long id){
      return notificationService.getNotificationById(id).
              map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
  }

  @GetMapping("/by-user/{userId}")
  public ResponseEntity<List<NotificationResponseDTO>> getNotificationsByUserId(@PathVariable Long userId) {
      List<NotificationResponseDTO> notificationsList = notificationService.getNotificationsByUser(userId);
      return ResponseEntity.ok(notificationsList);
  }


}
