/*
 * Varun Parbhakar
 *
 * TCSS-143
 * Heroes VS Monster (Dungeon DLC)
 */
import java.util.ArrayList;

/**
 * This is the Room class which populates the rooms and insures that each room is labeled correctly.
 * @author Varun Parbhakar
 */
public class Room {
    private boolean hasMy_Monster;
    private boolean hasMy_Pit;
    private boolean hasMy_HealingPotion;
    private boolean hasMy_VisionPotion;
    private boolean hasMy_Entrance;
    private boolean hasMy_Exit;
    private boolean hasMy_Crown;
    private boolean hasMy_2ndCrown;
    private boolean isPlayerinRoom;
    private String myDisplayIcon;
    private ArrayList<String> myRoomInventory = new ArrayList<>();

    /**
     * This is the room constructor which populates the room with healing and vision potions
     * and other items.
     */
    public Room() {
        myDisplayIcon = "? ";
        if (Math.random() < .1) {
            setHasHealingPotion(true);
            myRoomInventory.add("Healing Potion");
        }
        if (Math.random() < .05) {
            setHasVisionPotion(true);
            myRoomInventory.add("Vision Potion");
        }
        if (Math.random() < .1) {
            setHasPit(true);
            myRoomInventory.add("Pit!");
        }
        if (Math.random() < .1) {
            setHasMonster(true);
            myRoomInventory.add("Monster!");
        }

    }

    /**
     * This toString method returns the room icon.
     * @return
     */
    public String toString() {
        if (isPlayerinRoom){
            return "* ";
        }
        return myDisplayIcon;
    }
    /**
     * This is the explore method which searches the room and sets the icon of that room accordingly.
     */
    protected void exploreTheRoom(){
        if (hasMy_Entrance || hasMy_Exit){
            if (hasMy_Entrance){
                setMyDisplayIcon("I ");
            }
            if (hasMy_Exit){
                setMyDisplayIcon("O ");
            }
        }
        else {
            if (myRoomInventory.size() == 1 && !hasMy_Entrance) {
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
            } else if (myRoomInventory.size() == 0 && !hasMy_Entrance) {
                setMyDisplayIcon("E ");
            } else if (myRoomInventory.size() > 1 && !hasMy_Entrance){
                setMyDisplayIcon("M ");
            }
        }
    }

    /**
     * This method sets the hasMy_Monster field to the passed boolean value.
     * @param theMonster
     */
    protected void setHasMonster(final boolean theMonster) {
        hasMy_Monster = theMonster;

    }

    /**
     * This method adds the passed item to the room inventory.
     * @param theItem
     */
    protected void addRoomInventory(final String theItem) {
        myRoomInventory.add(theItem);
    }

    /**
     * This method return the room's inventory.
     * @return
     */
    protected ArrayList getMyRoomInventory() {
        return myRoomInventory;
    }

    /**
     * This methods sets the hasMy_Pit to the passed value.
     * @param thePit
     */
    protected void setHasPit(final boolean thePit) {
        hasMy_Pit = thePit;

    }

    /**
     * This methods sets the hasMy_HealingPotion to the passed value.
     * @param theHealingPotion
     */
    protected void setHasHealingPotion(final boolean theHealingPotion) {
        hasMy_HealingPotion = theHealingPotion;

    }

    /**
     * This methods sets the hasMy_VisionPotion to the passed value.
     * @param theVisionPotion
     */
    protected void setHasVisionPotion(final boolean theVisionPotion) {
        hasMy_VisionPotion = theVisionPotion;

    }

    /**
     * This methods sets the hasMy_Entrance to the passed value.
     * @param theEntrance
     */
    protected void setHasEnterance(final boolean theEntrance) {
        setEmptyRoom();
        setMyDisplayIcon("I ");
        hasMy_Entrance = theEntrance;


    }

    /**
     * This method clears the room of any items.
     */
    protected void setEmptyRoom() {
        hasMy_Monster = false;
        hasMy_Pit = false;
        hasMy_HealingPotion = false;
        hasMy_VisionPotion = false;
        hasMy_Entrance = false;
        hasMy_Exit = false;
        hasMy_Crown = false;
        hasMy_2ndCrown = false;
        clearInventory();
    }

    /**
     * This method is used to move items from room's inventory to the Hero's inventory.
     * @param theItem (The item being picked up)
     * @param theHero (The Hero)
     */
    protected void pickUP(final String theItem, final Hero theHero){
        if (myRoomInventory.size() != 0) {
            if (myRoomInventory.contains(theItem)){
                theHero.addItem2Satchel(theItem);
                myRoomInventory.remove(theItem);
            }

        }
    }

    /**
     * This method clears the ArrayList that contains the items in the room.
     */
    protected void clearInventory() {
        myRoomInventory.clear();
    }

    /**
     * This methods sets the hasMy_Exit to the passed value.
     * @param theExit (Boolean value to indicate yes or no)
     */
    protected void setHasExit(final boolean theExit) {
        setEmptyRoom();
        hasMy_Exit = theExit;

    }

    /**
     * This methods sets the hasMy_Crown to the passed value.
     * @param theCrown (Boolean value to indicate yes or no)
     */
    protected void setHasMy_Crown(final boolean theCrown) {
        hasMy_Crown = theCrown;

    }

    /**
     * This methods sets the hasMy_2ndCrown to the passed value.
     * @param theCrown (Boolean value to indicate yes or no)
     */
    protected void setHasMy_SecondCrown(final boolean theCrown) {
        hasMy_2ndCrown = theCrown;

    }
    /**
     * This method sets the isPlayerinRoom to the passed value.
     * @param isHe (Boolean if the player is in the room)
     */
    protected void setisPlayerinRoom(final boolean isHe){
        isPlayerinRoom = isHe;
    }

    /**
     * This methods return hasMy_Entrance.
     * @return
     */
    protected boolean getHasEnterance() {
        return hasMy_Entrance;
    }

    /**
     * This methods return hasMy_Exit.
     * @return
     */
    protected boolean getHasExit() {
        return hasMy_Exit;
    }

    /**
     * This methods return hasMy_Crown.
     * @return
     */
    protected boolean getHasACrown(){
        return hasMy_Crown;
    }

    /**
     * This method return hasMy_2ndCrown.
     * @return
     */
    protected boolean getHasASecondCrown(){
        return hasMy_2ndCrown;
    }

    /**
     * This method sets the icon of the room with the passed String
     * @param theIcon (String text that represents the room)
     */
    protected void setMyDisplayIcon(final String theIcon) {
        if (theIcon.length() > 2 || theIcon.length() <= 0) {
            throw new IllegalArgumentException("The Icon string length cannot be greater than 1 or less than 0");
        } else {
            myDisplayIcon = theIcon;
        }
    }
}
//END
