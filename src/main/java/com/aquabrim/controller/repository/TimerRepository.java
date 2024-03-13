package com.aquabrim.controller.repository;

import com.aquabrim.controller.entity.Controller;
import com.aquabrim.controller.entity.Timer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TimerRepository extends JpaRepository<Timer, Long> {
    @Query("SELECT timer From Timer timer WHERE timer.controller =?1 AND timer.timerNumber =?2")
    Timer getTimerByTimerNumberAndController(Controller controller, int timerNumber);
}
