package com.aquabrim.controller.entity;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

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
