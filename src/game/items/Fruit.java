package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class Fruit extends Item {
    private int rotTime = 15;
    /***
     * Constructor.
     *  @param name the name of this Item
     */
    public Fruit(String name) {
        super(name, 'f', true);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);

        if(rotTime == 0){
            currentLocation.removeItem(this);
        }
        else{
            this.rotTime--;
        }


    }
}
