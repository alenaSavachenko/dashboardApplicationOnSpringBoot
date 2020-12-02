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

    @GetMapping ("/saveAllIssuesInCustomerDb")
    @ResponseBody
    public String xxx()
    {

        List<Task> allIssues=client.getAllIssues();

        System.out.println("all issues:");

        for (Task var:allIssues)

            System.out.println(var.getTaskKey());
        return  "data saved!";

    }



}
