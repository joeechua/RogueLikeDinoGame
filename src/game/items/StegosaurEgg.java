package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

public class StegosaurEgg extends Egg {
    /***
     * Constructor.
     */
    public StegosaurEgg() {
        super("Stegosaur Egg", 'Ṡ');
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
    }

    @Override
    public void tick(Location location, Actor actor) {
        super.tick(location, actor);
    }
}
