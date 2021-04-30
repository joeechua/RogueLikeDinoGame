package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

public class AllosaurEgg extends Egg{
    /***
     * Constructor.
     * @param name the name of this Item
     */
    public AllosaurEgg(String name) {
        super(name, 'Ȧ', true);
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
