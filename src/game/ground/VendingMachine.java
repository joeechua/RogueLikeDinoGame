package game.ground;

import edu.monash.fit2099.engine.*;
import game.actions.PurchasingAction;
import game.actors.Player;
import game.enums.VendingItems;
import game.items.*;

import java.util.Scanner;

/**
 * A class that represents VendingMachine.
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 3.0
 * @see edu.monash.fit2099.engine.Ground
 */
public class VendingMachine extends Ground {

    /**
     * Constructor.
     * @see VendingItems
     */
    public VendingMachine() {
        super('⌻');
        // add capability
        for (VendingItems item: VendingItems.values()){
            this.addCapability(item);
        }
    }

    /**
     * Get the purchasing action with the item and price
     * @param price price of the item
     * @param item the items to be purchase
     * @return a PurchasingAction with the price and item
     * @see PurchasingAction
     */
    public PurchasingAction getPurchasingAction(int price, VendingItems item) {
        return new PurchasingAction(price, item);
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A collection of Actions.
     * @see Actor
     * @see Location
     * @see VendingItems
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        for(VendingItems item: VendingItems.values()){
            actions.add(getPurchasingAction(item.getValue(), item));
        }
        return actions;
    }
}
