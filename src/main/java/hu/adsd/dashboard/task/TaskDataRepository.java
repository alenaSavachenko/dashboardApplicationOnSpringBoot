package hu.adsd.dashboard.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TaskDataRepository extends JpaRepository<TaskData, Integer> {

    //List<TaskData> findAllByDateBetweenOrderByDate(Date date, Date date2);
}
