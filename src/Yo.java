import javax.swing.plaf.SliderUI;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Yo {
    public static void main(String[] args) {
        ArrayList<String> varun = new ArrayList<>();
        varun.add("Varun");
        if (varun.contains("Varun")){
            System.out.println("He is there");
        }


    }
    public static float factorial(int number) {
        float sum = 0;
        for (int i = number; i < 0; i--) {
            sum += number * number -1;
        }
        System.out.println(sum);
        return sum;
    }
}