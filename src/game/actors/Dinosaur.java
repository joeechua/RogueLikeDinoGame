package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.FeedingAction;
import game.behaviours.*;
import game.enums.DinosaurCapabilities;
import game.enums.Food;
import game.enums.Gender;
import game.ground.Bush;
import game.ground.Dirt;
import java.util.ArrayList;
import java.util.Random;

/**
 * A dinosaur class.
 *
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see Actor
 */

public abstract class Dinosaur extends Actor {

    private Random random = new Random();
    private ArrayList<Behaviour> behaviours;
    private ArrayList<Food> edibleFoodList;
    private Location prevLoc;
    protected Gender gender;
    protected int foodLevel;
    protected int initFoodLevel;
    protected int minFoodLevel;
    protected int maxFoodLevel;
    protected int unconsciousTime;
    protected int maxUnconsciousTime;
    protected int rotTime;
    protected int pregnancyTurns;
    protected int attackTurns;
    protected ArrayList<DinosaurCapabilities> capabilities;
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @see DinosaurBehaviour
     * @see BreedBehaviour
     * @see GrowBehaviour
     */
    public Dinosaur(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        pregnancyTurns = 0;
        behaviours = new ArrayList<>();
        capabilities = new ArrayList<>();
        edibleFoodList = new ArrayList<>();
        if(this instanceof Brachiosaur){
            minFoodLevel = 140;
            maxFoodLevel = 160;
            maxUnconsciousTime = 15;
            initFoodLevel = foodLevel = 100;
            rotTime = 40;
        }
        else{
            minFoodLevel = 90;
            maxFoodLevel = 100;
            maxUnconsciousTime = 20;
            initFoodLevel = foodLevel = 50;
            rotTime = 20;
        }
        addBehaviour(new DinosaurBehaviour());
        // if adult add breed behaviour
        if(!(this instanceof BabyDinosaur)){
            addBehaviour(new BreedBehaviour());
        }
        // if baby add grow behaviour
        else {
            addBehaviour(new GrowBehaviour());
        }
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     * @see GameMap
     * @see HungerBehaviour
     * @see DinosaurBehaviour
     */
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
        //after attack can eat
        else if(lastAction instanceof AttackAction){
            return new HungerBehaviour().getAction(this,map);
        }
        return new DinosaurBehaviour().getAction(this, map);
    }

    /**
     * Called once per turn, so that maps can experience the passage of time.
     */
    public void tick(){
        if(this.isPregnant()) {
            pregnancyTurns++;
        }
        if(foodLevel > 0){
            foodLevel--;
            unconsciousTime = 0;
        }
        else {
            unconsciousTime++;
        }
    }

    /**
     * Set the edibleFoodList
     * @param edibleFoodList food list that contains all edible foods
     */
    public void setEdibleFoodList(ArrayList<Food> edibleFoodList) {
        this.edibleFoodList = edibleFoodList;
    }

    /**
     * Get the edibleFoodList
     * @return array list that contains all edible foods
     */
    public ArrayList<Food> getEdibleFoodList(){
        return this.edibleFoodList;
    }

    /**
     * Get the name of dinosaur
     * @return name of the dinosaur
     */
    public String getName(){
        return this.name;
    }

    /**
     * Get previous location of dinosaur
     * @return a previous location that the dinosaur come from
     */
    public Location getPrevLoc(){return this.prevLoc;}

    /**
     * Set previous location of dinosaur
     * @param prev a previous location that the dinosaur come from
     */
    public void setPrevLoc(Location prev){
        this.prevLoc = prev;
    }

    /**
     * Get rot time of corpse that according to the species of dino
     * @return int that represents rot time of corpse
     */
    public int getRotTime(){
        return rotTime;
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     * @see Actor
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        // player to attack dino
        actions.add(new AttackAction(this));
        for(Item item: otherActor.getInventory()){
            if(this.canEat(item)){
                actions.add(new FeedingAction(this, item));
            }
        }
        return actions;
    }

