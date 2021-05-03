package game.items;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.actions.EatingAction;
import game.actions.HarvestAction;

import java.util.List;

public class Fruit extends PortableItem {
    private int rotTime = 15;
    private boolean onTree;
    private boolean inBag;
    /***
     * Constructor.
     */
    public Fruit() {
        super("Fruit", 'f');
        capabilities.addCapability(ItemCapabilities.EATEN);
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
        else if(!onTree || !inBag){
            this.rotTime--;
        }

    }

    @Override
    public List<Action> getAllowableActions() {
        return super.getAllowableActions();
    }

    public boolean isOnTree() {
        return onTree;
    }

    public boolean setOnTree(boolean stat){
        onTree = stat;
        return true;
    }

    public boolean isInBag() {
        return inBag;
    }

    public void setInBag(boolean inBag) {
        this.inBag = inBag;
    }

    //public HarvestAction getHarvestAction() {return new HarvestAction(this);}

    //public EatingAction getEatingAction() {return new EatingAction(this);}
}
