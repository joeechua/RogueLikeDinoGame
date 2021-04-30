package game.actors;

import edu.monash.fit2099.engine.*;
import game.behaviours.Behaviour;
import game.behaviours.BreedBehaviour;

import java.util.ArrayList;

// need Gender enums

public abstract class Dinosaur extends Actor {

    private ArrayList<Behaviour> behaviours;
//    private Gender gender;
    protected int foodLevel;
    protected int initFoodLevel;
    protected int unconciousTime;
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
        if(!(this instanceof BabyDinosaur)){
            behaviours.add(new BreedBehaviour());
        }
    }

//    @Override
//    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
//        tick();
//        return new;
//    }

    public void tick(){
        if(foodLevel > 0){
            foodLevel--;
            unconciousTime = 0;
        }
        else {
            unconciousTime++;
        }
    }


}
