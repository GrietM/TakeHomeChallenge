package com.miempresa.miaplicacion.notifications.service;

import com.miempresa.miaplicacion.notifications.model.Notification;
import com.miempresa.miaplicacion.notifications.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

  private final NotificationRepository notificationRepository;

  public NotificationServiceImpl(NotificationRepository notificationRepository) {
    this.notificationRepository = notificationRepository;
  }

  @Override
  public List<Notification> getAllNotifications() {
    return notificationRepository.findAll();
  }

  @Override
  public Optional<Notification> getNotificationById(Long id) {
    return notificationRepository.findById(id);
  }

  @Override
  public Notification createNotification(Notification notification) {
    return notificationRepository.save(notification);
  }

    @Override
    public Optional<Notification> updateNotification(Long id, Notification notification) {
      return notificationRepository.findById(id)
              .map(existing -> {
                    existing.setTitulo(notification.getTitulo());
                    existing.setContenido(notification.getContenido());
                    existing.setCanal(notification.getCanal());
                  return notificationRepository.save(existing);
                });
    }

  @Override
  public void deleteNotificationById(Long id) {
    notificationRepository.deleteById(id);
  }
}
