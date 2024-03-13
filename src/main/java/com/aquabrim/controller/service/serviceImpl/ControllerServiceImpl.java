package com.aquabrim.controller.service.serviceImpl;

import com.aquabrim.controller.entity.Controller;
import com.aquabrim.controller.entity.Tank;
import com.aquabrim.controller.entity.Timer;
import com.aquabrim.controller.repository.TankRepository;
import com.aquabrim.controller.repository.TimerRepository;
import com.aquabrim.controller.service.ControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ControllerServiceImpl implements ControllerService {
    @Autowired
    TankRepository tankRepository;
    @Autowired
    TimerRepository timerRepository;

    @Override
    public Controller findByDeviceId(String idno) {
        return null;
    }

    @Override
    public List<String> getAllDeviceIds() {
        return null;
    }

    @Override
    public void SaveWaterControllerData(Controller controller, int commandId, String payloadA, String payloadB) {
        int timerNumber = Integer.parseInt(payloadA.substring(0, 3), 2);
        Timer timer = timerRepository.getTimerByTimerNumberAndController(controller, timerNumber);
        int subId = Integer.parseInt(payloadA.substring(0, 6), 2);
        Tank tank = tankRepository.findByControllerAndSubId(controller, subId);
        if (tank == null) {
            return;
        }

        int valueOf7thBitPA = Integer.parseInt(String.valueOf(payloadA.charAt(6)));
        int valueOf8thBitPA = Integer.parseInt(String.valueOf(payloadA.charAt(7)));
        if (commandId == 1) {
            int waterLevel = Integer.parseInt(payloadB.substring(1), 2);
            tank.setCurrentOverflowStatus(valueOf7thBitPA);
            tank.setCurrentFlowStatus(valueOf8thBitPA);
            tank.setWaterLevel(waterLevel);

        } else if (commandId == 2) {
            //for future use
        } else if (commandId == 3) {
            int MotorTrigger = Integer.parseInt(String.valueOf(payloadB.charAt(0)));
            int flowProtection = Integer.parseInt(String.valueOf(payloadB.charAt(1)));
            int enableDisable = Integer.parseInt(String.valueOf(payloadB.charAt(2)));
            int noSignalDuration = Integer.parseInt(payloadB.substring(3), 2);

            tank.setLowLevelAlarm(valueOf8thBitPA);
            tank.setFullLevelAlarm(valueOf7thBitPA);
            tank.setMotorTrigger(MotorTrigger);
            controller.setFlowProtection(flowProtection);
            controller.setEnableDisable(enableDisable);
            controller.setNoSignalDuration(noSignalDuration);


        } else if (commandId == 4) {
            int Mode = Integer.parseInt(payloadB.substring(6, 8), 2);
            int Tr = Integer.parseInt(String.valueOf(payloadB.charAt(0)), 2);
            int D = Integer.parseInt(payloadB.substring(1, 5), 2);
            int T = Integer.parseInt(String.valueOf(payloadB.charAt(5)), 2);

            controller.setModeSelection(Mode);
            controller.setTrialEnabled(Tr);
            controller.setTimerBased(T);
            controller.setTrialPeriod(D);
        } else if (commandId == 5) {
            int o = Integer.parseInt(payloadA.substring(6, 8), 2) * 5 + 10;
            int w = Integer.parseInt(payloadB.substring(0, 1), 2);
            int l = Integer.parseInt(payloadB.substring(1, 8), 2);

            tank.setWaterLevelType(w);
            tank.setOffsetLevelReset(o);
            tank.setWaterLevel(l);

        } else if (commandId == 6) {
            int rd = Integer.parseInt(payloadA.substring(0, 3), 2);
            int td = Integer.parseInt(payloadA.substring(3, 8), 2);
            int tg = Integer.parseInt(payloadB.substring(0, 4), 2);
            int n = Integer.parseInt(payloadB.substring(4, 6), 2);
            int mOperating = Integer.parseInt(payloadB.substring(6, 8), 2);

            controller.setRestartDelay(rd + 1);
            controller.setTrialGap(tg * 2);
            controller.setTrialDuration(td);
            controller.setTrials(n + 1);
            controller.setOperatingMn(mOperating);

        } else if (commandId == 7) {
            int ri = Integer.parseInt(payloadA.substring(0, 3), 2);
            int sd = Integer.parseInt(payloadA.substring(3, 7), 2);
            int en = Integer.parseInt(payloadA.substring(7, 8), 2);
            int to = Integer.parseInt(payloadB, 2);

            controller.setResetInterval(ri * 15);
            controller.setInitialStartDelay(sd);
            controller.setTimeoutProtection(en);
            controller.setTimeoutDuration((to * 2) + 60);

        } else if (commandId == 8) {
            int ov = Integer.parseInt(payloadA.substring(0, 4), 2);
            int lv = Integer.parseInt(payloadA.substring(4, 8), 2);
            int le = Integer.parseInt(payloadB.substring(1, 2), 2);
            int he = Integer.parseInt(payloadB.substring(2, 3), 2);
            int hv = Integer.parseInt(payloadB.substring(3, 8), 2);

            controller.setVoltageEnable(1);
            controller.setHighVoltagePoint((hv * 4) + 240);
            controller.setLowVoltagePoint(200 - (lv * 3));
            controller.setOffsetVoltage(ov);
            controller.setLowVoltageAlarm(le);
            controller.setHighVoltageAlarm(he);

        } else if (commandId == 9 && timer != null) {
            int hours = Integer.parseInt(payloadA.substring(3, 8), 2);
            int day = Integer.parseInt(payloadB.substring(0, 2), 2);
            int minutes = Integer.parseInt(payloadB.substring(2, 8), 2);

            timer.setHours(hours);
            timer.setMinutes(minutes);
            timer.setWeekDays(day);

        } else if (commandId == 10 && timer != null) {
            int hours = Integer.parseInt(payloadA.substring(3, 8), 2);
            int m = Integer.parseInt(payloadB.substring(2, 8), 2);

            timer.setMaxDurationHours(hours);
            timer.setMaxDurationMinutes(m);


        } else if (commandId == 11) {
            int day = Integer.parseInt(payloadA.substring(0, 3), 2);
            int hours = Integer.parseInt(payloadA.substring(3, 8), 2);
            int minutes = Integer.parseInt(payloadB.substring(2, 8), 2);

            controller.setMasterWeekDays(day);
            controller.setMasterHours(hours);
            controller.setMasterMinutes(minutes);


        } else if (commandId == 12) {
            int onOff = Integer.parseInt(payloadB.substring(2, 3), 2);
            boolean valueChanged = controller.getOnStatus() != onOff;

            controller.setOnStatus(onOff);
            controller.setFlowErrorAlarm(Integer.parseInt(payloadB.substring(3, 4), 2));
            controller.setTimeoutAlarm(Integer.parseInt(payloadB.substring(4, 5), 2));
            controller.setHighVoltageAlarm(Integer.parseInt(payloadB.substring(5, 6), 2));
            controller.setLowVoltageAlarm(Integer.parseInt(payloadB.substring(6, 7), 2));

            if (valueChanged) {
                if (onOff == 1) {
                    controller.setLastMotorOnTime(LocalDateTime.now());
                } else {
                    controller.setLastMotorOffTime(LocalDateTime.now());
                }
            }
        } else if (commandId == 19) {
            controller.setOnStatus(Integer.parseInt(payloadB.substring(7, 8), 2));
            if (controller.getOnStatus() == 1) {
                controller.setLastMotorOnTime(LocalDateTime.now());
            } else {
                controller.setLastMotorOffTime(LocalDateTime.now());
            }

        } else if (commandId == 20) {
            tank.setNoSignalAlarm(Integer.parseInt(payloadA.substring(7, 8), 2));
        } else if (commandId == 51) {
            controller.setControllerPowerOn(Integer.parseInt(payloadB.substring(7, 8), 2));
            controller.setControllerPowerFailure(Integer.parseInt(payloadB.substring(6, 7), 2));
            controller.setRxUartPartialOrErraticData(Integer.parseInt(payloadB.substring(5, 6), 2));
            controller.setNoDataFromRxLastFiveMinutes(Integer.parseInt(payloadB.substring(4, 5), 2));
            controller.setRxAddressNotInitialised(Integer.parseInt(payloadB.substring(3, 4), 2));

        } else if (commandId == 58) {
            controller.setPhaseFailure(Integer.parseInt(payloadB.substring(7, 8), 2));
            controller.setLoadOrAmpError(Integer.parseInt(payloadB.substring(6, 7), 2));
        } else {
            System.out.println("SOMETHING SEEMS TO BE WRONG HERE!!!!! GO AWAY!!!!");
        }


    }

    @Override
    public void SaveTemperatureControllerData(Controller controller, int commandId, String payloadA, String payloadB) {

    }

    @Override
    public void SaveFlowMeterControllerData(Controller controller, int commandId, String payloadA, String payloadB) {

    }

    @Override
    public void SavePressureControllerData(Controller controller, int commandId, String payloadA, String payloadB) {

    }
}
