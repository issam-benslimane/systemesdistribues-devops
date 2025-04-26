package org.example.service;

import org.example.model.PageEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.function.Supplier;

@Service
public class PageEventSupplier {
    @Bean
    public Supplier<PageEvent> pageEventSupplier() {
        return () -> new PageEvent(
            "page" + new Random().nextInt(10),
            "user" + new Random().nextInt(100),
            new Random().nextInt(1000),
            new Date().getTime()
        );
    }
} 