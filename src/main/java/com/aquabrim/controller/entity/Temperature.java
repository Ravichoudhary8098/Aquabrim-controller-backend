package com.aquabrim.controller.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "temperature")
public class Temperature extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "controller_id", nullable = false)
    private Controller controller;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String subId;

    @Column(name = "current_temperature", nullable = false)
    private Double currentTemperature;

    @Column(name = "high_temperature_alarm", nullable = false)
    private Double highTemperatureAlarm;

    @Column(name = "low_temperature_alarm", nullable = false)
    private Double lowTemperatureAlarm;

    @Column(name = "no_signal_alarm", nullable = false)
    private boolean noSignalAlarm;

    @Column(name = "log_time", nullable = false)
    private LocalDateTime logTime;

    @Column(name = "log_time_string", nullable = false)
    private String logTimeString;


}
