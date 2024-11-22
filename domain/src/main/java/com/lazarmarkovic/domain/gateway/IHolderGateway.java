package com.lazarmarkovic.domain.gateway;

import com.lazarmarkovic.domain.entity.Holder;

import java.util.UUID;

public interface IHolderGateway {

    Holder findByUuid(UUID uuid);

    Holder save(Holder holder);
}
