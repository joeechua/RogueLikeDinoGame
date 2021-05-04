package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import game.actors.BabyStegosaur;
import game.actors.Player;

/***
 * Class for Stegosaur Egg object
 */
public class StegosaurEgg extends Egg {
    /***
     * Constructor.
     * @see Egg
     */
    public StegosaurEgg() {
        super("Stegosaur Egg", 'Ṡ');
    }

    /***
     * Tick function to let egg keep track of time, and when egg is due to hatch
     * @param location location of egg
     * @see Player
     * @see BabyStegosaur
     * @see Location
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if(this.getTimeHatch() == 0 && !location.containsAnActor()){
            Player.wallet.addEcoPoints(100);
            location.removeItem(this);
            location.addActor(new BabyStegosaur());
        }
    }

}
