package com.aquabrim.controller.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Tank")
public class Tank extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "controller_id", nullable = false)
    private Controller controllerId;

    @Column(nullable = false)
    private String name;

    @Column(name = "sub_id", nullable = false)
    private String subId;

    @Column(name = "motor_trigger", nullable = false)
    private int motorTrigger = 0;

    @Column(name = "low_level_alarm", nullable = false)
    private Integer lowLevelAlarm;

    @Column(name = "full_level_alarm", nullable = false)
    private Integer fullLevelAlarm;

    @Column(name = "overflow_alarm", nullable = false)
    private Integer overflowAlarm;

    @Column(name = "current_overflow_status", nullable = false)
    private Integer currentOverflowStatus;

    @Column(name = "current_flow_status", nullable = false)
    private Integer currentFlowStatus;

    @Column(name = "no_signal_alarm", nullable = false)
    private Integer noSignalAlarm;

    @Column(name = "water_level", nullable = false)
    private Integer waterLevel;

    @Column(name = "offset_level_reset", nullable = false)
    private Integer offsetLevelReset;

    @Column(name = "water_level_type", nullable = false)
    private Integer waterLevelType;

    @Column(name = "full_water_level", nullable = false)
    private Integer fullWaterLevel;

    @Column(name = "low_water_level", nullable = false)
    private Integer lowWaterLevel;

    @Column(nullable = false)
    private Integer display;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @Column(name = "low_water_level_alarm", nullable = false)
    private Integer lowWaterLevelAlarm;

    @Column(name = "alert_no_signal", nullable = false)
    private Integer alertNoSignal;

    @Column(name = "alert_low_water_level", nullable = false)
    private Integer alertLowWaterLevel;

    @Column(name = "alert_full_water_level", nullable = false)
    private Integer alertFullWaterLevel;

    @Column(name = "alert_overflow", nullable = false)
    private Integer alertOverflow;

    // Newly added fields
    @Column(name = "motorized_valve_status", nullable = false)
    private Integer motorizedValveStatus;

    @Column(name = "flow_status", nullable = false)
    private Integer flowStatus;

    @Column(name = "flow_rate", nullable = false)
    private Float flowRate;

}
