package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.Player;
import game.enums.Points;
import game.ground.Bush;
import game.ground.Tree;
import java.util.Random;

/**
 * Harvest Action for Actors.
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see edu.monash.fit2099.engine.Action
 */
public class HarvestAction extends Action {
    Item item;
    String loc;
    Bush bush;
    Tree tree;

    /**
     * Constructor
     *
     * @param item item to harvest
     * @param bush bush which item to harvest from
     */
    public HarvestAction(Item item, Bush bush) {
        this.item = item;
        this.bush = bush;
    }

    /**
     * Constructor
     *
     * @param item item to harvest
     * @param tree tree which item to harvest from
     */
    public HarvestAction(Item item, Tree tree) {
        this.item = item;
        this.tree = tree;
    }

    /**
     * Perform the Harvest action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     * @see Actor
     * @see Player
     * @see game.ecopoint.EcoPointWallet
     * @see Bush
     * @see Tree
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        boolean canHarvest = false;
        loc = "(" + map.locationOf(actor).x() + ", " + map.locationOf(actor).y() + ")";
        Random random = new Random();
        if(random.nextDouble() <= 0.6){
            actor.addItemToInventory(item);
            Player.wallet.addEcoPoints(Points.RIPE_FRUIT_HARVESTED.getPoints());
            if(bush != null){
                bush.setBushFruit(null);
            }
            else if(tree != null){
                tree.getTreeFruit().remove(0);
            }
        }
        else {
            return "You search the tree or bush for fruit, but you can’t find any ripe ones.";
        }
        return menuDescription(actor) + " at " + loc;
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " harvests the " + item;
    }
}
