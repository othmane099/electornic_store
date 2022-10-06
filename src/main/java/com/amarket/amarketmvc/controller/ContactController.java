package com.amarket.amarketmvc.controller;

import com.amarket.amarketmvc.dto.ContactUsDto;
import com.amarket.amarketmvc.model.AppSettings;
import com.amarket.amarketmvc.service.AppSettingsService;
import com.amarket.amarketmvc.service.ContactUsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class ContactController {

    private final AppSettingsService appSettingsService;
    private final ContactUsService contactUsFormService;


    @GetMapping("/contact")
    public String contact(Model model){

        AppSettings appSettings = appSettingsService.getAppSettings();
        model.addAttribute("appSettings", appSettings);
        return "contact";
    }


    @PostMapping("/contactusTemplate")
    public String contactForm(ContactUsDto contactUsForm) throws Exception {
        contactUsFormService.sendMail(contactUsForm);
        return "redirect:/contact";
    }
}
