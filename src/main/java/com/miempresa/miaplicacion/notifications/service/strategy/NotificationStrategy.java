package com.miempresa.miaplicacion.notifications.service.strategy;

import com.miempresa.miaplicacion.notifications.model.Notification;

public interface NotificationStrategy {
    String sendNotification(Notification notification);
}
