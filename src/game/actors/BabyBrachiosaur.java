package game.actors;

public class BabyBrachiosaur extends BabyDinosaur{
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public BabyBrachiosaur(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        dinoChar = 'b';
    }

    public boolean isVegetarian(){return true;}

}
