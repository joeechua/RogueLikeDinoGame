package game.items;

import edu.monash.fit2099.engine.Location;
import game.actors.BabyPterodactyl;
import game.actors.Player;
import game.enums.Points;

public class PterodactylEgg extends Egg {
    /**
     * Constructor.
     *
     * @see PortableItem
     * @see AllosaurEgg
     */
    public PterodactylEgg() {
        super("Pterodactyl Egg", 'Ṗ');
    }


    @Override
    public void tick(Location location) {
        super.tick(location);
        if(this.getTimeHatch() == 0 && !location.containsAnActor()){
            Player.wallet.addEcoPoints(Points.STEGOSAUR_HATCHED.getPoints());
            location.removeItem(this);
            location.addActor(new BabyPterodactyl(this.getGender()));
        }
    }
}
