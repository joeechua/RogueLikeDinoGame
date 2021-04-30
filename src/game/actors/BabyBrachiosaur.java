package game.actors;

public class BabyBrachiosaur extends BabyDinosaur{
    /**
     * Constructor.
     */
    public BabyBrachiosaur() {
        super("Baby Brachiosaur", 'b', 1000);
        dinoChar = 'b';
    }

    public boolean isVegetarian(){return true;}

}
