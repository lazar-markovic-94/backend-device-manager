package com.lazarmarkovic.persistence.gateway;

import com.lazarmarkovic.domain.entity.Holder;
import com.lazarmarkovic.domain.gateway.IHolderGateway;
import com.lazarmarkovic.persistence.dao.HolderDao;
import com.lazarmarkovic.persistence.helper.Mappers;
import com.lazarmarkovic.persistence.repository.HolderRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class HolderGateway implements IHolderGateway {

    private final HolderRepository holderRepository;

    public HolderGateway(HolderRepository holderRepository) {
        this.holderRepository = holderRepository;
    }

    @Override
    public Holder findByUuid(UUID uuid) {
        return holderRepository.findByUuid(uuid).map(HolderDao::toDomain).orElse(null);
    }

    @Override
    public Holder save(Holder holder) {
        return holderRepository.save(Mappers.toHolderDao(holder)).toDomain();
    }
}
