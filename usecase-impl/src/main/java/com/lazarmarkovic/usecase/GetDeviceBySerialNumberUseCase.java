package com.lazarmarkovic.usecase;

import com.lazarmarkovic.domain.entity.Device;
import com.lazarmarkovic.domain.exception.EntityNotFoundException;
import com.lazarmarkovic.persistence.gateway.DeviceGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GetDeviceBySerialNumberUseCase implements IGetDeviceBySerialNumberUseCase {
    private static final Logger logger = LoggerFactory.getLogger(GetDeviceBySerialNumberUseCase.class);

    private final DeviceGateway deviceGateway;

    public GetDeviceBySerialNumberUseCase(DeviceGateway deviceGateway) {
        this.deviceGateway = deviceGateway;
    }

    @Override
    public Device invoke(String serialNumber) {

         try {

             Device device = deviceGateway.findBySerialNumber(serialNumber);

             if(device == null) {
                 throw new EntityNotFoundException("Device with serial number: " + serialNumber + " not found.");
             }

             return device;
         } catch (Exception ex) {
             logger.error("Error while fetching device with serial number: " + serialNumber, ex);
             throw ex;
         }
    }
}
