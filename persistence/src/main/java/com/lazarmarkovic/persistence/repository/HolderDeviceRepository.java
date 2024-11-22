package com.lazarmarkovic.persistence.repository;

import com.lazarmarkovic.persistence.dao.HolderDeviceDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HolderDeviceRepository extends CrudRepository<HolderDeviceDao, UUID> {

    List<HolderDeviceDao> findByHolderUuid(UUID uuid);

    Optional<HolderDeviceDao> findByDeviceUuid(UUID uuid);
}
