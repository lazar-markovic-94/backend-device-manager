package com.lazarmarkovic.tests;

import com.lazarmarkovic.domain.entity.Device;
import com.lazarmarkovic.domain.entity.Holder;
import com.lazarmarkovic.domain.entity.HolderDevice;
import com.lazarmarkovic.domain.exception.EntityNotFoundException;
import com.lazarmarkovic.domain.exception.EntitySaveException;
import com.lazarmarkovic.persistence.gateway.DeviceGateway;
import com.lazarmarkovic.persistence.gateway.HolderDeviceGateway;
import com.lazarmarkovic.persistence.gateway.HolderGateway;
import com.lazarmarkovic.usecase.ConnectHolderToDeviceUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConnectHolderToDeviceUseCaseTest {

    @Mock
    private HolderDeviceGateway holderDeviceGateway;

    @Mock
    private HolderGateway holderGateway;

    @Mock
    private DeviceGateway deviceGateway;

    @InjectMocks
    private ConnectHolderToDeviceUseCase useCase;

    private UUID holderUuid = UUID.randomUUID();
    private UUID deviceUuid = UUID.randomUUID();

    private final Device device = new Device(
            deviceUuid,
            "test",
            "43132142131",
            "Base"
    );

    private final Holder holder = new Holder(
            holderUuid,
            "John",
            "Doe",
            "123 Main St",
            new java.util.Date()
    );

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void invoke_Success() {

        when(holderGateway.findByUuid(holderUuid)).thenReturn(holder);
        when(deviceGateway.findByUuid(deviceUuid)).thenReturn(device);
        HolderDevice expected = new HolderDevice(UUID.randomUUID(), holderUuid, deviceUuid);
        when(holderDeviceGateway.save(any(HolderDevice.class))).thenReturn(expected);
        
        HolderDevice result = useCase.invoke(holderUuid, deviceUuid);
        
        assertNotNull(result);
        assertEquals(holderUuid, result.holderUuid());
        assertEquals(deviceUuid, result.deviceUuid());
        verify(holderGateway).findByUuid(holderUuid);
        verify(deviceGateway).findByUuid(deviceUuid);
        verify(holderDeviceGateway).save(any(HolderDevice.class));
    }

    @Test
    void invoke_HolderNotFound_ThrowsEntityNotFoundException() {

        when(holderGateway.findByUuid(holderUuid)).thenReturn(null);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> useCase.invoke(holderUuid, deviceUuid));

        assertEquals("Holder: " + holderUuid + "does not exist.", exception.getMessage());
        verify(holderGateway).findByUuid(holderUuid);
        verify(deviceGateway, never()).findByUuid(deviceUuid);
        verify(holderDeviceGateway, never()).save(any(HolderDevice.class));
    }

    @Test
    void invoke_DeviceNotFound_ThrowsEntityNotFoundException() {
        
        when(holderGateway.findByUuid(holderUuid)).thenReturn(holder);
        when(deviceGateway.findByUuid(deviceUuid)).thenReturn(null);

        
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> useCase.invoke(holderUuid, deviceUuid));

        assertEquals("Device: " + deviceUuid + "does not exist.", exception.getMessage());
        verify(holderGateway).findByUuid(holderUuid);
        verify(deviceGateway).findByUuid(deviceUuid);
        verify(holderDeviceGateway, never()).save(any(HolderDevice.class));
    }

    @Test
    void invoke_SaveFails_ThrowsEntitySaveException() {
        
        when(holderGateway.findByUuid(holderUuid)).thenReturn(holder);
        when(deviceGateway.findByUuid(deviceUuid)).thenReturn(device);
        when(holderDeviceGateway.save(any(HolderDevice.class))).thenReturn(null);
        
        EntitySaveException exception = assertThrows(EntitySaveException.class,
                () -> useCase.invoke(holderUuid, deviceUuid));

        assertEquals("Failed to connect holder " + holderUuid + "to device" + deviceUuid, exception.getMessage());
        verify(holderGateway).findByUuid(holderUuid);
        verify(deviceGateway).findByUuid(deviceUuid);
        verify(holderDeviceGateway).save(any(HolderDevice.class));
    }

    @Test
    void invoke_ExceptionLoggedAndThrown() {
        
        when(holderGateway.findByUuid(holderUuid)).thenThrow(new RuntimeException("Unexpected error"));
        
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> useCase.invoke(holderUuid, deviceUuid));

        assertEquals("Unexpected error", exception.getMessage());
        verify(holderGateway).findByUuid(holderUuid);
        verify(deviceGateway, never()).findByUuid(deviceUuid);
        verify(holderDeviceGateway, never()).save(any(HolderDevice.class));
    }
}

