package by.bntu.fitr.notificationservice.rest;

import by.bntu.fitr.notificationservice.dto.SimpleEmailSendRequestDTO;
import by.bntu.fitr.notificationservice.service.SendNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/send-notification")
public class NotificationController {
    private final SendNotificationService sendNotificationService;

    @Autowired
    public NotificationController(SendNotificationService sendNotificationService) {
        this.sendNotificationService = sendNotificationService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> sendNotification(@RequestBody SimpleEmailSendRequestDTO simpleEmailSendRequestDTO) {
        sendNotificationService.sendSimpleNotification(simpleEmailSendRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
