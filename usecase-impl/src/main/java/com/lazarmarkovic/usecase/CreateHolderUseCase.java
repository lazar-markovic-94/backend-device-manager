package com.lazarmarkovic.usecase;

import com.lazarmarkovic.domain.entity.Holder;
import com.lazarmarkovic.domain.exception.EntitySaveException;
import com.lazarmarkovic.persistence.gateway.HolderGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateHolderUseCase implements ICreateHolderUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CreateHolderUseCase.class);

    private final HolderGateway holderGateway;

    public CreateHolderUseCase(HolderGateway holderGateway) {
        this.holderGateway = holderGateway;
    }

    @Override
    public Holder invoke(Holder holder) {
        try {
            Holder savedHolder = holderGateway.save(holder);

            if (savedHolder == null) {
                throw new EntitySaveException("Failed to save holder with uuid: " + holder.uuid());
            }

            return savedHolder;
        } catch (Exception ex) {
            logger.error("Failed to save holder with uuid: " + holder.uuid(), ex);
            throw ex;
        }
    }
}
