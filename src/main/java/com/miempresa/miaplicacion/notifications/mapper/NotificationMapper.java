package com.miempresa.miaplicacion.notifications.mapper;

import com.miempresa.miaplicacion.notifications.dto.NotificationCreateRequestDTO;
import com.miempresa.miaplicacion.notifications.dto.NotificationResponseDTO;
import com.miempresa.miaplicacion.notifications.model.Notification;
import com.miempresa.miaplicacion.users.model.User;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public Notification toEntity (NotificationCreateRequestDTO notificationCreateRequestDTO, User user){
        Notification notification = new Notification();
        notification.setTitulo(notificationCreateRequestDTO.getTitulo());
        notification.setContenido(notificationCreateRequestDTO.getContenido());
        notification.setCanal(notificationCreateRequestDTO.getCanal());
        notification.setUser(user);

        return notification;
    }

    public NotificationResponseDTO toResponseDTO (Notification n){
        NotificationResponseDTO notificationResponseDTO = new NotificationResponseDTO();
        notificationResponseDTO.setId(n.getId());
        notificationResponseDTO.setTitulo(n.getTitulo());
        notificationResponseDTO.setContenido(n.getContenido());
        notificationResponseDTO.setCanal(n.getCanal());
        notificationResponseDTO.setUserId(
            n.getUser() != null ? n.getUser().getId() : null
        );

        return notificationResponseDTO;
    }

    public void applyUpdates(Notification existing, NotificationCreateRequestDTO dto, User user) {
        existing.setTitulo(dto.getTitulo());
        existing.setContenido(dto.getContenido());
        existing.setCanal(dto.getCanal());
        existing.setUser(user);
    }


}
