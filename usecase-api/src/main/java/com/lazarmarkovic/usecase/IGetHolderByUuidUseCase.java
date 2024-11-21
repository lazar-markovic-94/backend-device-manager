package com.lazarmarkovic.usecase;

import com.lazarmarkovic.domain.entity.Holder;

import java.util.UUID;

public interface IGetHolderByUuidUseCase {

    Holder invoke(UUID uuid);
}
