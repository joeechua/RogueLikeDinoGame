package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.actors.BabyPterodactyl;
import game.actors.Dinosaur;
import game.actors.Player;
import game.enums.Points;

/**
 * Class for Pterodactyl Egg object
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 1.0
 * @see Egg
 */
public class PterodactylEgg extends Egg {
    /**
     * Constructor.
     * @see PortableItem#PortableItem(String, char)
     */
    public PterodactylEgg() {
        super("Pterodactyl Egg", 'Ṗ');
    }


    /**
     * Tick function to help egg keep track of time
     * @param location location of egg
     * @see Egg
     * @see Location#addActor(Actor)
     * @see Location#removeItem(Item)
     * @see Location#containsAnActor()
     * @see game.ecopoint.EcoPointWallet
     * @see Points#getPoints()
     * @see BabyPterodactyl
     * @see Dinosaur#getGender()
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if(this.getTimeHatch() == 0 && !location.containsAnActor()){
            //stegosaur hatched points used because same value
            Player.wallet.addEcoPoints(Points.STEGOSAUR_HATCHED.getPoints());
            location.removeItem(this);
            location.addActor(new BabyPterodactyl(this.getGender()));
        }
    }
}
