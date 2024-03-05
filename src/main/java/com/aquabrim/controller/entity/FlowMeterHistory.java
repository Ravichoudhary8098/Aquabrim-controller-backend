package com.aquabrim.controller.entity;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class FlowMeterHistory {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "controller_id", nullable = false)
    private Controller controller;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sub_id", nullable = false)
    private String subId;

    @Column(name = "flow_rate", nullable = false)
    private Double flow_rate;

    @Column(name = "no_signal_alarm", nullable = false)
    private Integer noSignalAlarm;
}
