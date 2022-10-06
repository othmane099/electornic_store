package com.amarket.amarketmvc.repository;

import com.amarket.amarketmvc.model.AppSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppSettingsRepository extends JpaRepository<AppSettings, Long> {
}
