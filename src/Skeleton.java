
/*
 * Varun Parbhakar
 *
 * TCSS-143
 * Assignment 2
 */
/**
 * This is a subclass of Monster.
 */
public class Skeleton extends Monster {

    /**
     * This constructor initializes all of the values
     * for the Skeleton character
     * @param theC_Name (The character name)
     */
    protected Skeleton(String theC_Name) {
        super(theC_Name,
                100,
                3,
                30,
                50,
                .8,
                .3,
                30,
                50);
    }
}