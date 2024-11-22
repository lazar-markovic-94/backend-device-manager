package com.lazarmarkovic.persistence;

import com.lazarmarkovic.domain.entity.Device;
import com.lazarmarkovic.persistence.dao.DeviceDao;
import com.lazarmarkovic.persistence.gateway.DeviceGateway;
import com.lazarmarkovic.persistence.repository.DeviceRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class DeviceGatewayTests {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceGateway deviceGateway;

    private final UUID uuid = UUID.randomUUID();
    private final String serialNumber = "TestSerial123";
    private final DeviceDao deviceDao = new DeviceDao(
            uuid,
            serialNumber,
            "43132142131", // phoneNumber
            "Base"         // model
    );

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByUuid() {

        when(deviceRepository.findByUuid(uuid)).thenReturn(Optional.of(deviceDao));

        Device device = deviceGateway.findByUuid(uuid);

        assertNotNull(device);
        assertEquals(deviceDao.getUuid(), device.uuid());
        assertEquals(deviceDao.getSerialNumber(), device.serialNumber());
    }

    @Test
    public void testFindBySerialNumber() {

        when(deviceRepository.findBySerialNumber(serialNumber)).thenReturn(Optional.of(deviceDao));

        Device device = deviceGateway.findBySerialNumber(serialNumber);

        assertNotNull(device);
        assertEquals(deviceDao.getSerialNumber(), device.serialNumber());
        assertEquals(deviceDao.getPhoneNumber(), device.phoneNumber());
    }
}
