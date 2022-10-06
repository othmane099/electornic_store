package com.amarket.amarketmvc.controller;

import com.amarket.amarketmvc.model.AppSettings;
import com.amarket.amarketmvc.model.IndexPage;
import com.amarket.amarketmvc.service.AppSettingsService;
import com.amarket.amarketmvc.service.IndexPageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AppSettingsController {
    private final AppSettingsService settingsService;
    private final IndexPageService indexPageService;

    @GetMapping("/admin/settings")
    public String settingsPage(Model model){
        AppSettings settings = settingsService.getAppSettings();
        IndexPage indexPage = indexPageService.getParameters();

        model.addAttribute("settings", settings);
        model.addAttribute("indexPage", indexPage);
        return "admin/admin-settings";
    }

    @PostMapping("/admin/settings/save")
    public String updateSettings(AppSettings settings){
        settingsService.update(settings);
        return "redirect:/admin/settings";
    }
}
