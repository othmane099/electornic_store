package com.amarket.amarketmvc.service;

import com.amarket.amarketmvc.model.AppSettings;
import com.amarket.amarketmvc.repository.AppSettingsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class AppSettingsService {

    private final AppSettingsRepository appSettingsRepository;

    public void save(AppSettings appSettings){
        appSettingsRepository.save(appSettings);
    }

    public AppSettings update(AppSettings appSettings){
        return appSettingsRepository.save(appSettings);
    }

    public AppSettings getAppSettings(){
        return appSettingsRepository.findAll().get(0);
    }
}
