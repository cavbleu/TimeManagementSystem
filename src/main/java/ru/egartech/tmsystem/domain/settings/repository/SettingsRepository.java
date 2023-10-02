package ru.egartech.tmsystem.domain.settings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmsystem.domain.settings.entitiy.Settings;

import java.util.Optional;

public interface SettingsRepository extends JpaRepository<Settings, Long> {
    public Optional<Settings> findByCurrentSettingsProfileIsTrue();
}
