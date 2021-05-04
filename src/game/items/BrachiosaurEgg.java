package game.items;

import edu.monash.fit2099.engine.Location;
import game.actors.BabyBrachiosaur;
import game.actors.Player;
import game.enums.Points;

/***
 * Class for Brachiosaur Egg object
 */
public class BrachiosaurEgg extends Egg{
    /***
     * Constructor.
     * @see Egg
     */
    public BrachiosaurEgg() {
        super("Brachiosaur Egg", 'Ḃ');
    }

    /***
     * Tick function to let egg keep track of time, and when egg is due to hatch
     * @param location location of egg
     * @see BabyBrachiosaur
     * @see Player
     * @see Location
     * @see Points
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if(this.getTimeHatch() == 0 && !location.containsAnActor()
        && random.nextInt(10) >= 3){
            Player.wallet.addEcoPoints(Points.BRACHIOSAUR_HATCHED.getPoints());
            location.removeItem(this);
            location.addActor(new BabyBrachiosaur());
        }
    }
}
