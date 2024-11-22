package com.lazarmarkovic.persistence.gateway;

import com.lazarmarkovic.domain.entity.Device;
import com.lazarmarkovic.domain.gateway.IDeviceGateway;
import com.lazarmarkovic.persistence.dao.DeviceDao;
import com.lazarmarkovic.persistence.helper.Mappers;
import com.lazarmarkovic.persistence.repository.DeviceRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeviceGateway implements IDeviceGateway {

    private final DeviceRepository deviceRepository;

    public DeviceGateway(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Device findByUuid(UUID uuid) {
        return deviceRepository.findByUuid(uuid).map(DeviceDao::toDomain).orElse(null);
    }

    @Override
    public Device findBySerialNumber(String serialNumber) {
        return deviceRepository.findBySerialNumber(serialNumber).map(DeviceDao::toDomain).orElse(null);
    }

    @Override
    public Device save(Device device) {
        return deviceRepository.save(Mappers.toDeviceDao(device)).toDomain();
    }
}
