package game.actors;

public class BabyAllosaur extends BabyDinosaur{
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public BabyAllosaur(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        dinoChar = 'a';
    }

    public boolean isVegetarian(){return false;}
}
