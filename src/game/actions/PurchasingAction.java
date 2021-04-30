package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.Player;
import game.ecopoint.EcoPointWallet;
import game.enums.VendingItems;
import game.items.*;

public class PurchasingAction extends Action {

    private int itemPrice;
    private VendingItems item;

    public PurchasingAction(int itemPrice, VendingItems item) {
        this.itemPrice = itemPrice;
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Item purchasedItem = getPurchasedItem(item);
        EcoPointWallet playerWallet = Player.wallet;
        if(playerWallet.getEcoPoints() >= itemPrice){
            actor.addItemToInventory(purchasedItem);
            playerWallet.payEcoPoints(itemPrice);
            return menuDescription(actor);
        }
        return actor + " has insufficent eco points to purchase " + purchasedItem;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + item + " for" + item.getValue() + "\nBalance of eco points: " + Player.wallet.getEcoPoints();
    }

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
