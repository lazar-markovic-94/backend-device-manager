package com.lazarmarkovic.persistence.repository;

import com.lazarmarkovic.domain.entity.HolderDevice;
import com.lazarmarkovic.persistence.dao.HolderDeviceDao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface HolderDeviceRepository extends CrudRepository<HolderDeviceDao, UUID> {

    List<HolderDeviceDao> findByHolder_Uuid(UUID uuid);

    HolderDeviceDao findByDevice_Uuid(UUID uuid);
}
