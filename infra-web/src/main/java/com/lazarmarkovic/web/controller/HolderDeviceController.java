package com.lazarmarkovic.web.controller;

import com.lazarmarkovic.domain.entity.HolderDevice;
import com.lazarmarkovic.usecase.ConnectHolderToDeviceUseCase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/holder-device")
public class HolderDeviceController {

    private final ConnectHolderToDeviceUseCase connectHolderToDeviceUseCase;

    public HolderDeviceController(ConnectHolderToDeviceUseCase connectHolderToDeviceUseCase) {
        this.connectHolderToDeviceUseCase = connectHolderToDeviceUseCase;
    }

    @PostMapping("/connect/{holderUuid}/{deviceUuid}")
    HolderDevice connectHolderToDevice(@PathVariable UUID holderUuid, @PathVariable UUID deviceUuid) {
        return connectHolderToDeviceUseCase.invoke(holderUuid, deviceUuid);
    }
}
