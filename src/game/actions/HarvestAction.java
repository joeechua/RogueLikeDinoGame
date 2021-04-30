package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.ground.Bush;
import game.items.Fruit;

import java.util.Random;

public class HarvestAction extends Action {
    Item item;

    public HarvestAction(Item item) {
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(item);
        if(item instanceof Fruit){
            Random random = new Random();
            if(random.nextDouble() <= 0.6){
                actor.addItemToInventory(item);
            }
            else {
                return "You search the tree or bush for fruit, but you can’t find any ripe ones.";
            }
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " harvests the " + item;
    }
}
