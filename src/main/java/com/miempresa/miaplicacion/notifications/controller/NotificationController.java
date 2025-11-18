package com.miempresa.miaplicacion.notifications.controller;

import com.miempresa.miaplicacion.notifications.model.Notification;
import com.miempresa.miaplicacion.notifications.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

  private final NotificationService notificationService;

  public NotificationController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @GetMapping
  public ResponseEntity<List<Notification>> getNotifications() {
    List<Notification> notificationsList = notificationService.getAllNotifications();
    return ResponseEntity.ok(notificationsList);
  }

  @PostMapping
  public ResponseEntity<Notification> postNotification(@RequestBody Notification notification) {
    Notification createdNotification= notificationService.createNotification(notification);
    return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
  }

  @PutMapping ("/{id}")
  public ResponseEntity<Notification> putNotification(@PathVariable Long id, @RequestBody Notification notification) {
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
  public ResponseEntity<Notification> getNotificationById(@PathVariable Long id){
      return notificationService.getNotificationById(id).
              map(ResponseEntity::ok)
              .orElseGet(()->ResponseEntity.notFound().build());
  }
}
