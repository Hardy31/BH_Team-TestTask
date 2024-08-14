package ru.hardy.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class LogLineDTO {
    private double x;
    private double y;
    private LocalDateTime registeredAt;
    private int webDriverId;
}
