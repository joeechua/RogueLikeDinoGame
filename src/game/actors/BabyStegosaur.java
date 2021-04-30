package game.actors;

public class BabyStegosaur extends BabyDinosaur{
    /**
     * Constructor.
     */
    public BabyStegosaur() {
        super("Baby Stegosaur",'s',100);
        dinoChar = 's';
    }

    public boolean isVegetarian(){return true;}
}
