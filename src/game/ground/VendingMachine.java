package game.ground;

import edu.monash.fit2099.engine.*;
import game.actions.PurchasingAction;
import game.actors.Player;
import game.enums.VendingItems;
import game.items.*;

import java.util.Scanner;

/**
 * A class that represents VendingMachine.
 * @see Ground
 */
public class VendingMachine extends Ground {

    /**
     * Constructor.
     */
    public VendingMachine() {
        super('⌻');
        // add capability
        for (VendingItems item: VendingItems.values()){
            addCapability(item);
        }
    }

    /**
     * Get the purchasing action with the item and price
     * @param price price of the item
     * @param item the items to be purchase
     * @return a PurchasingAction with the price and item
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
