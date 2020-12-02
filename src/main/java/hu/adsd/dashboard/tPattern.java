package hu.adsd.dashboard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class tPattern {

    public static void main(String[] args) {


        Pattern pattern = Pattern.compile("SP-(\\d*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher("Hello Java! Hello JavaScript! JavaSE 8 SP-100");
        boolean matchFound = matcher.find();
        if(matchFound) {
            System.out.println("Match found");
            System.out.println("found: "+matcher.group());
        } else {
            System.out.println("Match not found");
        }

    }
}
