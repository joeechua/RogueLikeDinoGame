package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.Dinosaur;

// need Food enums and Dinosaur class

public class EatingAction extends Action {
    private Item targetFood;

    public EatingAction(Item targetFood) {
        this.targetFood = targetFood;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;

        Location dinoLocation = map.locationOf(dino);
        dinoLocation.removeItem(targetFood);

        // get nutritionValue of targetFood, increase foodLevel by value

        // rmb to add foodLevel of dino and nutritionValue


        String result = menuDescription(actor) + "\nFood level of " + dino + " has increased by ";// + food level + " by " + nutritionValue
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is eating " + targetFood;
    }
}
