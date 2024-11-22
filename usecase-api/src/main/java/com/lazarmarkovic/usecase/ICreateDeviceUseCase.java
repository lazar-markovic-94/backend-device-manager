package com.lazarmarkovic.usecase;

import com.lazarmarkovic.domain.entity.Device;

public interface ICreateDeviceUseCase {

    Device invoke(Device device);
}
