package com.lazarmarkovic.usecase;

import com.lazarmarkovic.domain.entity.HolderDevice;

import java.util.UUID;

public interface IConnectHolderToDeviceUseCase {

    HolderDevice invoke(UUID holderUuid, UUID deviceUuid);
}
