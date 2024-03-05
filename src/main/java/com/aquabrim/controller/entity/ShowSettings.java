package com.aquabrim.controller.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "show_settings")
public class ShowSettings extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "controller_id", unique = true, nullable = false)
    private Controller controller;

    @Column(name = "show_tank_setting", nullable = false)
    private boolean showTankSetting;

    @Column(name = "show_controller_setting", nullable = false)
    private boolean showControllerSetting;

    @Column(name = "show_display_setting", nullable = false)
    private boolean showDisplaySetting;

    @Column(name = "show_timer_setting", nullable = false)
    private boolean showTimerSetting;

    @Column(name = "show_view_history", nullable = false)
    private boolean showViewHistory;

    @Column(name = "show_event_history", nullable = false)
    private boolean showEventHistory;

    @Column(name = "enable_motor_on_off", nullable = false)
    private boolean enableMotorOnOff;

    @Column(name = "show_power_status", nullable = false)
    private boolean showPowerStatus;

    @Column(name = "show_phase_failure", nullable = false)
    private boolean showPhaseFailure;

    @Column(name = "show_amp_error", nullable = false)
    private boolean showAmpError;

    @Column(name = "show_rx_comm_error", nullable = false)
    private boolean showRxCommError;

}
