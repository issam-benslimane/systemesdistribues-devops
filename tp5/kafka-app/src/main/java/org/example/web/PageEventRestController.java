package org.example.web;

import org.example.model.PageEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class PageEventRestController {
    private KafkaTemplate<String, PageEvent> kafkaTemplate;

    public PageEventRestController(KafkaTemplate<String, PageEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/send/{topic}/{name}")
    public String send(@PathVariable String topic, @PathVariable String name) {
        PageEvent pageEvent = new PageEvent(name, "user" + new Random().nextInt(1000), 
            new Random().nextInt(1000), new Date().getTime());
        kafkaTemplate.send(topic, pageEvent);
        return "Message sent to " + topic;
    }
} 