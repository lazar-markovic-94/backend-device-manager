package com.lazarmarkovic.usecase;

import com.lazarmarkovic.domain.entity.Device;

public interface ISaveDeviceUseCase {

    Device invoke(Device device);
}
