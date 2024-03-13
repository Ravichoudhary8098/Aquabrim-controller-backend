package com.aquabrim.controller.repository;

import com.aquabrim.controller.entity.FlowMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowMeterRepository extends JpaRepository<FlowMeter, Long> {
}
