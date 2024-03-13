package com.aquabrim.controller.repository;

import com.aquabrim.controller.entity.FlowMeterHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowMeterHistoryRepository extends JpaRepository<FlowMeterHistory, Long> {
}
