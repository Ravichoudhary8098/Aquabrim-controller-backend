package com.aquabrim.controller.repository;

import com.aquabrim.controller.entity.ControllerHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControllerHistoryRepository extends JpaRepository<ControllerHistory, Long> {
}
