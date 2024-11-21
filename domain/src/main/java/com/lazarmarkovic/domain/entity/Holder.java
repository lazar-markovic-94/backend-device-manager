package com.lazarmarkovic.domain.entity;

import java.util.Date;
import java.util.UUID;

public record Holder(
        UUID uuid,
        String firstName,
        String lastName,
        String address,
        Date birthday
) {
}

