package game.items;

import edu.monash.fit2099.engine.Location;
import game.actors.BabyAllosaur;
import game.actors.Player;
import game.enums.Points;

/***
 * Class for Allosaur Egg object
 */
public class AllosaurEgg extends Egg{

    /***
     * Constructor.
     * @see Egg
     */
    public AllosaurEgg() {
        super("Allosaur Egg", 'Ȧ');
    }

    /***
     * Tick function to let egg keep track of time, and when egg is due to hatch
     * @param location location of egg
     * @see Egg
     * @see BabyAllosaur
     * @see Player
     * @see Location
     * @see Points
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if(this.getTimeHatch() == 0 && !location.containsAnActor()){
            Player.wallet.addEcoPoints(Points.ALLOSAUR_HATCHED.getPoints());
            location.removeItem(this);
            location.addActor(new BabyAllosaur());
        }
    }




}
