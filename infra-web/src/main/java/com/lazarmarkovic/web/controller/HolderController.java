package com.lazarmarkovic.web.controller;

import com.lazarmarkovic.domain.entity.Holder;
import com.lazarmarkovic.usecase.CreateHolderUseCase;
import com.lazarmarkovic.usecase.GetHolderByUuidUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/holder")
public class HolderController {

    private final CreateHolderUseCase createHolderUseCase;
    private final GetHolderByUuidUseCase getHolderByUuidUseCase;

    public HolderController(CreateHolderUseCase createHolderUseCase, GetHolderByUuidUseCase getHolderByUuidUseCase) {
        this.createHolderUseCase = createHolderUseCase;
        this.getHolderByUuidUseCase = getHolderByUuidUseCase;
    }

    @GetMapping("/{uuid}")
    Holder getHolderByUuid(@PathVariable UUID uuid) {
        return getHolderByUuidUseCase.invoke(uuid);
    }

    @PostMapping
    Holder createHolder(@RequestBody Holder request) {
        return createHolderUseCase.invoke(request);
    }
}
