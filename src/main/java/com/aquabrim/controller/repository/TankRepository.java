package com.aquabrim.controller.repository;

import com.aquabrim.controller.entity.Controller;
import com.aquabrim.controller.entity.Tank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TankRepository extends JpaRepository<Tank, Long> {
    @Query("SELECT tank FROM Tank tank WHERE tank.controllerId = ?1 AND tank.subId = ?2")

    Tank findByControllerAndSubId(Controller controller, int subId);
}
