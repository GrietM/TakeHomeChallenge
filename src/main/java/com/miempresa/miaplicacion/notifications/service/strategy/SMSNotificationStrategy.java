package com.miempresa.miaplicacion.notifications.service.strategy;

import com.miempresa.miaplicacion.notifications.model.Notification;
import org.springframework.stereotype.Component;

@Component
public class SMSNotificationStrategy implements NotificationStrategy {

    @Override
    public String sendNotification(Notification notification) {
        System.out.println("Sending notification through " + notification.getCanal());
        return "Sending notification through " + notification.getCanal();
    }
}
