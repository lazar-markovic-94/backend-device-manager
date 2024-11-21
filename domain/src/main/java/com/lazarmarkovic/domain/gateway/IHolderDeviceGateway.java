package com.lazarmarkovic.domain.gateway;

import com.lazarmarkovic.domain.entity.HolderDevice;

import java.util.List;
import java.util.UUID;

public interface IHolderDeviceGateway {

    HolderDevice save(HolderDevice holderDevice);

    List<HolderDevice> findByHolderUuid(UUID uuid);

    HolderDevice findByDeviceUuid(UUID uuid);
}
