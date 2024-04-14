package com.aquabrim.controller.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "controller")
public class Controller extends BaseEntity {
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "show_settings_id", referencedColumnName = "id")
    private ShowSettings showSettings;

    @Column(unique = true)
    private Integer deviceId;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "last_recharge_date")
    private LocalDateTime lastRechargeDate;

    @Column(name = "next_recharge_date")
    private LocalDateTime nextRechargeDate;

    @Column(name = "device_id_TX", length = 100, nullable = false)
    private String deviceIdTx;

    @Column(name = "motor_status")
    private int motorStatus;

    @Column(name = "signal_status", length = 10, nullable = false)
    private int signalStatus;

    @Column(name = "voltage", nullable = false)
    private Integer voltage;

    @Column(name = "time_out_interval", nullable = false)
    private Integer timeoutInterval;

    @Column(name = "controller_power_on_off", nullable = false)
    private Integer controllerPowerOnOff;//motor status

    @Column(name = "hold", nullable = false)
    private Integer hold;

    @Column(name = "voltage_status", nullable = false)
    private int voltageStatus;

    @Column(name = "timeout_status", nullable = false)
    private int timeoutStatus;

    @Column(name = "on_status", length = 10, nullable = false)
    private int onStatus;//controllerOnOff status discuss with dad.

    @Column(name = "flow_status", nullable = false)
    private int flowStatus;

    @Column(name = "enable_disable", nullable = false)
    private int enableDisable;

    @Column(name = "flow_protection", nullable = false)
    private int flowProtection;

    @Column(name = "motor_trigger", nullable = false)
    private int motorTrigger;

    @Column(name = "low_leve_alarm", nullable = false)
    private Integer lowLevelAlarm;

    @Value(value = "0")
    @Column(name = "full_level_alarm", nullable = false)
    private Integer fullLevelAlarm;

    @Column(name = "over_flow_alarm", nullable = false)
    private Integer overflowAlarm;

    @Column(name = "no_signal_duration",nullable = false)
    private Integer noSignalDuration;

    @Column(name = "mode_selection",nullable = false)
    private Integer modeSelection;

    @Column(name = "tx_type",nullable = false)
    private Integer txType;

    @Column(name = "operating_mn",nullable = false)
    private Integer operatingMn;

    @Column(name = "trails",nullable = false)
    private Integer trials;

    @Column(name = "trail_duration",nullable = false)
    private Integer trialDuration;

    @Column(name = "trail_gap",nullable = false)
    private Integer trialGap;

    @Column(name = "restart_delay",nullable = false)
    private Integer restartDelay;

    @Column(name = "timeout_duration",nullable = false)
    private Integer timeoutDuration;

    @Column(name = "timeout_protection",nullable = false)
    private Integer timeoutProtection;

    @Column(name = "voltage_enable",nullable = false)
    private Integer voltageEnable;

    @Column(name = "initial_start_delay",nullable = false)
    private Integer initialStartDelay;

    @Column(name = "high_voltage_point",nullable = false)
    private Integer highVoltagePoint;

    @Column(name = "low_voltage_point",nullable = false)
    private Integer lowVoltagePoint;

    @Column(name = "off_set_voltage",nullable = false)
    private Integer offsetVoltage;

    @Column(name = "high_voltage_protection",nullable = false)
    private Integer highVoltProtection;

    @Column(name = "low_voltage_protection",nullable = false)
    private Integer lowVoltProtection;

    @Column(name = "timer_enable",nullable = false)
    private Integer timerEnable;

    @Column(name = "week_days",nullable = false)
    private Integer weekDays;

    @Column(name = "timer_number",nullable = false)
    private Integer timerNumber;

    @Column(name = "hours",nullable = false)
    private Integer hours;

    @Column(name = "tx_alarm",nullable = false)
    private Integer txAlarm;

    @Column(name = "low_voltage_alarm",nullable = false)
    private Integer lowVoltageAlarm;

    @Column(name = "high_voltage_alarm",nullable = false)
    private Integer highVoltageAlarm;

    @Column(name = "timeout_alarm",nullable = false)
    private Integer timeoutAlarm;

    @Column(name = "flow_error_alarm", nullable = false)
    private Integer flowErrorAlarm;

    @Column(name = "motor_on_alarm", nullable = false)
    private Integer motorOnAlarm;

    @Column(name = "trial_enabled", nullable = false)
    private int trialEnabled;

    @Column(name = "timer_based", nullable = false)
    private Integer timerBased;

    @Column(name = "trail_period", nullable = false)
    private Integer trialPeriod;

    @Column(name = "license_type", length = 100, nullable = false)
    private String licenseType;

    @Column(name = "select_motor", length = 100, nullable = false)
    private String selectMotor;

    @Column(name = "max_motor_on", length = 100, nullable = false)
    private String maxMotorOn;

    @Column(name = "comm_error_alarm", nullable = false)
    private int commErrorAlarm;

    @Column(name = "max_duration_hours", nullable = false)
    private Integer maxDurationHours;

    @Column(name = "max_duration_minutes", nullable = false)
    private Integer maxDurationMinutes;

    @Column(name = "device_type", nullable = false)
    private int deviceType;

    @Column(name = "timer_days", length = 100, nullable = false)
    private String timerDays;

    @Column(name = "master_weekdays", nullable = false)
    private Integer masterWeekDays;

    @Column(name = "master_hours", nullable = false)
    private Integer masterHours;

    @Column(name = "master_minutes", nullable = false)
    private Integer masterMinutes;
    @Column(name = "reset_interval", nullable = false)
    private Integer resetInterval;


    @Column(name = "com_pass", length = 100, nullable = false)
    private String comPass;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate = LocalDateTime.now();

    @Column(name = "last_motor_on_time", nullable = false)
    private LocalDateTime lastMotorOnTime;

    @Column(name = "last_motor_off_time", nullable = false)
    private LocalDateTime lastMotorOffTime;
    @Column(name = "com_m", nullable = false)
    private Integer comM;

    @Column(name = "com_t", nullable = false)
    private Integer comT;

    @Column(name = "com_tr", nullable = false)
    private Integer comTr;

    @Column(name = "com_d", nullable = false)
    private Integer comD;

    @Column(name = "com_last_access_auth_or_not", nullable = false)
    private Integer comLastAccessAuthOrNot;

    @Column(name = "show_historical_data", nullable = false)
    private Integer showHistoricalData;

    @Column(name = "controller_power_on", nullable = false)
    private Integer controllerPowerOn;

    @Column(name = "controller_power_failure", nullable = false)
    private Integer controllerPowerFailure;

    @Column(name = "rx_uart_partial_or_erratic_data", nullable = false)
    private Integer rxUartPartialOrErraticData;

    @Column(name = "no_data_from_rx_last_five_minutes", nullable = false)
    private Integer noDataFromRxLastFiveMinutes;

    @Column(name = "rx_address_not_initialised", nullable = false)
    private Integer rxAddressNotInitialised;

    @Column(name = "comm_error_alarm_rx", nullable = false)
    private Integer commErrorAlarmRx;

    @Column(name = "number_history_records_to_keep", nullable = false)
    private Integer numberHistoryRecordsToKeep;

    @Column(name = "phase_failure_alarm", nullable = false)
    private Integer phaseFailureAlarm;

    @Column(name = "load_error_alarm", nullable = false)
    private Integer loadErrorAlarm;

    @Column(name = "power_failure_alarm", nullable = false)
    private Integer powerFailureAlarm;

    @Column(name = "sms_alert_alarm", nullable = false)
    private Integer smsAlertAlarm;

    @Column(name = "call_alert_alarm", nullable = false)
    private Integer callAlertAlarm;

    @Column(name = "phone_number_for_alarm", nullable = false)
    private String phoneNumberForAlarm;

    @Column(name = "alert_comm_error_rx", nullable = false)
    private Integer alertCommErrorRx;

    @Column(name = "alert_comm_error_tx", nullable = false)
    private Integer alertCommErrorTx;

    @Column(name = "alert_flow_error", nullable = false)
    private Integer alertFlowError;

    @Column(name = "alert_high_volt", nullable = false)
    private Integer alertHighVolt;

    @Column(name = "alert_low_volt", nullable = false)
    private Integer alertLowVolt;

    @Column(name = "alert_time_out", nullable = false)
    private Integer alertTimeOut;

    @Column(name = "phase_failure", nullable = false)
    private Integer phaseFailure;

    @Column(name = "load_or_amp_error", nullable = false)
    private Integer loadOrAmpError;

    @Column(name = "call_balance", nullable = false)
    private Integer callBalance;

    @Column(name = "sms_balance", nullable = false)
    private Integer smsBalance;

}