package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.actors.BabyStegosaur;
import game.actors.Player;
import game.enums.Points;

/**
 * Class for Stegosaur Egg object
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see Egg
 */
public class StegosaurEgg extends Egg {
    /**
     * Constructor.
     * @see Egg
     */
    public StegosaurEgg() {
        super("Stegosaur Egg", 'Ṡ');
    }

    /**
     * Tick function to let egg keep track of time, and when egg is due to hatch
     * @param location location of egg
     * @see Player#wallet
     * @see BabyStegosaur
     * @see Location#removeItem(Item)
     * @see Location#addActor(Actor)
     * @see Points
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if(this.getTimeHatch() == 0 && !location.containsAnActor()){
            Player.wallet.addEcoPoints(Points.STEGOSAUR_HATCHED.getPoints());
            location.removeItem(this);
            location.addActor(new BabyStegosaur(this.getGender()));
        }
    }

}
