import java.awt.*;

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

    public Room() {
        myDisplayIcon = "? ";
        if (Math.random() < .1) {
            setHasHealingPotion(true);
            myTotalItem++;
        }
        if (Math.random() < .05) {
            setHasVisionPotion(true);
            myTotalItem++;
        }
        if (Math.random() < .1) {
            setHasPit(true);
            myTotalItem++;
        }
        if (Math.random() < .1) {
            setHasMonster(true);
            myTotalItem++;
        }

    }
    public String toString() {
        if (isPlayerinRoom){
            return "C ";
        }
        return myDisplayIcon;
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
            if (myTotalItem == 1 && !hasMy_Enterance) {
                if (hasMy_Exit) {
                    setMyDisplayIcon("O ");
                } if (hasMy_Monster) {
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
            } else if (myTotalItem == 0 && !hasMy_Enterance) {
                System.out.println("This room is empty");
                setMyDisplayIcon("E ");
            } else {
                System.out.println("This room has multiple items");
                setMyDisplayIcon("M ");

            }

        }

    }
    protected void setHasMonster(final boolean theMonster) {
        hasMy_Monster = theMonster;

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
    }
    protected void setHasExit(final boolean theExit) {
        setEmptyRoom();
        hasMy_Exit = theExit;

    }
    protected void setHasMy_Crown(final boolean theCrown) {
        hasMy_Crown = theCrown;

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
