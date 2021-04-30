package game.actors;

import edu.monash.fit2099.engine.*;
import game.behaviours.Behaviour;
import game.behaviours.BreedBehaviour;
import game.items.Egg;

import java.util.ArrayList;

// need Gender enums

public abstract class Dinosaur extends Actor {

    private ArrayList<Behaviour> behaviours;
//    private Gender gender;
    protected int foodLevel;
    protected int initFoodLevel;
    protected int unconsciousTime;
    protected final int MAX_FOOD_LEVEL;
    public static Egg egg;

    public ArrayList<Behaviour> getBehaviours() {
        return behaviours;
    }

    public int getFoodLevel() {
        return foodLevel;
    }

    public int getInitFoodLevel() {
        return initFoodLevel;
    }

    public int getUnconsciousTime() {
        return unconsciousTime;
    }

    public int getMAX_FOOD_LEVEL() {
        return MAX_FOOD_LEVEL;
    }

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Dinosaur(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        // gender
        behaviours = new ArrayList<Behaviour>();
        initFoodLevel = foodLevel = 0;
        if(this instanceof Brachiosaur){
            MAX_FOOD_LEVEL = 160;
        }
        else{
            MAX_FOOD_LEVEL = 100;
        }
//        if(!(this instanceof BabyDinosaur)){
//            behaviours.add(new BreedBehaviour());
//        }
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        tick();
        return null;
    }

    public void tick(){
        if(foodLevel > 0){
            foodLevel--;
            unconsciousTime = 0;
        }
        else {
            unconsciousTime++;
        }
    }

    public void increaseFoodLevel(int incValue){
        foodLevel = Math.min(foodLevel+incValue, MAX_FOOD_LEVEL);
    }

    public static Egg getEgg() {
        return egg;
    }

    public static void setEgg(Egg egg) {
        Dinosaur.egg = egg;
    }
}
