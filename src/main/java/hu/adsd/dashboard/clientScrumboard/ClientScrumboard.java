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
           // System.out.println("issue object:"+issues.get(i));

            String issueKey = jsonObjectIssue.getString("key");
            System.out.println("key: "+issueKey);


            JSONObject fieldsObject = jsonObjectIssue.getJSONObject("fields");

            System.out.println("fields:");
            System.out.println(fieldsObject);
            // get categroy change date
            String  statuscategorychangedate= fieldsObject.getString("statuscategorychangedate");
            System.out.println("statuscategorychangedate");
            System.out.println(statuscategorychangedate);

            JSONObject project = fieldsObject.getJSONObject("project");
            System.out.println("project: "+project);
            String projectKey=fieldsObject.getJSONObject("project").getString("key");
            System.out.println(" p key:"+ projectKey);
            String projectName=fieldsObject.getJSONObject("project").getString("name");
            System.out.println(" p name:"+ projectName);

            JSONObject status = fieldsObject.getJSONObject("status");
            System.out.println("status: "+status);
           // String desc= fieldsObject.getString("description");
           // System.out.println("desc:"+desc);

            //JSONObject.NULL
            if (fieldsObject.isNull("description"))
            {
                System.out.println("desc is null");
            }

            else
            {
                String desc= fieldsObject.getString("description");
                System.out.println("description  "+desc);

            }

            if (fieldsObject.isNull("assignee"))
            {
                System.out.println("assignee is null");
            }

            else
            {

                String assignee=fieldsObject.getJSONObject("assignee").getString("displayName");
                System.out.println("assignee "+assignee);

            }

            Task task=new Task();
            task.setTaskKey(issueKey);

            allIssues.add(task);

        }



        return allIssues;
    }
}
