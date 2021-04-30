package game.items;

import edu.monash.fit2099.engine.Item;
import game.actions.HatchAction;
import game.actors.Dinosaur;

public abstract class Egg extends Item {
    private int timeHatch;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Egg(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }


    public Dinosaur toHatch(){
        if(timeHatch == 0){
            //HatchAction();
        }
        return null;
    }
}
