package com.miempresa.miaplicacion.notifications.service;

import com.miempresa.miaplicacion.notifications.dto.NotificationCreateRequestDTO;
import com.miempresa.miaplicacion.notifications.dto.NotificationResponseDTO;
import com.miempresa.miaplicacion.notifications.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
     List<NotificationResponseDTO> getAllNotifications();
     Optional<NotificationResponseDTO> getNotificationById(Long id);
     NotificationResponseDTO createNotification(NotificationCreateRequestDTO notification);
     Optional<NotificationResponseDTO> updateNotification (Long id, NotificationCreateRequestDTO notification);
     void deleteNotificationById(Long id);
     List<NotificationResponseDTO> getNotificationsByUser(Long userId);
}
