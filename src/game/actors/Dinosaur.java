package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.behaviours.*;
import game.enums.DinosaurCapabilities;
import game.enums.Gender;
import game.ground.Bush;
import game.ground.Dirt;

import java.util.ArrayList;
import java.util.Random;

public abstract class Dinosaur extends Actor {

    private Random random = new Random();
    private ArrayList<Behaviour> behaviours;
    protected Gender gender;
    protected int foodLevel;
    protected int initFoodLevel;
    protected int minFoodLevel;
    protected int maxFoodLevel;
    protected int unconsciousTime;
    protected int maxUnconsciousTime;
    protected int turns;
    protected int attackTurns;
    protected ArrayList<DinosaurCapabilities> capabilities;
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Dinosaur(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        turns = 0;
        behaviours = new ArrayList<>();
        capabilities = new ArrayList<>();
        if(this instanceof Brachiosaur){
            minFoodLevel = 140;
            maxFoodLevel = 160;
            maxUnconsciousTime = 15;
            initFoodLevel = foodLevel = 100;
        }
        else{
            minFoodLevel = 90;
            maxFoodLevel = 100;
            maxUnconsciousTime = 20;
            initFoodLevel = foodLevel = 50;
        }
        if(!(this instanceof BabyDinosaur)){
            addBehaviour(new BreedBehaviour());
        }
        else {
            addBehaviour(new GrowBehaviour(getTurns()));
        }
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        tick();
        if(map.locationOf(this).getGround() != null){
            if((this instanceof Brachiosaur || this instanceof BabyBrachiosaur) && map.locationOf(this).getGround().equals(Bush.class)){
                if(random.nextDouble() <= 0.5){
                    map.locationOf(this).setGround(new Dirt());
                }
            }
        }
        return new DinosaurBehaviour().getAction(this, map);
    }

    public void tick(){
        turns++;
        if(foodLevel > 0){
            foodLevel--;
            unconsciousTime = 0;
        }
        else {
            unconsciousTime++;
        }
    }

    public String getName(){
        return this.name;
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        actions.add(new AttackAction(this));
        return actions;
    }

    public boolean isHerbivore(){
        if(capabilities.contains(DinosaurCapabilities.HERBIVORE)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isLongNeck(){
        if(capabilities.contains(DinosaurCapabilities.LONG_NECK)){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isHungry(){
        return this.foodLevel < getMinFoodLevel();
    }

    public boolean isUnconscious(){
        return foodLevel <= 0;
    }

    public boolean isDead(){
        return (getUnconsciousTime() >= getMaxUnconsciousTime() || hitPoints <= 0);
    }

    public void incFoodLevel(int incValue){
        foodLevel = Math.min(getFoodLevel()+incValue, getMaxFoodLevel());
    }

    public void decFoodLevel(int decValue){
        foodLevel = Math.max(getFoodLevel()-decValue, 0);
    }

    public ArrayList<Behaviour> getBehaviours() {
        return behaviours;
    }

    public void addBehaviour(Behaviour behaviour){
        behaviours.add(behaviour);
    }

    public void removeBehaviour(Behaviour behaviour){
        behaviours.remove(behaviour);
    }

    public int getFoodLevel() {
        return foodLevel;
    }

    public Gender getGender() {
        return gender;
    }


    public int getUnconsciousTime() {
        return unconsciousTime;
    }

    public int getMinFoodLevel() {
        return minFoodLevel;
    }

    public int getMaxFoodLevel() {
        return maxFoodLevel;
    }

    public int getMaxUnconsciousTime() {
        return maxUnconsciousTime;
    }

    public int getTurns() {
        return turns;
    }

    public void setAttackTurns(int attackTurns) {
        this.attackTurns = attackTurns;
    }

    public int getAttackTurns() {
        return attackTurns;
    }

    public boolean isPregnant(){
        for(Behaviour behaviour: behaviours){
            if(behaviour instanceof PregnantBehaviour){
                return true;
            }
        }
        return false;
    }

    public NumberRange[] getRange(Location location, int xRange, int yRange){
        NumberRange[] ret;
        int s = Math.max(location.map().getXRange().min(), location.x() - xRange);
        int e = Math.min(location.map().getXRange().max(), location.x() + xRange);
        NumberRange x = new NumberRange(s,e-s+1);

        s = Math.max(location.map().getYRange().min(), location.x() - yRange);
        e = Math.min(location.map().getYRange().max(), location.x() + yRange);
        NumberRange y = new NumberRange(s,e-s+1);

        ret = new NumberRange[]{x, y};
        return ret;
    }

    public ArrayList<DinosaurCapabilities> getCapabilities() {
        return capabilities;
    }
}
