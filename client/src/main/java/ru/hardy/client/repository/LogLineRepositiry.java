package ru.hardy.client.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.client.enttity.LogLine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface LogLineRepositiry extends JpaRepository<LogLine, Long> {

//    List<LogLine> findAllOrderByregisteredAtOrderByxOrderByy();


//    ИЛИ отсортировать через
//    @Query(
//            value = "SELECT * FROM LogLine as ll ORDER BY ll.registeredAt, ll.x, ll.y",
//            nativeQuery = true)
//    ArrayList<LogLine> findAllSortetByTXY();


//      ИЛИ отсортировать через Stream()
//        ArrayList<LogLine> logLists = LogLineRepositiry.findAll();
//        ArrayList<LogLine> sortedLogs = logLists.straem().sorted(
//            Comparator.comparing(LogLine::getRegisteredAt).thenComparing(LogLine::getX).thenComparing(LogLine::getY)
//        ).collect(Collectors.toList());

}







