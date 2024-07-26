package ru.hardy.server.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LogLineDto {
    private double x;
    private double y;
    private LocalDateTime registeredAt;
    private int webDriverId;
}
