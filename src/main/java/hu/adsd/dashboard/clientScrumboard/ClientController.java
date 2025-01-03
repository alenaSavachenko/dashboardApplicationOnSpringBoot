package hu.adsd.dashboard.clientScrumboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    ClientScrumboard client;
    @Autowired
    TaskRepository repository;

    @GetMapping ("/saveAllIssuesInCustomerDb")
    @ResponseBody
    public String saveMyIssuesToDb()
    {

        List<Task> allIssues=client.getAllIssues();

        System.out.println("all issues:");

        for (Task task:allIssues)
        {
            System.out.println(task.getTaskKey());
            repository.save(task);


        }


        return  "data saved!";

    }



}
