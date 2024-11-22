package com.lazarmarkovic.usecase;

import com.lazarmarkovic.domain.entity.Device;
import com.lazarmarkovic.domain.exception.EntitySaveException;
import com.lazarmarkovic.persistence.gateway.DeviceGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateDeviceUseCase implements ICreateDeviceUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CreateDeviceUseCase.class);

    private final DeviceGateway deviceGateway;

    public CreateDeviceUseCase(DeviceGateway deviceGateway) {
        this.deviceGateway = deviceGateway;
    }

    @Override
    public Device invoke(Device device) {

        try {

            Device existingDevice = deviceGateway.findBySerialNumber(device.serialNumber());

            if (existingDevice != null) {
                throw new EntitySaveException("Device with serial number: " + device.serialNumber() + " already exists.");
            }

            Device savedDevice = deviceGateway.save(device);

            if (savedDevice == null) {
                throw new EntitySaveException("Failed to save the device with serial number: " + device.serialNumber());
            }

            return savedDevice;

        } catch (EntitySaveException ex) {
            logger.error("Device creation failed: {}", ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error("Unexpected error occurred while creating the device: {}", device.serialNumber(), ex);
            throw new RuntimeException("An unexpected error occurred while creating the device.");
        }
    }
}
