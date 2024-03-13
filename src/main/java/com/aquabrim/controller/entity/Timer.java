package com.aquabrim.controller.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "timer")
public class Timer extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "controller_id", referencedColumnName = "id", nullable = false)
    private Controller controller;

    @Column(name = "week_days")
    private Integer weekDays;

    @Column(name = "timer_enable")
    private Integer timerEnable;

    @Column(name = "timer_number")
    private Integer timerNumber;

    @Column(name = "hours")
    private Integer hours;

    @Column(name = "minutes")
    private Integer minutes;

    @Column(name = "max_duration_hours")
    private Integer maxDurationHours;

    @Column(name = "max_duration_minutes")
    private Integer maxDurationMinutes;

}
