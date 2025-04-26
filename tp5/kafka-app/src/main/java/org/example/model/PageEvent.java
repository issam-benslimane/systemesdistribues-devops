package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageEvent {
    private String name;
    private String user;
    private long duration;
    private long timestamp;
} 