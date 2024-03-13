package com.aquabrim.controller.repository;

import com.aquabrim.controller.entity.TemperatureHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureHistoryRepository extends JpaRepository<TemperatureHistory, Long> {
}
