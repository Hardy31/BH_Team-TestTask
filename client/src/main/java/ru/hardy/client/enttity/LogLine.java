package ru.hardy.client.enttity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "log_line")
@Getter
@ToString
public class LogLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    double x;
    double y;
    LocalDateTime registeredAt;
    int webDriverId;



}
