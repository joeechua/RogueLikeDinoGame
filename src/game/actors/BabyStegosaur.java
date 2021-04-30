package game.actors;

public class BabyStegosaur extends BabyDinosaur{
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public BabyStegosaur(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        dinoChar = 's';
    }

    public boolean isVegetarian(){return true;}
}
