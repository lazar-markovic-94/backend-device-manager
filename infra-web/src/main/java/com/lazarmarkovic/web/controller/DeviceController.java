package com.lazarmarkovic.web.controller;

import com.lazarmarkovic.domain.entity.Device;
import com.lazarmarkovic.usecase.CreateDeviceUseCase;
import com.lazarmarkovic.usecase.GetDeviceBySerialNumberUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private final CreateDeviceUseCase saveDeviceUseCase;
    private final GetDeviceBySerialNumberUseCase getDeviceBySerialNumberUseCase;

    public DeviceController(
            CreateDeviceUseCase saveDeviceUseCase,
            GetDeviceBySerialNumberUseCase getDeviceBySerialNumberUseCase
    ) {
        this.saveDeviceUseCase = saveDeviceUseCase;
        this.getDeviceBySerialNumberUseCase = getDeviceBySerialNumberUseCase;
    }

    @PostMapping
    Device createDevice(@RequestBody Device request) {
        return saveDeviceUseCase.invoke(request);
    }

    @GetMapping("/{serialNumber}")
    Device getDeviceBySerialNumber(@PathVariable String serialNumber) {
        return getDeviceBySerialNumberUseCase.invoke(serialNumber);
    }
}
