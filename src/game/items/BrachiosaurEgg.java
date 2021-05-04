package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.actors.BabyBrachiosaur;
import game.actors.Player;
import game.enums.Points;

/**
 * Class for Brachiosaur Egg object
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see Egg
 */
public class BrachiosaurEgg extends Egg{
    /**
     * Constructor.
     * @see Egg
     */
    public BrachiosaurEgg() {
        super("Brachiosaur Egg", 'Ḃ');
    }

    /**
     * Tick function to let egg keep track of time, and when egg is due to hatch
     * @param location location of egg
     * @see Egg#getTimeHatch()
     * @see BabyBrachiosaur
     * @see Player#wallet
     * @see Location#removeItem(Item)
     * @see Location#addActor(Actor)
     * @see Points#BRACHIOSAUR_HATCHED
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if(this.getTimeHatch() == 0 && !location.containsAnActor()
        && random.nextInt(10) >= 3){
            Player.wallet.addEcoPoints(Points.BRACHIOSAUR_HATCHED.getPoints());
            location.removeItem(this);
            location.addActor(new BabyBrachiosaur(this.getGender()));
        }
    }
}
