package com.miempresa.miaplicacion.notifications.service;

import com.miempresa.miaplicacion.notifications.model.Notification;
import com.miempresa.miaplicacion.notifications.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

  private final NotificationRepository notificationRepository;
  private final NotificationSender notificationSender;

  public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationSender notificationSender) {
    this.notificationRepository = notificationRepository;
    this.notificationSender = notificationSender;
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
      Notification saved = notificationRepository.save(notification);
      notificationSender.sendNotification(notification);
      return saved;
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
