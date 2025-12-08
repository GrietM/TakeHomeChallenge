package com.miempresa.miaplicacion.notifications.service.strategy;

import com.miempresa.miaplicacion.notifications.model.Notification;
import io.swagger.v3.core.util.Json;
import org.springframework.stereotype.Component;

@Component
public class PushNotificationStrategy implements NotificationStrategy {

    @Override
    public String sendNotification(Notification notification) {

        String token = "exampleToken";
        boolean isValidToken = validateToken(token);
        if(!isValidToken){
            String error = "ERROR: Invalid device token";
            System.out.println(error);
            return error;
        }

        String formattedPayload = formatPayload(notification, token);
        String status = registerStatus();
        String message ="Sending notification through "+ notification.getCanal() + ". The payload is:  \n" + formattedPayload + " and the status: " + status;

        System.out.println(message);
        return message;
    }

   private boolean validateToken(String token){
       return  token != null && !token.isBlank();
    }

    private String formatPayload(Notification notification, String token){
        String title = notification.getTitulo();
        String content = notification.getContenido();;
        return """
            {
              "token": "%s",
              "title": "%s",
              "body": "%s"
            }
            """.formatted(token , title, content);

    }

    private String registerStatus() {
        return "SENT";
    }
}
