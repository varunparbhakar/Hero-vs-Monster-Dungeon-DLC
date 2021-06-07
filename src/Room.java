public class Room {
    private boolean hasMy_Monster;
    private boolean hasMy_Pit;
    private boolean hasMy_HealingPotion;
    private boolean hasMy_Enterance;
    private boolean hasMy_Exit;
    private boolean hasMy_Crown;

    public Room() {



    }
    protected void setHasMonster(final boolean theMonster) {
        if (theMonster) {
            hasMy_Monster = true;
        }

    }
    protected void setHasPit(final boolean thePit) {
        if (thePit) {
            hasMy_Pit = true;
        } else {

        }

    }
    protected void setHasHealingPotion(final boolean theHealingPotion) {
        if (theHealingPotion) {
            hasMy_HealingPotion = true;
        }

    }
}
