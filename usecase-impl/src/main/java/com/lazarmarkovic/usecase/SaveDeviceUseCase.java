package com.lazarmarkovic.usecase;

import com.lazarmarkovic.domain.entity.Device;
import com.lazarmarkovic.domain.exception.EntitySaveException;
import com.lazarmarkovic.persistence.gateway.DeviceGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SaveDeviceUseCase implements ISaveDeviceUseCase {

    private static final Logger logger = LoggerFactory.getLogger(SaveDeviceUseCase.class);

    private final DeviceGateway deviceGateway;

    public SaveDeviceUseCase(DeviceGateway deviceGateway) {
        this.deviceGateway = deviceGateway;
    }

    @Override
    public Device invoke(Device device) {

        try {

            Device savedDevice = deviceGateway.save(device);

            if(savedDevice == null) {
                throw new EntitySaveException("Insertion failed for device with a serial number: " + device.serialNumber());
            }

            return savedDevice;
        } catch (Exception ex) {
            logger.error("Device insertion failed for device with serial number: " + device.serialNumber(), ex);
            throw ex;
        }
    }
}
