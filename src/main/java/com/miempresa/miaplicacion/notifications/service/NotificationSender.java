package com.miempresa.miaplicacion.notifications.service;

import com.miempresa.miaplicacion.notifications.model.Channel;
import com.miempresa.miaplicacion.notifications.model.Notification;
import com.miempresa.miaplicacion.notifications.service.strategy.EmailNotificationStrategy;
import com.miempresa.miaplicacion.notifications.service.strategy.NotificationStrategy;
import com.miempresa.miaplicacion.notifications.service.strategy.PushNotificationStrategy;
import com.miempresa.miaplicacion.notifications.service.strategy.SMSNotificationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class NotificationSender {

    private NotificationStrategy notificationStrategy;
    @Autowired
    private EmailNotificationStrategy emailNotificationStrategy;

    @Autowired
    private SMSNotificationStrategy smsNotificationStrategy;

    @Autowired
    private PushNotificationStrategy pushNotificationStrategy;

    public String sendNotification(Notification notification) {
        Channel canal = notification.getCanal();

        if (canal == Channel.EMAIL){
            this.setNotificationStrategyInterface(emailNotificationStrategy);
        }
        if (canal == Channel.SMS){
            this.setNotificationStrategyInterface(smsNotificationStrategy);
        }
        if (canal == Channel.PUSH){
            this.setNotificationStrategyInterface(pushNotificationStrategy);
        }

        return notificationStrategy.sendNotification(notification);
    }

    private void setNotificationStrategyInterface(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

}
