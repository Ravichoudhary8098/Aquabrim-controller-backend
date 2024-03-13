package com.aquabrim.controller.repository;

import com.aquabrim.controller.entity.Controller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControllerRepository extends JpaRepository<Controller, Long> {
}
