package hu.adsd.dashboard.clientScrumboard;


import hu.adsd.dashboard.task.TaskDataRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClientScrumboard {




    public static void main(String[] args) {

        getAllIssues();
    }


public static int getStatistics (String projectName, String taskName )
{
    // to gerenate filter you need have project Name and task name

    String filter="status=%22"+taskName+"%22%20AND%20project=%22"+projectName+"%22";
    String query="https://andgreg.atlassian.net/rest/api/2/search?jql="+filter;
    // use  Unirest library to connect to Api, you need to add unirest dependency to your pom.xml

    kong.unirest.HttpResponse<JsonNode> response = Unirest.get(query)
            // email and token of Jira account
            .basicAuth("alenasavachenko3@gmail.com", "XaSvh24eI7ftgqS8gV0q978A")
            .header("Accept", "application/json")
            .asJson();

    //parse response
    JSONObject resObj = response.getBody().getObject();
    int total = resObj.getInt("total");
    //System.out.println("totaal task  : "+total);

    return total;


}


    public static  List<Task> getAllIssues  ( )
    {

        List<Task> allIssues=new ArrayList<Task>();

        String query="https://andgreg.atlassian.net/rest/api/2/search?";
        kong.unirest.HttpResponse<JsonNode> response = Unirest.get(query)
                // email and token of Jira account
                .basicAuth("alenasavachenko3@gmail.com", "XaSvh24eI7ftgqS8gV0q978A")
                .header("Accept", "application/json")
                .asJson();

        //parse response
        JSONObject resObj = response.getBody().getObject();
        JSONArray issues=resObj.getJSONArray("issues");
        //System.out.println("issues:"+issues);

        for(int i=0;i<issues.length();i++) {
           // JSONObject jsonObject1 = issues.getJSONObject(i);
            //System.out.println("json obj:"+jsonObject1);

            JSONObject jsonObjectIssue = issues.getJSONObject(i);
            System.out.println("issue object:"+issues.get(i));

            String issueKey = jsonObjectIssue.getString("key");
            System.out.println("key: "+issueKey);

            Task task=new Task();
            task.setTaskKey(issueKey);

            allIssues.add(task);

        }



        return allIssues;
    }
}
