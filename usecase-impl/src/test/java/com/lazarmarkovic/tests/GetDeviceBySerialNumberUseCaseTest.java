package com.lazarmarkovic.tests;

import com.lazarmarkovic.domain.entity.Device;
import com.lazarmarkovic.domain.exception.EntityNotFoundException;
import com.lazarmarkovic.persistence.gateway.DeviceGateway;
import com.lazarmarkovic.usecase.GetDeviceBySerialNumberUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetDeviceBySerialNumberUseCaseTest {

    @Mock
    private DeviceGateway deviceGateway;

    @InjectMocks
    private GetDeviceBySerialNumberUseCase getDeviceBySerialNumberUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private final UUID uuid = UUID.randomUUID();
    private final String serialNumber = "TestSerial123";
    private final Device device = new Device(
            uuid,
            serialNumber,
            "43132142131",
            "Base"
    );

    @Test
    void testInvoke_DeviceFound_ReturnsDevice() {

        when(deviceGateway.findBySerialNumber(serialNumber)).thenReturn(device);

        Device result = getDeviceBySerialNumberUseCase.invoke(serialNumber);

        assertNotNull(result);
        assertEquals(device, result);
        verify(deviceGateway, times(1)).findBySerialNumber(serialNumber);
    }

    @Test
    void testInvoke_DeviceNotFound_ThrowsEntityNotFoundException() {

        String serialNumber = "12345";
        when(deviceGateway.findBySerialNumber(serialNumber)).thenReturn(null);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> getDeviceBySerialNumberUseCase.invoke(serialNumber));

        assertEquals("Device with serial number: 12345 not found.", exception.getMessage());
        verify(deviceGateway, times(1)).findBySerialNumber(serialNumber);
    }

    @Test
    void testInvoke_ExceptionInGateway_ThrowsSameException() {

        String serialNumber = "12345";
        RuntimeException runtimeException = new RuntimeException("Database error");
        when(deviceGateway.findBySerialNumber(serialNumber)).thenThrow(runtimeException);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> getDeviceBySerialNumberUseCase.invoke(serialNumber));

        assertEquals("Database error", exception.getMessage());
        verify(deviceGateway, times(1)).findBySerialNumber(serialNumber);
    }
}

