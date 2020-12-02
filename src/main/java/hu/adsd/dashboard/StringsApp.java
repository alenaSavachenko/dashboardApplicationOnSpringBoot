package hu.adsd.dashboard;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringsApp {


        public static void main(String[] args) {

            String input = "Hello Java! Hello JavaScript! JavaSE 8. SP-100";
            Pattern pattern = Pattern.compile("SP-(\\d*)");
            Matcher matcher = pattern.matcher(input);
            while(matcher.find())
                System.out.println(matcher.group());


            Pattern pattern2 = Pattern.compile("w3schools", Pattern.CASE_INSENSITIVE);
            Matcher matcher2 = pattern2.matcher("Visit W3Schools!");
            boolean matchFound = matcher2.find();
            if(matchFound) {
                System.out.println("Match found  "+matcher2.group());
            } else {
                System.out.println("Match not found");
            }


        }

}

