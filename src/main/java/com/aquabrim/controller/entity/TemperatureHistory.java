package com.aquabrim.controller.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "temperature_history")
public class TemperatureHistory extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "controller_id", nullable = false)
    private Controller controller;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sub_id", nullable = false)
    private String subId;

    @Column(name = "current_temperature", nullable = false)
    private Double currentTemperature;

    @Column(name = "no_signal_alarm", nullable = false)
    private Integer noSignalAlarm;
}
