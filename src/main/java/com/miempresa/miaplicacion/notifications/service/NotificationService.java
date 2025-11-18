package com.miempresa.miaplicacion.notifications.service;

import com.miempresa.miaplicacion.notifications.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    public List<Notification> getAllNotifications();
    public Optional<Notification> getNotificationById(Long id);
    public Notification createNotification(Notification notification);
    public Optional<Notification> updateNotification (Long id, Notification notification);
    public void deleteNotificationById(Long id);
}
