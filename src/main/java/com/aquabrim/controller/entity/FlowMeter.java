package com.aquabrim.controller.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "flow_meter")
public class FlowMeter extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "controller_id", nullable = false)
    private Controller controller;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sub_id", nullable = false)
    private String subId;

    @Column(name = "flow_rate", nullable = false)
    private Double flowRate;

    @Column(name = "high_flow_rate_alarm", nullable = false)
    private Double highFlowRateAlarm;

    @Column(name = "low_flow_rate_alarm", nullable = false)
    private Double lowFlowRateAlarm;

    @Column(name = "no_signal_alarm", nullable = false)
    private boolean noSignalAlarm;

}
