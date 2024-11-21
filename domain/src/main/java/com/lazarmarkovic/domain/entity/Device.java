package com.lazarmarkovic.domain.entity;

import java.util.UUID;

public record Device(
        UUID uuid,
        String serialNumber,
        String phoneNumber,
        String model
) {
}
