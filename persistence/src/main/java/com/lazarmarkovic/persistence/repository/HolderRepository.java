package com.lazarmarkovic.persistence.repository;

import com.lazarmarkovic.persistence.dao.HolderDao;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface HolderRepository extends CrudRepository<HolderDao, UUID> {

    HolderDao findByUuid(UUID uuid);
}
