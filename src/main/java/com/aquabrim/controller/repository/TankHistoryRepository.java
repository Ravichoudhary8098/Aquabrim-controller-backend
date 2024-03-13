package com.aquabrim.controller.repository;

import com.aquabrim.controller.entity.TankHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TankHistoryRepository extends JpaRepository<TankHistory, Long> {
}
