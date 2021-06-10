import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Dungeon {
    private ArrayList<ArrayList<Room>> myDungeon;
    private int myDungeonSize;

    public Dungeon(final int theDungeonSize, final Hero theHero) {
        myDungeonSize = theDungeonSize;
        myDungeon = new ArrayList<>();
        dungeonBuilder();
        exitEnteranceMaker(myDungeon, theDungeonSize, theHero);

    }
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

    protected Room getContent(final int theY, final int theX) {
        return myDungeon.get(theY).get(theX);
    }
    public String toString() {
        String dungeonPrint = "";
        for (ArrayList row: myDungeon) {
            for (Object myRoom : row) {
                dungeonPrint += myRoom.toString();
            }
            dungeonPrint += "\n";
        }
        return dungeonPrint ;
    }



    public static void exitEnteranceMaker(final ArrayList<ArrayList<Room>> theDungeon, final int theDungeonSize, final Hero theHero ) {
        Random rand = new Random();
        boolean haveEnterance = false;
        boolean haveExit = false;
        boolean haveCrown = false;
        boolean have2ndCrown = false;
        int roomNumber;
        Room roomSetter;
        while (!haveEnterance || !haveExit) {
            if (Math.random() < .1 && !haveEnterance) {
                roomNumber = rand.nextInt(theDungeonSize - 1);
                roomSetter = theDungeon.get(0).get(roomNumber);
                theHero.setCharacterLocation(roomNumber, 0);
                roomSetter.setHasEnterance(true);
                haveEnterance = true;
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
    protected void revealAll() {
        Room dummyRoom;
        for(int i = 0; i < myDungeonSize; i++){
            for (int j = 0; j < myDungeonSize; j++) {
                dummyRoom = myDungeon.get(i).get(j);
                dummyRoom.exploreTheRoom();
            }
        }
    }
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
}
