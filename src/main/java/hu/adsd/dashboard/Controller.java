package hu.adsd.dashboard;

import hu.adsd.dashboard.clientScrumboard.ClientScrumboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    ClientScrumboard clientScrumboard;


    @GetMapping("/statisticsMethod")
    @ResponseBody
    public int getMethod( @RequestParam("project") String projectName, @RequestParam("task") String taskName)
    {
        // replace whitespace with %20, according to syntax of Rest Api Jira
        //for calls use pattern :  http://localhost:8080/statisticsMethod?project=testProject&task=Done

        String replacedTaskName=taskName.replace(" ", "%20");
        return clientScrumboard.getStatistics(projectName,replacedTaskName);
    }




}
