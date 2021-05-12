package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.Brachiosaur;
import game.actors.Dinosaur;
import game.actors.Pterodactyl;
import game.enums.Food;
import game.ground.Bush;
import game.ground.Tree;
import game.items.Corpse;
import game.items.Fruit;

/**
 * Eating Action for Actors.
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see edu.monash.fit2099.engine.Action
 */

public class EatingAction extends Action {
    private Item targetFood;
    private Actor targetDino;
    private boolean isFed = false;
    private Ground origin;
    private Location foodLoc;

    /**
     * Constructor
     *
     * @param targetFood the food that will be eat by dinosaur
     * @param origin ground item at current location
     */
    public EatingAction(Item targetFood, Ground origin) {
        this.targetFood = targetFood;
        this.origin = origin;
    }

    /**
     * Constructor
     *
     * @param targetFood the food that will be eat by dinosaur
     * @param actor the actor performing the action
     */
    public EatingAction(Item targetFood, Actor actor) {
        this.targetFood = targetFood;
        this.isFed = true;
    }

    /**
     * Constructor
     *
     * @param targetFood the food that will be eat by dinosaur
     * @param loc location of the targetFood
     */
    public EatingAction(Item targetFood, Location loc) {
        this.targetFood = targetFood;
        this.foodLoc = loc;
    }

    public EatingAction(Actor targetFood,Location loc){
        this.targetDino = targetFood;
        this.foodLoc = loc;
    }

    /**
     * Performing the Eating action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     * @see Bush
     * @see Tree
     * @see Food
     * @see Corpse
     * @see Dinosaur
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;
        int removeCount = 1;

        // origin ground has bush
        if(origin instanceof Bush && targetFood instanceof Fruit){
            Bush b = (Bush) origin;
            if(b.gotFruit()) {
                b.getBushFruit().remove(0);
            }
        }
        // origin ground has tree
        else if(origin instanceof Tree && targetFood instanceof Fruit){
            Tree t = (Tree) origin;
            if(t.gotFruit()) {
                t.getTreeFruit().remove(0);
            }
        }
        else if(targetDino != null){
            foodLoc.map().removeActor(targetDino);
        }

        int nutritionValue = 0;
        for(Food enumFood: Food.values()){
            if(targetFood != null && targetFood.getClass() == enumFood.getClassType()){
                // player feeds on dinosaur
                if(isFed && enumFood.getIsVeg()){
                    // feeds fruit
                    if(targetFood.getClass() == Fruit.class){
                        nutritionValue = enumFood.getUpLevel("FED_FRUIT");
                    }
                    // feeds meal kit
                    else{
                        dino.setFoodLevel(dino.getMaxFoodLevel());
                    }
                }
                // dinosaur eats fruit on their own
                else if(targetFood.toString().equals("Fruit") &&
                        (dino.getDisplayChar() == 'S' || dino.getDisplayChar() == 's')){
                    nutritionValue = enumFood.getUpLevel("STEG_FRUIT");
                }
                else if(targetFood.toString().equals("Fruit") &&
                        (dino.getDisplayChar() == 'B' || dino.getDisplayChar() == 'b')){
                    nutritionValue = enumFood.getUpLevel("BRACH_FRUIT");
                }
                // eats corpse
                else if(targetFood.getClass() == Corpse.class){
                    Corpse c = (Corpse) targetFood;
                    if(dino instanceof  Pterodactyl){
                        removeCount = c.getRemoveCount();
                    }
                    if(c.getOriginDino() instanceof Brachiosaur){
                        nutritionValue = enumFood.getUpLevel("BRACH_CORPSE");
                    }
                    else if(c.getOriginDino() instanceof Pterodactyl){
                        nutritionValue = enumFood.getUpLevel("PTER_CORPSE");
                    }
                    else{
                        nutritionValue = enumFood.getUpLevel("CORPSE");
                    }
                }
                // eats egg
                else {
                    nutritionValue = enumFood.getUpLevel(enumFood.name());
                }
            }
            else if(targetDino != null){
                nutritionValue = Food.PTERO.getUpLevel("PTERO");
            }
        }
        if(foodLoc != null && targetFood != null && removeCount == 1){
            foodLoc.removeItem(targetFood);
        }

        if(targetFood != null && targetFood.getClass() == Corpse.class && dino instanceof Pterodactyl){
            Corpse c = (Corpse) targetFood;
            c.setRemoveCount(removeCount-1);
            nutritionValue = 10;
        }

        // increase foodlevel
        dino.incFoodLevel(nutritionValue);

        // if dino has long neck (Brachiosaur) - eats fruit from tree
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

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is eating " + targetFood;
    }
}
