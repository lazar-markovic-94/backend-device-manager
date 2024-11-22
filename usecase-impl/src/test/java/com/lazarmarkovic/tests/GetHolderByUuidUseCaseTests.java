package com.lazarmarkovic.tests;

import com.lazarmarkovic.domain.entity.Holder;
import com.lazarmarkovic.domain.exception.EntityNotFoundException;
import com.lazarmarkovic.persistence.gateway.HolderGateway;
import com.lazarmarkovic.usecase.GetHolderByUuidUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetHolderByUuidUseCaseTests {

    @Mock
    private HolderGateway holderGateway;

    @InjectMocks
    private GetHolderByUuidUseCase getHolderByUuidUseCase;

    private final UUID uuid = UUID.randomUUID();

    private final Holder holder = new Holder(
            uuid,
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
    void testInvoke_HolderFound_ReturnsHolder() {

        when(holderGateway.findByUuid(uuid)).thenReturn(holder);

        Holder result = getHolderByUuidUseCase.invoke(uuid);

        assertNotNull(result);
        assertEquals(holder, result);
        verify(holderGateway, times(1)).findByUuid(uuid);
    }

    @Test
    void testInvoke_HolderNotFound_ThrowsEntityNotFoundException() {

        when(holderGateway.findByUuid(uuid)).thenReturn(null);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> getHolderByUuidUseCase.invoke(uuid));

        assertEquals("Holder with uuid: " + uuid + "does not exist.", exception.getMessage());
        verify(holderGateway, times(1)).findByUuid(uuid);
    }

    @Test
    void testInvoke_ExceptionInGateway_ThrowsSameException() {

        RuntimeException runtimeException = new RuntimeException("Database error");
        when(holderGateway.findByUuid(uuid)).thenThrow(runtimeException);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> getHolderByUuidUseCase.invoke(uuid));

        assertEquals("Database error", exception.getMessage());
        verify(holderGateway, times(1)).findByUuid(uuid);
    }
}

