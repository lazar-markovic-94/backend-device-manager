package com.lazarmarkovic.persistence.repository;

import com.lazarmarkovic.persistence.dao.HolderDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HolderRepository extends CrudRepository<HolderDao, UUID> {

    Optional<HolderDao> findByUuid(UUID uuid);
}
