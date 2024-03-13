package com.aquabrim.controller.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "controller_history")
public class ControllerHistory extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @Column(name = "on_off", nullable = false)
    private Integer onoff;

    @Column(name = "low_voltage_alarm", nullable = false)
    private Integer lowVoltageAlarm;

    @Column(name = "high_voltage_alarm", nullable = false)
    private Integer highVoltageAlarm;

    @Column(name = "flow_error_alarm", nullable = false)
    private Integer flowErrorAlarm;

    @Column(name = "comm_error_alarm", nullable = false)
    private Integer commErrorAlarm;

    @Column(name = "controller_power_failure", nullable = false)
    private Integer controllerPowerFailure;

    @Column(name = "log_time", nullable = false)
    private LocalDateTime logTime;

    @Column(name = "log_time_string", nullable = false)
    private String logTimeString;


}
