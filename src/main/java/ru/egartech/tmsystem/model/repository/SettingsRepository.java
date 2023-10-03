package ru.egartech.tmsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egartech.tmsystem.model.entity.Settings;

import java.util.Optional;

public interface SettingsRepository extends JpaRepository<Settings, Long> {
    public Optional<Settings> findByCurrentSettingsProfileIsTrue();
}
