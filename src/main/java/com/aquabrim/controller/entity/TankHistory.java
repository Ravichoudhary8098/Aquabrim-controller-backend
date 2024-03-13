package com.aquabrim.controller.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tank_history")
public class TankHistory extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "controller_id", nullable = false)
    private Controller controller;

    @Column(nullable = false)
    private String name;

    @Column(name = "sub_id", nullable = false)
    private String subId;

    @Column(name = "water_level", nullable = false)
    private Integer waterLevel;

    @Column(name = "current_flow_status", nullable = false)
    private Integer currentFlowStatus;

    @Column(name = "current_overflow_status", nullable = false)
    private Integer currentOverflowStatus;

    @Column(name = "motorized_valve_status", nullable = false)
    private Integer motorizedValveStatus;

    @Column(name = "full_water_level", nullable = false)
    private Integer fullWaterLevel;

    @Column(name = "low_water_level", nullable = false)
    private Integer lowWaterLevel;

    @Column(name = "no_signal_alarm", nullable = false)
    private Integer noSignalAlarm;

    @Column(name = "log_time", nullable = false)
    private LocalDateTime logTime;

    @Column(name = "log_time_string", nullable = false)
    private String logTimeString;

}
