package com.miempresa.miaplicacion.notifications.service.strategy;

import com.miempresa.miaplicacion.notifications.model.Notification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EmailNotificationStrategy implements NotificationStrategy {


    @Override
    public String sendNotification(Notification notification) {

        String destinatario = "destinatario@example.com";
        Boolean isValidDestiny = validateDestiny(destinatario);

        if(!isValidDestiny){
            String error = "ERROR: El destinatario no cumple con el formato exigido";
            System.out.println(error);
            return error;
        }

        String template = generateTemplate(notification, destinatario);
        String fechaEnvio = registerAdditionalData();

        String message = "EMAIL sent to " + destinatario
                + " on " + fechaEnvio
                + "\n" + template;

        System.out.println(message);
        return message;
    }

    private Boolean validateDestiny(String destinatario){
        if (destinatario == null) {
            return false;
        }
        return destinatario.contains("@") && destinatario.contains(".");
    }

    private String generateTemplate(Notification notification, String destinatario){
        String title = notification.getTitulo();
        String content = notification.getContenido();
        return """
            [EMAIL TEMPLATE]
            To: %s
            Subject: %s

            %s
            """.formatted(destinatario, title, content);

    }

    private String registerAdditionalData(){
        LocalDateTime fechaEnvio = LocalDateTime.now();
        return fechaEnvio.toString();
    }

}
