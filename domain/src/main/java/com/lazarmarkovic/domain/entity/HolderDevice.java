package com.lazarmarkovic.domain.entity;

import java.util.UUID;

public record HolderDevice(
        UUID uuid,
        Holder holder,
        Device device
) {
}
