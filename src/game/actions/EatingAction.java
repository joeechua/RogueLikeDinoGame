package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.Dinosaur;
import game.enums.Food;
import game.items.Fruit;

public class EatingAction extends Action {
    private Item targetFood;
    private boolean isFed = false;

    public EatingAction(Item targetFood) {
        this.targetFood = targetFood;
    }

    public EatingAction(Item targetFood, Actor actor) {
        this.targetFood = targetFood;
        this.isFed = true;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;

        Location dinoLocation = map.locationOf(dino);
        map.at(dinoLocation.x(), dinoLocation.y()).removeItem(targetFood);

        int nutritionValue = 0;
        for(Food enumFood: Food.values()){
            if(targetFood.getClass() == enumFood.getClassType()){
                if(isFed){
                    if(targetFood.getClass() == Fruit.class){
                        nutritionValue = enumFood.getUpLevel("FED_FRUIT");
                    }
                    else{
                        dino.setFoodLevel(dino.getMaxFoodLevel());
                    }
                }
                else if(targetFood.toString().equals("Fruit") && dino.getDisplayChar() == 'S'){
                    nutritionValue = enumFood.getUpLevel("STEG_FRUIT");
                }
                else if(targetFood.toString().equals("Fruit") && dino.getDisplayChar() == 'B'){
                    nutritionValue = enumFood.getUpLevel("BRACH_FRUIT");
                }
                else {
                    nutritionValue = enumFood.getUpLevel(enumFood.name());
                }
            }
//            dino.incFoodLevel(nutritionValue);
        }
        //put here cuz inside loop will increment multiple time
        dino.incFoodLevel(nutritionValue);

        String result = menuDescription(actor) + "\nFood level of " + dino + " has increased by "  + nutritionValue + " to " +  dino.getFoodLevel();
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is eating " + targetFood;
    }
}
