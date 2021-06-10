import javax.xml.transform.Source;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class DungeonAdventure {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        if(introduction(userInput)) {
            Hero hero = new Warrior(heroName(userInput));
            Monster monster = new Skeleton("Null Pointer");
            int myDungeonSize = 5;
            Dungeon myDungeon = new Dungeon(myDungeonSize, hero);


            while(hero.alive()) {
                mover(userInput, hero, myDungeonSize, myDungeon, monster);

            }
        }
    }
    // return on this method
    public static boolean introduction(Scanner userInput) {
        System.out.println("------------------ Welcome!!! -------------------");
        System.out.println("---------------- Hear Ye Hear Ye ----------------");
        System.out.println("A long time ago, a warrior had challenged" +
                " the creator, \n the programmer himself, to a round of " +
                "Petteia, but \n       he lost and he paid with his life. \n" +
                "\nNo one has challenged him ever again, but you my child,");
        System.out.println("You wish to challenge him?");
        return (yesORNo(userInput));
    }
    /**
     * This method is responsible for making sure the user inputs a name for the hero,
     * this method is also performs input validation.
     *
     * @return String (The name of the hero)
     */
    public static String heroName(final Scanner userInput) {
        boolean correctAnswer = false;
        String heroName = null;

        while (!correctAnswer) {
            System.out.print("Please enter a name for your hero: ");
            if (userInput.hasNextInt() || userInput.hasNextDouble()) {
                System.out.println("Invalid input");
                userInput.next();
            } else {
                heroName = userInput.next();
                correctAnswer = true;
            }

        }
        return heroName;

    }

    public static void mover(final Scanner userInput,
                             final Hero theHero,
                             final int dungeonSize,
                             final Dungeon theDungeon,
                             final Monster theMonster) {

        if (theHero.alive()){

        }else {

        }
        if (theHero.alive()) {

            Point location = theHero.getCharacterLocation();
            Room myRoom = theDungeon.getContent(theHero.getCharacterLocationY(),theHero.getCharacterLocationX());
            myRoom.exploreTheRoom();
            ArrayList<String> roomItemList = (ArrayList)(myRoom.getMyRoomInventory()).clone();
            if (theHero.getHeroSatchel().contains("Vision Potion") || theHero.getHeroSatchel().contains("Healing Potion")) {
                String potionChoice = ("You have unused potions, you can press 'y' to use your item 'n' for no");
                System.out.println(potionChoice);
                System.out.print("These are your available potions " + theHero.getHeroSatchel());
                if (yesORNo(userInput)){
                    if (theHero.getHeroSatchel().contains("Vision Potion")) {
                        theDungeon.visionPotionUser(theHero.getCharacterLocation());
                        theHero.removeSatchelItem("Vision Potion");
                    }
                    if (theHero.getHeroSatchel().contains("Healing Potion")) {
                        theHero.healingPotion();
                        theHero.removeSatchelItem("Healing Potion");
                    }
                    if (theHero.getHeroSatchel().contains("Vision Potion") && theHero.getHeroSatchel().contains("Healing Potion")) {
                        theHero.removeSatchelItem("Healing Potion");
                        theHero.removeSatchelItem("Vision Potion");
                    }
                }
            }

            myRoom.setisPlayerinRoom(true);
            System.out.println(theDungeon);   //Prints dungeon
            roomItemList = checkRoom(theHero, roomItemList, theMonster, userInput, theDungeon);
            System.out.print("This room has: ");
            System.out.println(roomItemList);

            heroItemPicker(roomItemList, myRoom, theHero);
            if (!isHeroAtExit(myRoom, theHero, userInput)){
                if (!theHero.alive()){
                    System.out.println("You came here with such life yet here you lie");
                    System.out.println("               lifeless.");
                    System.out.println("        Better Luck Next time!");
                } else {
                    System.out.println(theHero);
                    System.out.print(theHero.getCharacter_Name()+ "'s Inventory: ");
                    System.out.println(theHero.getHeroSatchel());
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
                }
            }





        }




    }
    public static boolean isHeroAtExit(final Room theRoom, final Hero theHero, final Scanner userInput) {
        if (theRoom.getHasExit()) {
            System.out.println("You are on the exit!");
            if (theHero.hasBothCrowns()) {
                if (yesORNo(userInput)) {
                    System.out.println("               You did great out there!");
                    System.out.println("the programmer didn't even notice the missing coding crowns.");
                    theHero.killCharacter();
                    return true;
                }
            } else {
                System.out.println("You have to keep looking for more treasure!");
                return false;
            }
        }
        return false;
    }
    public static boolean yesORNo(final Scanner userInput) {
        boolean correctAnswer = false;
        String choice = null;
        while (!correctAnswer) {
            System.out.print("'y' for yes, 'n' for no: ");

            if (userInput.hasNext()) {
                choice = userInput.next();

                if (choice.equals("n") || choice.equals("y")) {
                    correctAnswer = true;
                    if (choice.equals("y")) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    System.out.println("Please select the correct response");
                }
            } else {
                System.out.println("Invalid Input\n");
                userInput.next();
            }
        }
    return false;
    }
    public static void heroItemPicker(final ArrayList<String> theRoomItems,
                                      final Room theRoom,
                                      final Hero theHero) {
        for( String theItem : theRoomItems) {
            if (theItem.equals("Coding Crown")){
                theRoom.pickUP(theItem, theHero);
                System.out.println("You have picked up the Coding Crown!");
                theHero.addCrownPiece();
                continue;

            }if (theItem.equals("Second Coding Crown")){
                theRoom.pickUP(theItem, theHero);
                theHero.addCrownPiece();
                System.out.println("You have picked up the Second Coding Crown!");
                continue;

            }
            if (theItem.equals("Healing Potion")){
                theRoom.pickUP(theItem, theHero);
                theHero.addHealingPotion();
                System.out.println("You have picked up the Healing Potion!");
                continue;

            }
            if (theItem.equals("Vision Potion")){
                theRoom.pickUP(theItem, theHero);
                theHero.addVisionPotion();
                System.out.println("You have picked up the Vision Potion!");

                continue;
            }


        }

    }
    public static ArrayList<String> checkRoom(final Hero theHero,
                          final ArrayList<String> theRoomItems,
                          final Monster theMonster,
                          final Scanner userInput, final Dungeon theDungeon) {
        ArrayList<String> copyList = (ArrayList)(theRoomItems.clone());
        for (String theItem: copyList) {
            if (theItem.equals("Monster!")){
                int roundCounter = 1;
                while (theHero.alive() && theMonster.alive()) {
                    System.out.println("\n\t\t\t Round: " + roundCounter + "\n");
                    System.out.println("Player HP: " + theHero.getCharacter_HealthPoints() + "\t\t Monster's HP: " + theMonster.getCharacter_HealthPoints() + "\n");
                    theHero.attacks(theMonster);
                    theMonster.attacks(theHero);
                    roundCounter++;
                    System.out.print("END OF ROUND, PRESS ANY KEY TO CONTINUE");
                    userInput.nextLine();
                } if (!theMonster.alive()){
                    theRoomItems.remove("Monster!");
                    System.out.println(theDungeon);
                }
                } if (theItem.equals("Pit!")) {
                theHero.heroTakesDamage();
            }

            }
        return theRoomItems;

        }

        public static String directionChecker(final Scanner userInput, final Point theLocation, final int theDungeonSize){
        String choices = "Please select your movement(n for North, s for South, e for East, w for West)";
        ArrayList<String> choiceList = availableChoices(theLocation, theDungeonSize);
        String direction = null;
        boolean correctAnswer = false;

        // Input Validation
        while (!correctAnswer) {
            System.out.println(choices);
            System.out.print("These are your available moves " + choiceList + ": ");

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
}
