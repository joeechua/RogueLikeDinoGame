package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.Player;
import game.ecopoint.EcoPointWallet;
import game.enums.VendingItems;
import game.items.*;

/**
 * Purchasing Action for Actors.
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see edu.monash.fit2099.engine.Action
 */
public class PurchasingAction extends Action {

    private int itemPrice;
    private VendingItems item;

    /**
     * Constructor
     * @param itemPrice price of the vending item
     * @param item vending item to be purchased
     */
    public PurchasingAction(int itemPrice, VendingItems item) {
        this.itemPrice = itemPrice;
        this.item = item;
    }

    /**
     * Perform the Purchasing action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     * @see EcoPointWallet
     * @see Actor
     * @see Player
     * @see Item
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Item purchasedItem = getPurchasedItem(item);
        EcoPointWallet playerWallet = Player.wallet;
        if(playerWallet.getEcoPoints() >= itemPrice){
            actor.addItemToInventory(purchasedItem);
            playerWallet.payEcoPoints(itemPrice);
            return menuDescription(actor);
        }
        return actor + " has insufficient eco points to purchase " + purchasedItem + "\nLack of eco points: " + (itemPrice - playerWallet.getEcoPoints());
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + item + " for " + item.getValue() + "; Current eco points: " + Player.wallet.getEcoPoints();
    }

    /**
     * Create the vending item and return the item
     * @param item Vending Items to be purchased
     * @return Vending Items to be purchased
     * @see Item
     * @see Fruit
     * @see LaserGun
     * @see AllosaurEgg
     * @see StegosaurEgg
     * @see BrachiosaurEgg
     * @see CarnivoreMealKit
     * @see VegetarianMealKit
     */
    public Item getPurchasedItem(VendingItems item){
        switch (item){
            case FRUIT: return new Fruit();
            case LASER_GUN: return new LaserGun();
            case ALLOSAUR_EGG: return new AllosaurEgg();
            case STEGOSAUR_EGG: return new StegosaurEgg();
            case BRACHIOSAUR_EGG: return new BrachiosaurEgg();
            case CRANIVORE_MEAL_KIT: return new CarnivoreMealKit();
            case VEGETARIAN_MEAL_KIT: return new VegetarianMealKit();
            default: return null;
        }
    }
}
