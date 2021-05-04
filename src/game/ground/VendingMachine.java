package game.ground;

import edu.monash.fit2099.engine.*;
import game.actions.PurchasingAction;
import game.actors.Player;
import game.enums.VendingItems;
import game.items.*;

import java.util.Scanner;

public class VendingMachine extends Ground {

    /**
     * Constructor.
     */
    public VendingMachine() {
        super('⌻');
        for (VendingItems item: VendingItems.values()){
            addCapability(item);
        }
    }

    public static PurchasingAction getPurchasingAction(int price, VendingItems item) {
        return new PurchasingAction(price, item);
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        for(VendingItems item: VendingItems.values()){
            actions.add(getPurchasingAction(item.getValue(), item));
        }
        return actions;
    }
}
