package com.amarket.amarketmvc.service;

import com.amarket.amarketmvc.dto.ContactUsDto;
import com.amarket.amarketmvc.model.AppSettings;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class ContactUsService {

    private final JavaMailSender mailSender;
    private final com.amarket.amarketmvc.service.MailContentBuilder mailContentBuilder;
    private final AppSettingsService settingService;




    @Async
    public void sendMail(ContactUsDto form) throws Exception {
        AppSettings setting = settingService.getAppSettings();
        Map<String, Object> contents = new HashMap<>();
        contents.put("fullName", form.getContactName());
        contents.put("email", form.getContactEmail());
        contents.put("subject", form.getContactSubject());
        contents.put("message", form.getContactMessage());

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(form.getContactEmail(), form.getContactName());
            messageHelper.setTo(setting.getMailReceptionAgency());
            messageHelper.setSubject(form.getContactSubject());
            messageHelper.setText(mailContentBuilder.build(contents), true);
        };
        try {
            mailSender.send(messagePreparator);
            log.info("contact us email sent!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new Exception("Exception occurred when sending mail to " + setting.getMailReceptionAgency());
        }
    }
}
