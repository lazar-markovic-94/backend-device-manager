package com.lazarmarkovic.usecase;

import com.lazarmarkovic.domain.entity.HolderDevice;
import com.lazarmarkovic.domain.exception.EntityNotFoundException;
import com.lazarmarkovic.domain.exception.EntitySaveException;
import com.lazarmarkovic.persistence.gateway.DeviceGateway;
import com.lazarmarkovic.persistence.gateway.HolderDeviceGateway;
import com.lazarmarkovic.persistence.gateway.HolderGateway;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ConnectHolderToDeviceUseCase implements IConnectHolderToDeviceUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ConnectHolderToDeviceUseCase.class);

    private final HolderDeviceGateway holderDeviceGateway;
    private final HolderGateway holderGateway;
    private final DeviceGateway deviceGateway;

    public ConnectHolderToDeviceUseCase(HolderDeviceGateway holderDeviceGateway, HolderGateway holderGateway, DeviceGateway deviceGateway) {
        this.holderDeviceGateway = holderDeviceGateway;
        this.holderGateway = holderGateway;
        this.deviceGateway = deviceGateway;
    }

    @Override
    @Transactional
    public HolderDevice invoke(UUID holderUuid, UUID deviceUuid) {

        try {

            if (holderGateway.findByUuid(holderUuid) == null) {
                throw new EntityNotFoundException("Holder: " + holderUuid + "does not exist.");
            }

            if (deviceGateway.findByUuid(deviceUuid) == null) {
                throw new EntityNotFoundException("Device: " + deviceUuid + "does not exist.");
            }

            HolderDevice connectedHolderDevice = holderDeviceGateway.save(new HolderDevice(null, holderUuid, deviceUuid));

            if (connectedHolderDevice == null) {
                throw new EntitySaveException("Failed to connect holder " + holderUuid + "to device" + deviceUuid);
            }

            return connectedHolderDevice;
        } catch (Exception ex) {
            logger.error("Failed to connect device to holder", ex);
            throw ex;
        }
    }
}
