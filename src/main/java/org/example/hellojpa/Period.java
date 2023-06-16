package org.example.hellojpa;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Period {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
