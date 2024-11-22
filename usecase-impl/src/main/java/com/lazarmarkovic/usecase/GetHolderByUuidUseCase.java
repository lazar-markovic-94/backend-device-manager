package com.lazarmarkovic.usecase;

import com.lazarmarkovic.domain.entity.Holder;
import com.lazarmarkovic.domain.exception.EntityNotFoundException;
import com.lazarmarkovic.persistence.gateway.HolderGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetHolderByUuidUseCase implements IGetHolderByUuidUseCase {

    private static final Logger logger = LoggerFactory.getLogger(GetHolderByUuidUseCase.class);

    private final HolderGateway holderGateway;

    public GetHolderByUuidUseCase(HolderGateway holderGateway) {
        this.holderGateway = holderGateway;
    }

    @Override
    public Holder invoke(UUID uuid) {

        try {
            Holder holder = holderGateway.findByUuid(uuid);

            if (holder == null) {
                throw new EntityNotFoundException("Holder with uuid: " + uuid + "does not exist.");
            }

            return holder;
        } catch (Exception ex) {
            logger.error("Failed to fetch holder with uuid: " + uuid, ex);
            throw ex;
        }
    }
}
