package org.example.service;

import org.example.model.PageEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PageEventConsumer {
    @KafkaListener(topics = "page-events", groupId = "group-1")
    public void consume(PageEvent pageEvent) {
        System.out.println("Consumed event: " + pageEvent);
    }
} 