package com.aquabrim.controller.repository;

import com.aquabrim.controller.entity.ShowSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSettingsRepository extends JpaRepository<ShowSettings, Long> {
}
