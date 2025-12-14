package com.miempresa.miaplicacion.notifications.service;

import com.miempresa.miaplicacion.notifications.dto.NotificationCreateRequestDTO;
import com.miempresa.miaplicacion.notifications.dto.NotificationResponseDTO;
import com.miempresa.miaplicacion.notifications.mapper.NotificationMapper;
import com.miempresa.miaplicacion.notifications.model.Notification;
import com.miempresa.miaplicacion.notifications.repository.NotificationRepository;
import com.miempresa.miaplicacion.users.model.User;
import com.miempresa.miaplicacion.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

  private final NotificationRepository notificationRepository;
  private final NotificationSender notificationSender;
  private final UserRepository userRepository;
  private final NotificationMapper notificationMapper;

  public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationSender notificationSender, UserRepository userRepository, NotificationMapper notificationMapper) {
    this.notificationRepository = notificationRepository;
    this.notificationSender = notificationSender;
    this.userRepository= userRepository;
    this.notificationMapper= notificationMapper;
  }

  @Override
  public List<NotificationResponseDTO> getAllNotifications() {
    List<Notification> notificationList = notificationRepository.findAll();
    List<NotificationResponseDTO> notificationResponseDTOList = new ArrayList<>();
    for(Notification n : notificationList){
        NotificationResponseDTO notificationResponseDTO = notificationMapper.toResponseDTO(n);
        notificationResponseDTOList.add(notificationResponseDTO);
    }
    return notificationResponseDTOList;

    //podria usar streams asi:
   /*   return notificationRepository.findAll()
              .stream()
              .map(notificationMapper::toResponseDTO)
              .toList();*/
  }

  @Override
  public Optional<NotificationResponseDTO> getNotificationById(Long id) {
    return notificationRepository.findById(id).map(notificationMapper::toResponseDTO);
  }


  @Override
  public NotificationResponseDTO createNotification(NotificationCreateRequestDTO notification) {
      Long userID = notification.getUserId();
      User user = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User not found"));

      Notification newNotification = notificationMapper.toEntity(notification, user);

      Notification saved = notificationRepository.save(newNotification);
      notificationSender.sendNotification(saved);
      return notificationMapper.toResponseDTO(saved);//?
  }

  @Override
  public Optional<NotificationResponseDTO> updateNotification(Long id, NotificationCreateRequestDTO notificationDTO) {
      Optional<Notification> notification = notificationRepository.findById(id);
      if (notification.isEmpty()){
          return Optional.empty(); // controller -> 404
      }

      Optional<User> optUser = userRepository.findById(notificationDTO.getUserId());
      if (optUser.isEmpty()) {
          throw new RuntimeException("User not found"); // mañana lo refinamos a 404/400
      }

      Notification existing = notification.get();

      notificationMapper.applyUpdates(existing,notificationDTO,optUser.get());
      Notification saved = notificationRepository.save(existing);
      return Optional.of(notificationMapper.toResponseDTO(saved));

  }

  @Override
  public void deleteNotificationById(Long id) {
      notificationRepository.deleteById(id);
  }

  @Override
  public List<NotificationResponseDTO> getNotificationsByUser(Long userId) {
      List<Notification> notificationList = notificationRepository.findByUser_Id(userId);
      List<NotificationResponseDTO> notificationResponseDTOList = new ArrayList<>();
      for(Notification n : notificationList){
          NotificationResponseDTO notificationResponseDTO = notificationMapper.toResponseDTO(n);
          notificationResponseDTOList.add(notificationResponseDTO);
      }

      return notificationResponseDTOList;
  }

}
