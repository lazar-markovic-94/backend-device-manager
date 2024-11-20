package com.lazarmarkovic.app;

import com.lazarmarkovic.WebConfig;
import com.lazarmarkovic.persistence.PersistenceConfig;
import com.lazarmarkovic.usecase.UseCaseConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(
    {
        WebConfig.class,
        PersistenceConfig.class,
        UseCaseConfig.class
    }
)
public class Wiring {
}
