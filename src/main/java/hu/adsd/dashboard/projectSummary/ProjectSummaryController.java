package hu.adsd.dashboard.projectSummary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProjectSummaryController {
    private final ProjectSummaryDataRepository projectSummaryDataRepository;

    public ProjectSummaryController(ProjectSummaryDataRepository projectSummaryDataRepository) {
        this.projectSummaryDataRepository = projectSummaryDataRepository;
    }

    @GetMapping("/projectsummary")
    public List<ProjectSummaryData> getProjectSummary(){
        return projectSummaryDataRepository.findAll();
    }


    @GetMapping("/updatePS")
    public void updateUser(ProjectSummaryData pd) {

        int newPoints=33;

        Optional<ProjectSummaryData> psmFromDb=projectSummaryDataRepository.findById(2);
        //ProjectSummaryData psmFromDb=projectSummaryDataRepository.findById(2);
        psmFromDb.ifPresent(projectSummaryData -> {

                    projectSummaryData.setStoryPoints(newPoints);

                    System.out.println("project summary data's name = " + projectSummaryData.getName() +
                            "items: " +
                            projectSummaryData.getItems() +
                            "story points"
                            + projectSummaryData.getStoryPoints()
                    );
                     projectSummaryDataRepository.save(projectSummaryData);

                }
        );
        //ProjectSummaryData psmFromDb=projectSummaryDataRepository.findById(2);

        //User userFromDb = userRepository.findById(u.getid());
        // crush the variables of the object found
        //userFromDb.setFirstname("john");
        //userFromDb.setLastname("dew");
        //userFromDb.setAge(16);
        //userRepository.save(userFromDb);
    }

}
