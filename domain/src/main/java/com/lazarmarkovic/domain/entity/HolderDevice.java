package com.lazarmarkovic.domain.entity;

import java.util.UUID;

public record HolderDevice(
        UUID uuid,
        UUID holderUuid,
        UUID deviceUuid
) {
}
