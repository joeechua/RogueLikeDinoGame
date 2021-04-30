package game.actions;

import edu.monash.fit2099.engine.*;

// need VendingItems, EcoPoint and all items

public class PurchasingAction extends Action {
    public PurchasingAction() {
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + " for" + "\nBalance of eco points: ";
    }
}
