package com.lazarmarkovic.persistence;

import com.lazarmarkovic.domain.entity.HolderDevice;
import com.lazarmarkovic.persistence.dao.HolderDeviceDao;
import com.lazarmarkovic.persistence.gateway.HolderDeviceGateway;
import com.lazarmarkovic.persistence.repository.HolderDeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.lazarmarkovic.persistence.helper.Mappers.toHolderDeviceDao;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HolderDeviceGatewayTests {

    @Mock
    private HolderDeviceRepository holderDeviceRepository;

    @InjectMocks
    private HolderDeviceGateway holderDeviceGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private UUID uuid = UUID.randomUUID();
    private UUID holderUuid = UUID.randomUUID();
    private UUID deviceUuid = UUID.randomUUID();
    private HolderDevice holderDevice = new HolderDevice(uuid, holderUuid, deviceUuid);
    private HolderDeviceDao holderDeviceDao = toHolderDeviceDao(holderDevice);

    @Test
    void testSave_ShouldSaveAndReturnHolderDevice() {

        when(holderDeviceRepository.save(holderDeviceDao)).thenReturn(holderDeviceDao);

        HolderDevice result = holderDeviceGateway.save(holderDevice);

        assertNotNull(result);
        assertEquals(uuid, result.uuid());
        verify(holderDeviceRepository, times(1)).save(holderDeviceDao);
    }

    @Test
    void testFindByHolderUuid_ShouldReturnListOfHolderDevices() {

        List<HolderDeviceDao> holderDeviceDaos = List.of(
                holderDeviceDao
        );

        when(holderDeviceRepository.findByHolderUuid(holderUuid)).thenReturn(holderDeviceDaos);

        List<HolderDevice> result = holderDeviceGateway.findByHolderUuid(holderUuid);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(holderUuid, result.get(0).holderUuid());
        verify(holderDeviceRepository, times(1)).findByHolderUuid(holderUuid);
    }

    @Test
    void testFindByDeviceUuid_ShouldReturnHolderDeviceIfFound() {

        when(holderDeviceRepository.findByDeviceUuid(deviceUuid)).thenReturn(Optional.of(holderDeviceDao));

        HolderDevice result = holderDeviceGateway.findByDeviceUuid(deviceUuid);

        assertNotNull(result);
        assertEquals(deviceUuid, result.deviceUuid());
        verify(holderDeviceRepository, times(1)).findByDeviceUuid(deviceUuid);
    }

    @Test
    void testFindByDeviceUuid_ShouldReturnNullIfNotFound() {

        UUID deviceUuid = UUID.randomUUID();
        when(holderDeviceRepository.findByDeviceUuid(deviceUuid)).thenReturn(Optional.empty());

        HolderDevice result = holderDeviceGateway.findByDeviceUuid(deviceUuid);

        assertNull(result);
        verify(holderDeviceRepository, times(1)).findByDeviceUuid(deviceUuid);
    }
}

