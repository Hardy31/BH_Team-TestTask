package ru.hardy.server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogRow {
    double x;
    double y;
    LocalDateTime registeredAt;
    int webDriverId;
}
