package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.Player;
import game.ground.Bush;
import game.ground.Dirt;
import game.ground.Tree;
import game.items.Fruit;

import java.util.Random;

public class HarvestAction extends Action {
    Item item;
    String loc;
    Bush bush;
    Tree tree;

    public HarvestAction(Item item, Bush bush) {
        this.item = item;
        this.bush = bush;
    }

    public HarvestAction(Item item, Tree tree) {
        this.item = item;
        this.tree = tree;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        boolean canHarvest = false;
        loc = "(" + map.locationOf(actor).x() + ", " + map.locationOf(actor).y() + ")";
        Random random = new Random();
        if(random.nextDouble() <= 0.6){
            actor.addItemToInventory(item);
            Player.wallet.addEcoPoints(10);
            if(bush != null){
                System.out.println("bush fruit");
                bush.setBushFruit(null);
            }
            else if(tree != null){
                System.out.println("tree fruit");
                tree.getTreeFruit().remove(0);
            }
        }
        else {
            return "You search the tree or bush for fruit, but you can’t find any ripe ones.";
        }
        return menuDescription(actor) + " at " + loc;

        //return "Can only harvest from Trees or Bushes";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " harvests the " + item;
    }
}
