package game.actions;


import edu.monash.fit2099.engine.*;
import game.actors.Dinosaur;
import game.actors.Player;
import game.enums.Points;
import game.items.Fruit;

/**
 * Feeding Action for player to feed Actors.
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see edu.monash.fit2099.engine.Action
 */
public class FeedingAction extends Action {

    private Dinosaur targetDino;
    private Item foodInventory;

    /**
     * Constructor
     *
     * @param dino the dinosaur feed by player
     * @param item the item to feed on dinosaur
     */
    public FeedingAction(Dinosaur dino, Item item){
        this.targetDino = dino;
        foodInventory = item;
    }

    /**
     * Perform the Feeding action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     * @see EatingAction
     * @see Actor
     * @see Player
     * @see game.ecopoint.EcoPointWallet
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // dino performs eating action
        EatingAction feedDino = new EatingAction(foodInventory, actor);
        String result = feedDino.execute(targetDino, map);
        Item remItem = null;

        // remove item from actor's inventory
        for(Item food: actor.getInventory()){
            if(foodInventory.getClass() == food.getClass()){
                remItem = food;
            }
            // add eco points if player feed dinosaur with fruits
            if(foodInventory.getClass() == Fruit.class){
                Player.wallet.addEcoPoints(Points.FRUIT_FED.getPoints());
            }
        }
        actor.removeItemFromInventory(remItem);

        return menuDescription(actor) + "\n" + result;
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " feeds " + targetDino + " with " + foodInventory + ".";
    }
}
