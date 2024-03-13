package com.aquabrim.controller.service.serviceImpl;

import com.aquabrim.controller.entity.Controller;
import com.aquabrim.controller.service.ControllerService;
import com.aquabrim.controller.service.ConvertAndSaveService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConvertAndSaveServiceImpl implements ConvertAndSaveService {
    @Autowired
    ControllerService controllerService;

    @Override
    public void convertAndSave(String deviceId, String inputData) {
        if (StringUtils.isNotBlank(deviceId) && StringUtils.isNotBlank(inputData)) {
            List<String> array = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                array.add(inputData.substring(i * 8, (i + 1) * 8));
            }

            Controller controller = controllerService.findByDeviceId(deviceId);
            if (controller == null) {
                // Handle case when controller is not found
                return;
            }

            int command_id = Integer.parseInt(array.get(3), 2);
            String  payloadA = array.get(4);
            String payloadB = array.get(5);

            int deviceType = controller.getDeviceType();
            switch (deviceType) {
                case 1:
                    // Water level controller
                    controllerService.SaveWaterControllerData(controller, command_id, payloadA, payloadB);
                    break;
                case 2:
                    // Temperature controller
                    controllerService.SaveTemperatureControllerData(controller, command_id, payloadA, payloadB);
                    break;
                case 3:
                    // Flow meter controller
                    controllerService.SaveFlowMeterControllerData(controller, command_id, payloadA, payloadB);
                    break;
                case 4:
                    // Pressure controller
                    controllerService.SavePressureControllerData(controller, command_id, payloadA, payloadB);
                    break;
                default:
                    // Handle unknown device type
                    break;
            }
        }

    }
}