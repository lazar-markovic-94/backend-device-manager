package com.lazarmarkovic.persistence.gateway;

import com.lazarmarkovic.domain.entity.HolderDevice;
import com.lazarmarkovic.domain.gateway.IHolderDeviceGateway;
import com.lazarmarkovic.persistence.dao.HolderDeviceDao;
import com.lazarmarkovic.persistence.repository.HolderDeviceRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.lazarmarkovic.persistence.helper.Mappers.toHolderDeviceDao;

@Component
public class HolderDeviceGateway implements IHolderDeviceGateway {

    private final HolderDeviceRepository holderDeviceRepository;

    public HolderDeviceGateway(HolderDeviceRepository holderDeviceRepository) {
        this.holderDeviceRepository = holderDeviceRepository;
    }

    @Override
    public HolderDevice save(HolderDevice holderDevice) {
        return holderDeviceRepository.save(toHolderDeviceDao(holderDevice)).toDomain();
    }

    @Override
    public List<HolderDevice> findByHolderUuid(UUID uuid) {
        return holderDeviceRepository.findByHolderUuid(uuid).stream().map(HolderDeviceDao::toDomain).toList();
    }

    @Override
    public HolderDevice findByDeviceUuid(UUID uuid) {
        return holderDeviceRepository.findByDeviceUuid(uuid).map(HolderDeviceDao::toDomain).orElse(null);
    }
}
