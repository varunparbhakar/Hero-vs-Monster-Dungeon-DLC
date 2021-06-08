import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Dungeon {
    public static void main(String[] args) {
        ArrayList<ArrayList<Room>> myDungeon = new ArrayList<>();
        Hero hero = new Warrior("Varun");
        Scanner userInput = new Scanner(System.in);
        int dungeonSize = 4;


        for (int i = 0; i < dungeonSize; i++) {
            ArrayList<Room> row = new ArrayList<>();
            for (int j = 0; j < dungeonSize; j++) {
                Room myNewRoom = new Room();
                row.add(myNewRoom);

            }
            myDungeon.add(row);

        }
        exitEnteranceMaker(myDungeon, dungeonSize, hero);

        printDungeon(myDungeon);

        for (int i = 0; i < 100; i++) {
            System.out.println();
            mover(userInput, hero, dungeonSize, myDungeon);

        }

    }
    public static void printDungeon (final ArrayList<ArrayList<Room>> theDungeon) {
        for (ArrayList row: theDungeon) {
            for (Object myRoom : row) {
                System.out.print(myRoom);
            }
            System.out.println();
        }
    }
    public static void mover(final Scanner userInput, final Hero theHero, final int dungeonSize, final ArrayList<ArrayList<Room>> theDungeon) {
        Point location = theHero.getCharacterLocation();
        System.out.println(theHero.getCharacterLocation());
        Room myRoom = theDungeon.get(theHero.getCharacterLocationY()).get(theHero.getCharacterLocationX());
        myRoom.exploreTheRoom();
        myRoom.setisPlayerinRoom(true);
        printDungeon(theDungeon);


        String direction = directionChecker(userInput, location, dungeonSize);
        if (direction.equals("n")){
            theHero.setCharacterLocationY(-1);
        }
        if (direction.equals("s")){
            theHero.setCharacterLocationY(1);
        }
        if (direction.equals("e")){
            theHero.setCharacterLocationX(1);
        }
        if (direction.equals("w")){
            theHero.setCharacterLocationX(-1);
        }
        myRoom.setisPlayerinRoom(false);
        printDungeon(theDungeon);
        System.out.println(theHero.getCharacterLocation());




    }
    public static ArrayList<String> availableChoices(final Point theLocation, final int theSize) {
        ArrayList<String> availableChoices = new ArrayList<>();
        boolean north = theLocation.y > 0;
        boolean south = theLocation.y < theSize-1;
        boolean west = theLocation.x > 0;
        boolean east = theLocation.x < theSize-1;
        if (north) {
            availableChoices.add("n");
        }if (south) {
            availableChoices.add("s");
        }if (east) {
            availableChoices.add("e");
        }if (west) {
            availableChoices.add("w");
        }
        return availableChoices;

    }


    public static String directionChecker(final Scanner userInput, final Point theLocation, final int theDungeonSize){
        String choices = "Please select your movement(n for North, s for South, e for East, w for West): ";
        ArrayList<String> choiceList = availableChoices(theLocation, theDungeonSize);
        System.out.println(choiceList);
        String direction = null;
        boolean correctAnswer = false;

        // Input Validation
        while (!correctAnswer) {
            System.out.print(choices);

            if (userInput.hasNext()) {
                direction = userInput.next();

                if (direction.equals("n") || direction.equals("s") || direction.equals("w") || direction.equals("e")) {
                    if (choiceList.contains(direction)){
                        correctAnswer = true;
                    }

                } else {
                    System.out.println("Please select the correct direction");

                }

            } else {
                System.out.println("Invalid Input\n");
                userInput.next();
            }

        }
        return direction;
    }
    public static void exitEnteranceMaker(final ArrayList<ArrayList<Room>> theDungeon, final int theDungeonSize, final Hero theHero ) {
        Random rand = new Random();
        boolean haveEnterance = false;
        boolean haveExit = false;
        boolean haveCrown = false;
        boolean have2ndCrown = false;
        int roomNumber;
        Room roomSetter;
        while(!haveEnterance || !haveExit || !haveCrown || !have2ndCrown) {
            if (Math.random() < .1 && !haveEnterance) {
                roomNumber = rand.nextInt(theDungeonSize-1);
                roomSetter = theDungeon.get(0).get(roomNumber);
                theHero.setCharacterLocation(roomNumber, 0);
                roomSetter.setHasEnterance(true);
                haveEnterance = true;
            }
            if (Math.random() < .1 && !haveExit) {

                roomSetter = theDungeon.get(theDungeonSize-1).get(rand.nextInt(theDungeonSize));
                roomSetter.setHasExit(true);
                haveExit = true;
            }
            if (Math.random() < .1 && !haveCrown) {
                roomSetter = theDungeon.get(rand.nextInt(theDungeonSize-1)).get(rand.nextInt(theDungeonSize-1));
                roomSetter.setHasMy_Crown(true);
                haveCrown = true;
            }
            if (Math.random() < .1 && !have2ndCrown) {
                roomSetter = theDungeon.get(rand.nextInt(theDungeonSize-1)).get(rand.nextInt(theDungeonSize-1));
                roomSetter.setHasMy_Crown(true);
                have2ndCrown = true;
            }
        }

        }
    }
