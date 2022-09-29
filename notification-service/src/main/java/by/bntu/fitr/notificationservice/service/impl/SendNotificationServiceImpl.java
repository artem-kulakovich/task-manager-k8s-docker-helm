package by.bntu.fitr.notificationservice.service.impl;

import by.bntu.fitr.notificationservice.dto.SimpleEmailSendRequestDTO;
import by.bntu.fitr.notificationservice.service.SendNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendNotificationServiceImpl implements SendNotificationService {
    private final MailSender mailSender;
    @Value("spring.mail.username")
    private String from;

    @Autowired
    public SendNotificationServiceImpl(@Qualifier(value = "simpleJavaMailSender") final MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendSimpleNotification(SimpleEmailSendRequestDTO simpleEmailSendRequestDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(simpleEmailSendRequestDTO.getSendTo());
        message.setSubject(simpleEmailSendRequestDTO.getSubject());
        message.setText(simpleEmailSendRequestDTO.getMsg());

        mailSender.send(message);

    }
}
