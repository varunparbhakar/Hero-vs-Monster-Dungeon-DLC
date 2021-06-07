import java.util.LinkedList;

import java.util.List;
public class Yo {
    public static void main(String[] args) {
        System.out.println(vowelsToEnd("beautifully"));

    }
    public static String  vowelsToEnd(final String theString) {
        if (theString.length() <= 0) {
            return "";
        }
        char cur = theString.charAt(0);
        if ((cur == 'a') || (cur == 'e') || (cur == 'i') || (cur == 'o') || (cur == 'u') || (cur == 'y')) {
            return vowelsToEnd(theString.substring(1)) + cur;
        }
        return cur + vowelsToEnd(theString.substring(1));

    }





    }
