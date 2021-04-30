package game.actions;


import edu.monash.fit2099.engine.*;
import game.actors.Dinosaur;

public class FeedingAction extends Action {

    private Dinosaur targetDino;
    private Item foodInventory;

    public FeedingAction(Dinosaur dino, Item item){
        this.targetDino = dino;
        this.foodInventory = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        EatingAction feedDino = new EatingAction(foodInventory);
        String result = feedDino.execute(targetDino, map);

        for(Item food: actor.getInventory()){
            if(food.getClass() == food.getClass()){
                actor.removeItemFromInventory(food);
            }
        }

        return menuDescription(actor) + "\n" + result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " feeds " + targetDino + " with " + foodInventory + ".";
    }
}
