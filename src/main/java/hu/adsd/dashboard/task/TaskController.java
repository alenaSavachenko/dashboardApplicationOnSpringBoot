package hu.adsd.dashboard.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class TaskController {


    @Autowired
    TaskDataRepository taskDataRepository;

    @GetMapping("/taskdata")
    public List<TaskData> getTaskData() throws ParseException {

        TaskData taskData=new TaskData();

        taskData.setKey("Test-1");
        //taskData.setProject("JiraDashboard");
        taskData.setStoryPoint(101);
        taskData.setAssigned("AlenaS");
        taskData.setDescription("create new class");



        taskDataRepository.save(taskData);

        return taskDataRepository.findAll();
    }
}