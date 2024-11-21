package com.lazarmarkovic.persistence;

import com.lazarmarkovic.domain.entity.Holder;
import com.lazarmarkovic.persistence.dao.HolderDao;
import com.lazarmarkovic.persistence.gateway.HolderGateway;
import com.lazarmarkovic.persistence.repository.HolderRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class HolderGatewayTests {

    @Mock
    private HolderRepository holderRepository;

    @InjectMocks
    private HolderGateway holderGateway;

    private final UUID uuid = UUID.randomUUID();

    private final HolderDao holderDao = new HolderDao(
            uuid,
            "John",
            "Doe",
            "123 Main St",
            new java.util.Date()
    );

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByUuid() {

        when(holderRepository.findByUuid(uuid)).thenReturn(holderDao);

        Holder holder = holderGateway.findByUuid(uuid);

        assertNotNull(holder);
        assertEquals(holderDao.getUuid(), holder.uuid());
        assertEquals(holderDao.getFirstName(), holder.firstName());
        assertEquals(holderDao.getLastName(), holder.lastName());
        assertEquals(holderDao.getAddress(), holder.address());
        assertEquals(holderDao.getBirthday(), holder.birthday());
    }
}

