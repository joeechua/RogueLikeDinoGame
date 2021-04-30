package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.Dinosaur;
import game.enums.Food;

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

        int nutritionValue = 0;
        for(Food enumFood: Food.values()){
            if(targetFood.getClass() == enumFood.getClassType()){
                nutritionValue = enumFood.getUpLevel(targetFood);
                dino.increaseFoodLevel(nutritionValue);
                break;
            }
        }

        String result = menuDescription(actor) + "\nFood level of " + dino + " has increased by " + dino.getFoodLevel() + " by " + nutritionValue;
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is eating " + targetFood;
    }
}
