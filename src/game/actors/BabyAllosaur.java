package game.actors;

public class BabyAllosaur extends BabyDinosaur{
    /**
     * Constructor.
     */
    public BabyAllosaur() {
        super("Baby Allosaur",'a',1000);
        dinoChar = 'a';
    }

    public boolean isVegetarian(){return false;}
}
