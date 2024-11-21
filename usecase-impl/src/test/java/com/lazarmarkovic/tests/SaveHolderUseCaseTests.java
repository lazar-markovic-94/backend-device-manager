package com.lazarmarkovic.tests;

import com.lazarmarkovic.domain.entity.Holder;
import com.lazarmarkovic.domain.exception.EntitySaveException;
import com.lazarmarkovic.persistence.gateway.HolderGateway;
import com.lazarmarkovic.usecase.SaveHolderUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaveHolderUseCaseTests {

    @Mock
    private HolderGateway holderGateway;

    @InjectMocks
    private SaveHolderUseCase saveHolderUseCase;

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
    void testInvoke_SuccessfulSave_ReturnsSavedHolder() {

        when(holderGateway.save(holder)).thenReturn(holder);

        Holder result = saveHolderUseCase.invoke(holder);

        assertNotNull(result);
        assertEquals(holder, result);
        verify(holderGateway, times(1)).save(holder);
    }

    @Test
    void testInvoke_FailedSave_ThrowsEntitySaveException() {

        when(holderGateway.save(holder)).thenReturn(null);

        EntitySaveException exception = assertThrows(EntitySaveException.class,
                () -> saveHolderUseCase.invoke(holder));

        assertEquals("Failed to save holder with uuid: " + holder.uuid(), exception.getMessage());
        verify(holderGateway, times(1)).save(holder);
    }

    @Test
    void testInvoke_ExceptionDuringSave_ThrowsSameException() {

        RuntimeException runtimeException = new RuntimeException("Database error");
        when(holderGateway.save(holder)).thenThrow(runtimeException);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> saveHolderUseCase.invoke(holder));

        assertEquals("Database error", exception.getMessage());
        verify(holderGateway, times(1)).save(holder);
    }
}

