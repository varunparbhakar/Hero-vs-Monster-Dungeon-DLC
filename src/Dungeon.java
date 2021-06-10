/*
 * Varun Parbhakar
 *
 * TCSS-143
 * Heroes VS Monster (Dungeon DLC)
 */
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class manages the dungeon, it is responsible for creating and populating the dungeon
 * along other operation that are exclusive to the dungeon.
 * @author Varun Parbhakar
 */
public class Dungeon {
    private ArrayList<ArrayList<Room>> myDungeon;
    private int myDungeonSize;
    private boolean myCheatEnabled;

    /**
     * This constructor creates the dungeon with the use of the specified dungeon size from
     * the user.
     * @param theDungeonSize
     * @param theHero
     */
    public Dungeon(final int theDungeonSize, final Hero theHero) {
        myDungeonSize = theDungeonSize;
        myDungeon = new ArrayList<>();
        dungeonBuilder();
        exitEntranceMaker(myDungeon, theDungeonSize, theHero);

    }

    /**
     * This method returns list of all of the keys for the dungeon map.
     * @return
     */
    protected ArrayList<String> mapLegend() {
        ArrayList<String> list = new ArrayList<>();
        list.add("'*' = Current Player Location");
        list.add("'E' = Empty Room");
        list.add("'I' = Entrance");
        list.add("'P' = Pit");
        list.add("'Q' & 'K' = Coding Crown");
        list.add("'V' = Vision Potion");
        list.add("'H' = Healing Potion");
        list.add("'X' = Monster");
        list.add("'M' = Room containing multiple items");

        return list;
    }

    /**
     * This method build a dungeon by creating rooms in the ArrayList
     */
    protected void dungeonBuilder() {
        for (int i = 0; i < myDungeonSize; i++) {
            ArrayList<Room> row = new ArrayList<>();
            for (int j = 0; j < myDungeonSize; j++) {
                Room myNewRoom = new Room();
                row.add(myNewRoom);
            }
            myDungeon.add(row);
        }
    }

    /**
     * This method returns a room object that is located at the passed location
     * @param theY (The Y coordinate)
     * @param theX (The X coordinate)
     * @return (Room)
     */
    protected Room getContent(final int theY, final int theX) {
        return myDungeon.get(theY).get(theX);
    }

    /**
     * To string method for the dungeon.
     * @return (String containing the layout of the dungeon)
     */
    public String toString() {
        String dungeonPrint = "\n";
        for (ArrayList row: myDungeon) {
            for (Object myRoom : row) {
                dungeonPrint += myRoom.toString();
            }
            dungeonPrint += "\n";
        }
        return dungeonPrint ;
    }


    /**
     * This method creates and exit, entrance and populates the dungeon with 2 crowns.
     * @param theDungeon
     * @param theDungeonSize
     * @param theHero
     */
    public static void exitEntranceMaker(final ArrayList<ArrayList<Room>> theDungeon, final int theDungeonSize, final Hero theHero ) {
        Random rand = new Random();
        boolean haveEntrance = false;
        boolean haveExit = false;
        boolean haveCrown = false;
        boolean have2ndCrown = false;
        int roomNumber;
        Room roomSetter;
        while (!haveEntrance || !haveExit) {
            if (Math.random() < .1 && !haveEntrance) {
                roomNumber = rand.nextInt(theDungeonSize - 1);
                roomSetter = theDungeon.get(0).get(roomNumber);
                theHero.setCharacterLocation(roomNumber, 0);
                roomSetter.setHasEnterance(true);
                haveEntrance = true;
            }
            if (Math.random() < .1 && !haveExit) {

                roomSetter = theDungeon.get(theDungeonSize - 1).get(rand.nextInt(theDungeonSize));
                roomSetter.setHasExit(true);
                haveExit = true;
            }
        }
        while (!haveCrown || !have2ndCrown) {
            if (Math.random() < .1 && !haveCrown) {
                roomSetter = theDungeon.get(rand.nextInt(theDungeonSize - 1)).get(rand.nextInt(theDungeonSize - 1));
                if (!roomSetter.getHasASecondCrown() && !roomSetter.getHasEnterance() && !roomSetter.getHasExit()) {
                    roomSetter.setHasMy_Crown(true);
                    roomSetter.addRoomInventory("Coding Crown");
                    haveCrown = true;
                }

            }
            if (Math.random() < .1 && !have2ndCrown) {
                roomSetter = theDungeon.get(rand.nextInt(theDungeonSize - 1)).get(rand.nextInt(theDungeonSize - 1));
                if (!roomSetter.getHasACrown() && !roomSetter.getHasEnterance() && !roomSetter.getHasExit()) {
                    roomSetter.setHasMy_SecondCrown(true);
                    roomSetter.addRoomInventory("Second Coding Crown");
                    have2ndCrown = true;
                }
            }

        }
    }

    /**
     * This is a cheat method which reveals all of the items in the dungeon.
     */
    protected void revealAll() {
        Room dummyRoom;
        for(int i = 0; i < myDungeonSize; i++){
            for (int j = 0; j < myDungeonSize; j++) {
                dummyRoom = myDungeon.get(i).get(j);
                dummyRoom.exploreTheRoom();
            }
        }
    }

    /**
     * This method searches for the possible directions and reveals the room accordingly.
     * @param theLocation (Location of the hero)
     */
    protected void visionPotionUser(final Point theLocation) {
        Room dummyRoom = null;
        ArrayList<Point> currentLocation = new ArrayList<>();
        Point dummyPoint = (Point)(theLocation.clone());;
        boolean north = dummyPoint.y - 1 >= 0;
        boolean south = dummyPoint.y + 1 <= myDungeonSize-1;
        boolean west = dummyPoint.x - 1 >= 0;
        boolean east = dummyPoint.x + 1 <= myDungeonSize-1;
        if (north) {
            dummyPoint.translate(0,-1);
            currentLocation.add(dummyPoint);
            dummyPoint = (Point)(theLocation.clone());
        }if (south) {
            dummyPoint.translate(0,1);
            currentLocation.add(dummyPoint);
            dummyPoint = (Point)(theLocation.clone());
        }if (east) {
            dummyPoint.translate(1,0);
            currentLocation.add(dummyPoint);
            dummyPoint = (Point)(theLocation.clone());
        }if (west) {
            dummyPoint.translate(-1,0);
            currentLocation.add(dummyPoint);
            dummyPoint = (Point)(theLocation.clone());
        }


        for (Point local: currentLocation){
            dummyRoom = myDungeon.get(local.y).get(local.x);
            dummyRoom.exploreTheRoom();
        }
    }

    /**
     * This method sets the myCheatEnabled to true;
     */
    protected void setMyCheatEnabled(){
        System.out.println("Cheat has been enabled!");
        myCheatEnabled = true;
    }

    /**
     * This method returns the status of the myCheatEnabled field.
     * @return
     */
    protected boolean getMyCheat(){
        return myCheatEnabled;
    }
}
//END
