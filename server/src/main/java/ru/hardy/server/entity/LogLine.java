package ru.hardy.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LogLine {
    double x;
    double y;
    LocalDateTime registeredAt;
    int webDriverId;

}
