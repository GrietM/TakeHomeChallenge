package com.miempresa.miaplicacion.notifications.service.strategy;

import com.miempresa.miaplicacion.notifications.model.Notification;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class SMSNotificationStrategy implements NotificationStrategy {

    @Override
    public String sendNotification(Notification notification) {
        adjustLength(notification);
        String additionalData = registerAdditionalData();

        String message = "Sending notification through " + notification.getCanal() + " on Date: " + additionalData;

        System.out.println(message);
        return message;
    }

    private void adjustLength(Notification notification ){
        String content = notification.getContenido();
        if(content != null && content.length() > 160){
            String shortenedContent =content.substring(0, 160);
            notification.setContenido(shortenedContent);
        }

    }

    private String registerAdditionalData(){
        LocalDateTime fechaEnvio = LocalDateTime.now();
        return fechaEnvio.toString();
    }
}
