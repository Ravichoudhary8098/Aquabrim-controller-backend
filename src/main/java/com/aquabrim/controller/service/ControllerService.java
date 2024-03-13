package com.aquabrim.controller.service;

import com.aquabrim.controller.entity.Controller;

import java.util.List;

public interface ControllerService {
    Controller findByDeviceId(String idno);

    List<String> getAllDeviceIds();

    void SaveWaterControllerData(Controller controller, int commandId, String payloadA, String payloadB);

    void SaveTemperatureControllerData(Controller controller, int commandId, String payloadA, String payloadB);

    void SaveFlowMeterControllerData(Controller controller, int commandId, String payloadA, String payloadB);

    void SavePressureControllerData(Controller controller, int commandId, String payloadA, String payloadB);
}
