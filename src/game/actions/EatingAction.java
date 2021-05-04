package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.BabyBrachiosaur;
import game.actors.Brachiosaur;
import game.actors.Dinosaur;
import game.enums.Food;
import game.ground.Bush;
import game.ground.Tree;
import game.items.Corpse;
import game.items.Fruit;
import game.items.ItemCapabilities;

public class EatingAction extends Action {
    private Item targetFood;
    private boolean isFed = false;
    private Ground origin;
    private Location foodLoc;

    public EatingAction(Item targetFood, Ground origin) {
        this.targetFood = targetFood;
        this.origin = origin;
    }

    public EatingAction(Item targetFood, Actor actor) {
        this.targetFood = targetFood;
        this.isFed = true;
    }

    public EatingAction(Item targetFood, Location loc) {
        this.targetFood = targetFood;
        this.foodLoc = loc;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;

        Location dinoLocation = map.locationOf(dino);
        if(origin instanceof Bush && targetFood instanceof Fruit){
            Bush b = (Bush) origin;
            b.setBushFruit(null);
        }
        else if(origin instanceof Tree && targetFood instanceof Fruit){
            Tree t = (Tree) origin;
            t.getTreeFruit().remove(0);
        }
        else if(foodLoc != null){
            foodLoc.removeItem(targetFood);
        }

        int nutritionValue = 0;
        for(Food enumFood: Food.values()){
            if(targetFood.getClass() == enumFood.getClassType()){
                if(isFed && enumFood.getIsVeg()){
                    if(targetFood.getClass() == Fruit.class){
                        nutritionValue = enumFood.getUpLevel("FED_FRUIT");
                    }
                    else{
                        dino.setFoodLevel(dino.getMaxFoodLevel());
                    }
                }
                else if(targetFood.toString().equals("Fruit") &&
                        (dino.getDisplayChar() == 'S' || dino.getDisplayChar() == 's')){
                    nutritionValue = enumFood.getUpLevel("STEG_FRUIT");
                }
                else if(targetFood.toString().equals("Fruit") &&
                        (dino.getDisplayChar() == 'B' || dino.getDisplayChar() == 'b')){
                    nutritionValue = enumFood.getUpLevel("BRACH_FRUIT");
                }
                else if(targetFood.getClass() ==Corpse.class){
                    Corpse c = (Corpse) targetFood;
                    if(c.getOriginDino() instanceof Brachiosaur){
                        nutritionValue = enumFood.getUpLevel("BRACH_CORPSE");
                    }
                    else{
                        nutritionValue = enumFood.getUpLevel("CORPSE");
                    }

                }
                else {
                    nutritionValue = enumFood.getUpLevel(enumFood.name());
                }
            }
        }
        dino.incFoodLevel(nutritionValue);

        // instead of dino instanceof Brachiosaur || dino instanceof BabyBrachiosaur
        // dino has the isLongNeck() where only Brac has the capability of LONGNECK
        if(dino.isLongNeck()){
            if(map.locationOf(actor).getGround().getClass() == Tree.class){
                Tree tree = (Tree) map.locationOf(actor).getGround();
                while(tree.gotFruit() && !(dino.getFoodLevel()==dino.getMaxFoodLevel())){
                    tree.getTreeFruit().remove(0);
                    dino.incFoodLevel(5);
                }
                return menuDescription(actor) + "\nFood level of " + dino + " has increased to " +  dino.getFoodLevel();

            }

        }

        return menuDescription(actor) + "\nFood level of " + dino + " has increased by "  + nutritionValue + " to " +  dino.getFoodLevel();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is eating " + targetFood;
    }
}
