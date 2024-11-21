package com.lazarmarkovic.usecase;

import com.lazarmarkovic.domain.entity.Holder;

public interface ISaveHolderUseCase {

    Holder invoke(Holder holder);
}
