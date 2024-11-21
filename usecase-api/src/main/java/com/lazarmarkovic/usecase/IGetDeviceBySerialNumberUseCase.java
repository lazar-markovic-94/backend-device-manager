package com.lazarmarkovic.usecase;

import com.lazarmarkovic.domain.entity.Device;

public interface IGetDeviceBySerialNumberUseCase {

    Device invoke(String serialNumber);
}
