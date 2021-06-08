import javax.swing.plaf.SliderUI;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Yo {
    public static void main(String[] args) {
        int size = 3;
        int y = 3;
        int x = 1;
        ArrayList<Character> availableChoices = new ArrayList<>();
        boolean north = y > 0;
        boolean south = y < size;
        boolean west = x > 0;
        boolean east = x < size;
        if (north) {
            availableChoices.add('n');
        }if (south) {
            availableChoices.add('s');
        }if (east) {
            availableChoices.add('e');
        }if (west) {
            availableChoices.add('w');
        }
        System.out.println(availableChoices);
        System.out.println(availableChoices.contains('n'));


    }
}