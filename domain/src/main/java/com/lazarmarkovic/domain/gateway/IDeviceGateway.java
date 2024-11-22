package com.lazarmarkovic.domain.gateway;

import com.lazarmarkovic.domain.entity.Device;

import java.util.UUID;

public interface IDeviceGateway {

    Device findByUuid(UUID uuid);

    Device findBySerialNumber(String serialNumber);

    Device save(Device device);
}