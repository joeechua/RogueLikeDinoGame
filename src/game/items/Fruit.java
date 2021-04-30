package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.actions.HarvestAction;

public class Fruit extends Item {
    private int rotTime = 15;
    /***
     * Constructor.
     */
    public Fruit() {
        super("Fruit", 'f', true);
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

    public HarvestAction getHarvestAction() {return new HarvestAction(this);}
}
