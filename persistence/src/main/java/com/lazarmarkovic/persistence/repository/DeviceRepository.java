package com.lazarmarkovic.persistence.repository;

import com.lazarmarkovic.persistence.dao.DeviceDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceRepository extends CrudRepository<DeviceDao, UUID> {

    DeviceDao findByUuid(UUID uuid);

    DeviceDao findBySerialNumber(String serialNumber);
}
