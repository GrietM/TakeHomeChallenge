package com.miempresa.miaplicacion.notifications.service.strategy;

import com.miempresa.miaplicacion.notifications.model.Notification;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class EmailNotificationStrategy implements NotificationStrategy {

    Logger logger;

    @Override
    public String sendNotification(Notification notification) {
        logger.info("Sending notification through " + notification.getCanal());

        return "Sending notification through " + notification.getCanal();
    }
}
