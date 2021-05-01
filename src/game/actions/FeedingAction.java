package game.actions;


import edu.monash.fit2099.engine.*;
import game.actors.Dinosaur;
import game.actors.Player;
import game.enums.Food;
import game.items.Fruit;

public class FeedingAction extends Action {

    private Dinosaur targetDino;
    private Item foodInventory;

    public FeedingAction(Dinosaur dino, Item item){
        this.targetDino = dino;
        foodInventory = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        EatingAction feedDino = new EatingAction(foodInventory, actor);
        String result = feedDino.execute(targetDino, map);

        for(Item food:actor.getInventory()) {
            //idk why it doesn't work so i do like this first
            System.out.println(food.toString());
        }
//        for(Item food: actor.getInventory()){
//            if(foodInventory.getClass() == food.getClass()){
//                actor.removeItemFromInventory(food);
//            }
//            if(foodInventory.getClass() == Fruit.class){
//                Player.wallet.addEcoPoints(10);
//            }
//        }


        return menuDescription(actor) + "\n" + result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " feeds " + targetDino + " with " + foodInventory + ".";
    }
}
