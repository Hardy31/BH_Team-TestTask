package ru.hardy.client.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hardy.client.enttity.LogLine;

public interface LogLineRepositiry extends JpaRepository<LogLine, Long> {
}
