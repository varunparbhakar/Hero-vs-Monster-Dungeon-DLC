import java.awt.*;
import java.util.ArrayList;

public class Room {
    private boolean hasMy_Monster;
    private boolean hasMy_Pit;
    private boolean hasMy_HealingPotion;
    private boolean hasMy_VisionPotion;
    private boolean hasMy_Enterance;
    private boolean hasMy_Exit;
    private boolean hasMy_Crown;
    private boolean hasMy_2ndCrown;
    private boolean isPlayerinRoom;
    private String myDisplayIcon;
    private int myTotalItem;

    private ArrayList<String> myRoomInventory = new ArrayList<>();
    public Room() {
        myDisplayIcon = "? ";
        if (Math.random() < .1) {
            setHasHealingPotion(true);
            myRoomInventory.add("Healing Potion");
        }
        if (Math.random() < .05) {
            setHasVisionPotion(true);
            myRoomInventory.add("Vision Potion");
            myTotalItem++;
        }
        if (Math.random() < .1) {
            setHasPit(true);
            myRoomInventory.add("Pit!");
            myTotalItem++;
        }
        if (Math.random() < .1) {
            setHasMonster(true);
            myRoomInventory.add("Monster!");
            myTotalItem++;
        }

    }
    public String toString() {
        if (isPlayerinRoom){
            return "* ";
        }
        return myDisplayIcon;
    }
    protected void displayContent(){
        if (hasMy_Monster) {
            System.out.println("This room has a monster");
        } if (hasMy_VisionPotion) {
            System.out.println("This room has a vision potion");
        } if (hasMy_HealingPotion) {
            System.out.println("This room has a healing potion");
        } if (hasMy_Pit) {
            System.out.println("This room has a pit");
        } if (hasMy_Crown) {
            System.out.println("This room has a crown");
        }if (hasMy_2ndCrown) {
            System.out.println("This room has the 2nd crown");
        }
    }
    protected void exploreTheRoom(){
        // IF player is in room, make the ICON == C
        if (hasMy_Enterance || hasMy_Exit){
            if (hasMy_Enterance){
                setMyDisplayIcon("I ");
            }
            if (hasMy_Exit){
                setMyDisplayIcon("O ");
            }

        } else {
            if (myRoomInventory.size() == 1 && !hasMy_Enterance) {
                if (hasMy_Monster) {
                    setMyDisplayIcon("X ");
                } if (hasMy_VisionPotion) {
                    setMyDisplayIcon("V ");
                } if (hasMy_HealingPotion) {
                    setMyDisplayIcon("H ");
                } if (hasMy_Pit) {
                    setMyDisplayIcon("P ");
                } if (hasMy_Crown) {
                    setMyDisplayIcon("Q ");
                }
                if (hasMy_2ndCrown) {
                    setMyDisplayIcon("K ");
                }
            } else if (myRoomInventory.size() == 0 && !hasMy_Enterance) {
                System.out.println("This room is empty");
                setMyDisplayIcon("E ");
            } else if (myRoomInventory.size() > 1 && !hasMy_Enterance){
                System.out.println("This room has multiple items");
                setMyDisplayIcon("M ");

            }

        }

    }
    protected void setHasMonster(final boolean theMonster) {
        hasMy_Monster = theMonster;

    }
    protected void addRoomInventory(final String theItem) {
        myRoomInventory.add(theItem);
    }
    protected ArrayList getMyRoomInventory() {
        return myRoomInventory;
    }
    protected void setHasPit(final boolean thePit) {
        hasMy_Pit = thePit;

    }
    protected void setHasHealingPotion(final boolean theHealingPotion) {
        hasMy_HealingPotion = theHealingPotion;

    }
    protected void setHasVisionPotion(final boolean theVisionPotion) {
        hasMy_VisionPotion = theVisionPotion;

    }
    protected void setHasEnterance(final boolean theEnterance) {
        setEmptyRoom();
        setMyDisplayIcon("I ");
        hasMy_Enterance = theEnterance;


    }
    //Empties out the entire room
    protected void setEmptyRoom() {
        hasMy_Monster = false;
        hasMy_Pit = false;
        hasMy_HealingPotion = false;
        hasMy_VisionPotion = false;
        hasMy_Enterance = false;
        hasMy_Exit = false;
        hasMy_Crown = false;
        hasMy_2ndCrown = false;
        clearInventory();
    }
    protected void pickUP(final String theItem, final Hero theHero){
        if (myRoomInventory.size() != 0) {
            if (myRoomInventory.contains(theItem)){
                theHero.addItem2Satchel(theItem);
                myRoomInventory.remove(theItem);
            }

        }
    }
    protected void clearInventory() {
        myRoomInventory.clear();
    }
    protected void setHasExit(final boolean theExit) {
        setEmptyRoom();
        hasMy_Exit = theExit;

    }
    protected void setHasMy_Crown(final boolean theCrown) {
        hasMy_Crown = theCrown;

    }
    protected void setHasMy_SecondCrown(final boolean theCrown) {
        hasMy_2ndCrown = theCrown;

    }
    protected boolean getHasEnterance() {
        return hasMy_Enterance;
    }
    protected boolean getHasExit() {
        return hasMy_Exit;
    }
    protected boolean getHasACrown(){
        return hasMy_Crown;
    }
    protected boolean getHasASecondCrown(){
        return hasMy_2ndCrown;
    }
    protected void setMyDisplayIcon(final String theIcon) {
        if (theIcon.length() > 2 || theIcon.length() <= 0) {
            throw new IllegalArgumentException("The Icon string length cannot be greater than 1 or less than 0");
        } else {
            myDisplayIcon = theIcon;
        }
    }
    protected void setisPlayerinRoom(final boolean isHe){
        isPlayerinRoom = isHe;
    }
}
