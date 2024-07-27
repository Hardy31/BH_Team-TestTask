package ru.hardy.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.client.enttity.LogLine;

//Отсортированные  данные можно получить 3мя способами.

// 1) использовать встроенный механизм построения запроса по типу:
//    List<LogLine> findAllOrderByregisteredAtOrderByxOrderByy();

// 2) Используя аннатацию Query
//    @Query(value = "SELECT * FROM LogLine as ll ORDER BY ll.registeredAt, ll.x, ll.y", nativeQuery = true)
//    ArrayList<LogLine> findAllSortetByTXY();

// 3) сначало получить все данные и через стрим их отсортировать.
////        ArrayList<LogLine> logLists = LogLineRepositiry.findAll();
////        ArrayList<LogLine> sortedLogs = logLists.straem().sorted(
////            Comparator.comparing(LogLine::getRegisteredAt).thenComparing(LogLine::getX).thenComparing(LogLine::getY)
////        ).collect(Collectors.toList());

@Repository
public interface LogLineRepositiry extends JpaRepository<LogLine, Long> {
}







