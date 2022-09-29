package by.bntu.fitr.notificationservice.service;

import by.bntu.fitr.notificationservice.dto.SimpleEmailSendRequestDTO;

public interface SendNotificationService {

    void sendSimpleNotification(final SimpleEmailSendRequestDTO simpleEmailSendRequestDTO);
}