    /**
     * Determine whether the dinosaur is herbivore or carnivore
     * @return a boolean, if true dino is herbivorous, false dino is carnivore
     * @see DinosaurCapabilities
     */
    public boolean isHerbivore(){
        if(capabilities.contains(DinosaurCapabilities.HERBIVORE)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Determine whether the dinosaur has long neck.
     * @return a boolean, if true dino has long neck, false dino doesn't have long neck
     * @see DinosaurCapabilities
     */
    public boolean isLongNeck(){
        if(capabilities.contains(DinosaurCapabilities.LONG_NECK)){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Determine whether the food item is edible for the dinosaur.
     * @param food a food item
     * @return a boolean, if true food is edible, false food is not edible
     * @see Food
     */
    public boolean canEat(Item food){
        for(Food f: getEdibleFoodList()){
            if(food.getClass() == f.getClassType()){
                return true;
            }
        }
        return false;
    }

    /**
     * Determine whether the dinosaur is hungry with the food level.
     * @return a boolean, if true dino is hungry, false food is not hungry
     */
    public boolean isHungry(){
        return this.foodLevel < getMinFoodLevel();
    }

    /**
     * Determine whether a dinosaur is unconscious with food level
     * @return a boolean, if true dino is unconscious, false dino is conscious
     */
    public boolean isUnconscious(){
        return foodLevel <= 0;
    }

    /**
     * Determine whether a dinosaur is dead or alive
     * @return a boolean, if true dino is dead, false dino is alive
     */
    public boolean isDead(){
        return (getUnconsciousTime() >= getMaxUnconsciousTime() || hitPoints <= 0);
    }

    /**
     * Increase the food level of dinosaur
     * @param incValue the increment value
     */
    public void incFoodLevel(int incValue){
        foodLevel = Math.min(getFoodLevel()+incValue, getMaxFoodLevel());
    }

    /**
     * Decrease the food level of dinosaur
     * @param decValue the decrement value
     */
    public void decFoodLevel(int decValue){
        foodLevel = Math.max(getFoodLevel()-decValue, 0);
    }

    /**
     * Get the array list of behaviours of dinosaur
     * @return array list of behaviours
     */
    public ArrayList<Behaviour> getBehaviours() {
        return behaviours;
    }

    /**
     * Add new behaviour into dinosaur's behaviours array list
     * @param behaviour behaviour of dinosaur has to be added to behaviours
     */
    public void addBehaviour(Behaviour behaviour){
        behaviours.add(behaviour);
    }

    /**
     * Remove behaviour into dinosaur's behaviours array list
     * @param behaviour behaviour of dinosaur has to be removed to behaviours
     */
    public void removeBehaviour(Behaviour behaviour){
        behaviours.remove(behaviour);
    }

    /**
     * Get the dinosaur's food level
     * @return int represents the food level of dinosaur
     */
    public int getFoodLevel() {
        return foodLevel;
    }

    /**
     * Get the gender of dinosaur
     * @return Gender of dinosaur
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Set the foodLevel of dinosaur
     * @param foodLevel int that represents the food level to be set
     */
    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }

    /**
     * Get the unconscious time of dinosaur
     * @return int represents unconscious time of dinosaur
     */
    public int getUnconsciousTime() {
        return unconsciousTime;
    }

    /**
     * Get the minimum food level of dinosaurs
     * @return int represents minimum food level of dinosaurs
     */
    public int getMinFoodLevel() {
        return minFoodLevel;
    }

    /**
     * Get the maximum food level of dinosaurs
     * @return int represents maximum food level of dinosaurs
     */
    public int getMaxFoodLevel() {
        return maxFoodLevel;
    }

    /**
     * Get the max unconscious time of dinosaur
     * @return int represents max unconscious time of dinosaur
     */
    public int getMaxUnconsciousTime() {
        return maxUnconsciousTime;
    }

    /**
     * Get the pregnancy turns
     * @return int represents the pregnancy turns
     */
    public int getPregnancyTurns() {
        return pregnancyTurns;
    }
    /**
     * Set the pregnancy turns
     * @param t int represents the pregnancy turns
     */
    public void setPregnancyTurns(int t){
        if(t >= 0){
            pregnancyTurns = t;
        }
    }

    /**
     * Set the attack turns of dinosaur
     * @param attackTurns int that represents attack turns
     */
    public void setAttackTurns(int attackTurns) {
        this.attackTurns = attackTurns;
    }

    /**
     * Get the attack turns of dinosaur
     * @return int that represents attack turns
     */
    public int getAttackTurns() {
        return attackTurns;
    }

    /**
     * Determine whether the dino is pregnant or not
     * @return a boolean, if true dino is pregnant, false dino is not
     */
    public boolean isPregnant(){
        for(Behaviour behaviour: behaviours){
            if(behaviour instanceof PregnantBehaviour){
                return true;
            }
        }
        return false;
    }

    /**
     * Get the capabilities of dinosaur
     * @return ArrayList that contains the capabilities of dinosaur
     */
    public ArrayList<DinosaurCapabilities> getCapabilities() {
        return capabilities;
    }
}
