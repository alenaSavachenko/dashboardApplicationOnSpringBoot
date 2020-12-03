package hu.adsd.dashboard.clientScrumboard;


import hu.adsd.dashboard.task.TaskData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    //List<TaskData> findAllByDateBetweenOrderByDate(Date date, Date date2);
}
