/*
        Your task in order to complete this Kata is to write a function which formats a duration, 
        given as a number of seconds, in a human-friendly way.
        
        The function must accept a non-negative integer. If it is zero, it just returns "now". 
        Otherwise, the duration is expressed as a combination of years, days, hours, minutes and seconds.
    
        It is much easier to understand with an example:
            TimeFormatter.formatDuration(62)   //returns "1 minute and 2 seconds"
            TimeFormatter.formatDuration(3662) //returns "1 hour, 1 minute and 2 seconds"

        For the purpose of this Kata, a year is 365 days and a day is 24 hours.
        Note that spaces are important.
        
        Detailed rules
        * The resulting expression is made of components like 4 seconds, 1 year, etc. In general, 
        a positive integer and one of the valid units of time, separated by a space. 
        The unit of time is used in plural if the integer is greater than 1.
 
        * The components are separated by a comma and a space (", "). Except the last component, 
          which is separated by " and ", just like it would be written in English.
    
        * A more significant units of time will occur before than a least significant one. 
          Therefore, 1 second and 1 year is not correct, but 1 year and 1 second is.

        * Different components have different unit of times. So there is not repeated units like in 5 seconds and 1 second.

        * A component will not appear at all if its value happens to be zero. Hence, 1 minute and 0 seconds is not valid, but it should be just 1 minute.

        * A unit of time must be used "as much as possible". It means that the function should not return 61 seconds,
        but 1 minute and 1 second instead. Formally, the duration specified by of a component must not be greater than any valid more significant unit of time.
    */
package time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Heba Ashour
 */
public class TimeFormatter {
    
    
    public static void main(String[] args) {
        System.out.println(formatDuration(3992114));
    }
    
    public static String formatDuration(int seconds) {
        System.out.println("seconds " + seconds);
        int secs = 0;
        int minutes = 0;
        int hours = 0;
        int days = 0;
        int years = 0;
        String result;
        if(seconds < 60){
            secs = seconds;
        }else if (60 <= seconds && seconds < 3600 ){
            minutes = seconds / 60;
            secs = seconds % 60;
        }else if (3600 <= seconds && seconds < 86400){
            hours = seconds / (60*60);
            minutes = (seconds - (hours*3600)) / 60;
            secs = (seconds - (hours*3600) - (minutes*60)) % 60;
            
        }else if (86400 <= seconds && seconds < 31536000){
            days = seconds / 86400;
            hours = (seconds - (days * 86400)) / 3600;
            minutes = (seconds - (days * 86400) - (hours * 3600)) / 60;
            secs = (seconds - (days * 86400) - (hours * 3600) - (minutes*60)) % 60;
        }else{
            years = seconds / 31536000;
            days = (seconds - (years * 31536000)) / 86400;
            hours = (seconds - (years * 31536000) - (days * 86400)) / 3600;
            minutes = (seconds - (years * 31536000) - (days * 86400) - (hours * 3600)) / 60;
            secs = (seconds - (years * 31536000) - (days * 86400) - (hours * 3600) - (minutes*60)) % 60;                        
        }
         
        Map<String, Integer> numsMap = new HashMap<>();
        numsMap.put("year", years);
        numsMap.put("day", days);
        numsMap.put("hour", hours);
        numsMap.put("minute", minutes);
        numsMap.put("second", secs);
       
        int count = 0;
        ArrayList<String> fkeys = new ArrayList<>();
    
        for (Map.Entry<String, Integer> entry : numsMap.entrySet()){
            if(entry.getValue() != 0){
                count++;
                fkeys.add(entry.getKey());
            }
        }
        
        if(count == 0){
            result = "now";
        }else if(count == 1){
            String key = fkeys.get(0);
            System.out.println("KEY " + key);
            result = numsMap.get(key) + ((numsMap.get(key) == 1)? " " + key : " " + key + "s");
        }else if (count == 2){
            String key0 = fkeys.get(0);
            String key1 = fkeys.get(1);            
            result = (numsMap.get(key0) + ((numsMap.get(key0) == 1)? " " + key0 : " " + key0 + "s"))
                     + (" and " + (numsMap.get(key1) + (numsMap.get(key1) == 1? " " + key1 : " " + key1 + "s")));
        }else{
            //(year -> day -> hour -> minute -> second) to make the time duration from year to second
            int y = numsMap.get("year");
            int d = numsMap.get("day");
            int h = numsMap.get("hour");
            int m = numsMap.get("minute");
            int s = numsMap.get("second");
           
            ArrayList<String> keys = new ArrayList<>();
            if(y != 0) keys.add("year");
            if(d != 0) keys.add("day");
            if(h != 0) keys.add("hour");
            if(m != 0) keys.add("minute");
            if(s != 0) keys.add("second");
            
            
            String key0 = keys.get(0);
            result = (numsMap.get(key0) + ((numsMap.get(key0) == 1)? " " + key0 : " " + key0 + "s"));
            for (int i = 1; i < keys.size() - 1; i++) {
                key0 = keys.get(i);
                result += ", "+ (numsMap.get(key0) + ((numsMap.get(key0) == 1)? " " + key0 : " " + key0 + "s"));
            }
            String key1 = keys.get(keys.size() - 1); 
            result += (" and " + (numsMap.get(key1) + (numsMap.get(key1) == 1? " " + key1 : " " + key1 + "s")));
        }
        
        System.out.println("Y: "+ years + " D: " + days +  " H: " + hours + " M: " + minutes +" S: " + secs );
        System.out.println(result);
        return result;
    }
    
}
