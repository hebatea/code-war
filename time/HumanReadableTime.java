/*
        Write a function, which takes a non-negative integer (seconds) 
        as input and returns the time in a human-readable format (HH:MM:SS)

        HH = hours, padded to 2 digits, range: 00 - 99
        MM = minutes, padded to 2 digits, range: 00 - 59
        SS = seconds, padded to 2 digits, range: 00 - 59
        The maximum time never exceeds 359999 (99:59:59)

        You can find some examples in the test fixtures.
    */
package time;

/**
 *
 * @author Heba Ashour
 */
public class HumanReadableTime {
    public static void main(String[] args) {
        System.out.println(makeReadable(86399));
    }
    
    
    public static String makeReadable(int seconds) {
        System.out.println("seconds " + seconds);
        int secs = 0;
        int minutes = 0;
        int hours = 0;
        if(seconds < 60){
            secs = seconds;
        }else if (60 <= seconds && seconds < 3600 ){
            minutes = seconds / 60;
            secs = seconds % 60;
        }else{
            hours = seconds / (60*60);
            minutes = (seconds - (hours*3600)) / 60;
            secs = (seconds - (hours*3600) - (minutes*60)) % 60;
        }
        
        String result = ((hours < 10) ? ("0"+ hours) :(hours+"")) + ":" 
                + ((minutes < 10) ? ("0"+ minutes) : (minutes+"")) + ":"
                + ((secs < 10) ? ("0"+ secs) :(secs+""));
       
        System.out.println( "H: " + hours + " M: " + minutes +" S: " + secs );
        return result;
    }
}
