package com.lazarmarkovic.tests;

import com.lazarmarkovic.domain.entity.Device;
import com.lazarmarkovic.domain.exception.EntitySaveException;
import com.lazarmarkovic.persistence.gateway.DeviceGateway;
import com.lazarmarkovic.usecase.CreateDeviceUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateDeviceUseCaseTests {

    @Mock
    private DeviceGateway deviceGateway;

    @InjectMocks
    private CreateDeviceUseCase saveDeviceUseCase;

    private final UUID uuid = UUID.randomUUID();
    private final String serialNumber = "TestSerial123";
    private final Device device = new Device(
            uuid,
            serialNumber,
            "43132142131",
            "Base"
    );

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInvoke_DeviceSaved_ReturnsSavedDevice() {
        
        when(deviceGateway.save(device)).thenReturn(device);

        Device result = saveDeviceUseCase.invoke(device);
        
        assertNotNull(result);
        assertEquals(device, result);
        verify(deviceGateway, times(1)).save(device);
    }

    @Test
    void testInvoke_DeviceSaveFailed_ThrowsEntitySaveException() {

        when(deviceGateway.save(device)).thenReturn(null);

        EntitySaveException exception = assertThrows(EntitySaveException.class,
                () -> saveDeviceUseCase.invoke(device));

        assertEquals("Failed to save the device with serial number: " + device.serialNumber(), exception.getMessage());
        verify(deviceGateway, times(1)).save(device);
    }
}

