package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.FeedingAction;
import game.actions.LandingAction;
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
    // Collection of behaviours
    private ArrayList<Behaviour> behaviours;
    // List of edible food list
    private ArrayList<Food> edibleFoodList;
    // Collection of capabilities
    protected ArrayList<DinosaurCapabilities> capabilities;
    // previous location of dinosaur
    private Location prevLoc;
    // gender of dinosaurs
    protected Gender gender;
    // current food level
    protected int foodLevel;
    // min food level before getting unconscious
    protected int minFoodLevel;
    // max food level that dino can have
    protected int maxFoodLevel;
    // current unconscious time
    protected int unconsciousTime;
    // max unconscious time before dying
    protected int maxUnconsciousTime;
    // rot time for corps
    protected int rotTime;
    // pregnancy turns for pregnant dino
    protected int pregnancyTurns;
    // attack turns to check if allosaurs able to attack the same steg
    protected int attackTurns;
    // current water level
    protected int waterLevel;
    // min water level before getting unconscious
    protected int minWaterLevel;
    // max water level that dino can have
    protected int maxWaterLevel;
    // squares that Pterodactyl fly
    protected int squares;
    // to check if Pterodactyl is flying
    protected boolean isFlying;
    //to check if Pterodactyl is on a tree
    protected boolean onTree;

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
        setWaterLevel(60);
        setMinWaterLevel(40);

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
     * @see Actor
     * @see Action
     * @see Actions
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
        else if(lastAction instanceof AttackAction || lastAction instanceof LandingAction){
            return new HungerBehaviour().getAction(this,map);
        }

        return new DinosaurBehaviour().getAction(this, map);
    }

    /**
     * Called once per turn, so that maps can experience the passage of time.
     */
    public void tick(){
        // pregnant
        if(this.isPregnant()) {
            pregnancyTurns++;
        }

        // decrease food level for each tick
        if(foodLevel > 0){
            foodLevel--;
            unconsciousTime = 0;
        }

        // decrease water level for each tick
        if(waterLevel > 0){
            waterLevel--;
            System.out.println(this + " water level: " + waterLevel);
            unconsciousTime = 0;
        }

        // increase unconsciousTime if water or food level == 0
        if(foodLevel == 0 || waterLevel == 0) {
            unconsciousTime++;
        }

        //// INCOMPLETE
        // Landing for Pterodactyl and BabyPterodactyl
        if(this instanceof Pterodactyl || this instanceof BabyPterodactyl){
            if(squares > 0){
                squares--;
                System.out.println(this.getName() + " is flying.");
            }
            else if(squares==0){
                addBehaviour(new LandingBehaviour());
            }
        }
    }

    public void setSquares(int num){
        squares = num;
    }

    public int getSquares(){
        int sq = squares;
        return sq;
    }


    /**
     * Get the name of dinosaur
     * @return name of the dinosaur
     */
    public String getName(){
        return this.name;
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


    // Characteristic (capabilities)
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

    public boolean canLand(){
        if(capabilities.contains(DinosaurCapabilities.FLY)){
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

    // Previous location of dino
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

    // rot time for corpse
    /**
     * Set rot time of corpse that according to the species of dino
     * @param rotTime int that represents rot time of corpse
     */
    public void setRotTime(int rotTime) {
        this.rotTime = rotTime;
    }

    /**
     * Get rot time of corpse that according to the species of dino
     * @return int that represents rot time of corpse
     */
    public int getRotTime(){
        return rotTime;
    }

    // check status
    /**
     * Determine whether the dinosaur is hungry with the food level.
     * @return a boolean, if true dino is hungry, false food is not hungry
     */
    public boolean isHungry(){
        return this.foodLevel < getMinFoodLevel();
    }

    /**
     * Determine whether the dinosaur is thirsty with the water level.
     * @return a boolean, if true dino is thirsty, false dino is not thirsty
     */
    public boolean isThirsty(){
        return this.waterLevel < getMinWaterLevel();
    }

    /**
     * Determine whether a dinosaur is unconscious with food level, water level
     * @return a boolean, if true dino is unconscious, false dino is conscious
     */
    public boolean isUnconscious(){
        return foodLevel <= 0 || waterLevel <= 0;
    }

    /**
     * Determine whether a dinosaur is dead or alive
     * @return a boolean, if true dino is dead, false dino is alive
     */
    public boolean isDead(){
        return (getUnconsciousTime() >= getMaxUnconsciousTime() || hitPoints <= 0);
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

    // behaviours
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

    // Gender
    /**
     * Get the gender of dinosaur
     * @return Gender of dinosaur
     */
    public Gender getGender() {
        return gender;
    }

    // Edible food
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

    // Food Level
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
     * Get the dinosaur's food level
     * @return int represents the food level of dinosaur
     */
    public int getFoodLevel() {
        return foodLevel;
    }

    /**
     * Set the foodLevel of dinosaur
     * @param foodLevel int that represents the food level to be set
     */
    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
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
     * Set the minimum foodLevel of dinosaur
     * @param minFoodLevel int that represents the minimum food level to be set
     */
    public void setMinFoodLevel(int minFoodLevel) {
        this.minFoodLevel = minFoodLevel;
    }

    /**
     * Set the maximum foodLevel of dinosaur
     * @param maxFoodLevel int that represents the maximum food level to be set
     */
    public void setMaxFoodLevel(int maxFoodLevel) {
        this.maxFoodLevel = maxFoodLevel;
    }

    // Unconscious Time
    /**
     * Get the unconscious time of dinosaur
     * @return int represents unconscious time of dinosaur
     */
    public int getUnconsciousTime() {
        return unconsciousTime;
    }

    public void setMaxUnconsciousTime(int maxUnconsciousTime) {
        this.maxUnconsciousTime = maxUnconsciousTime;
    }

    /**
     * Get the max unconscious time of dinosaur
     * @return int represents max unconscious time of dinosaur
     */
    public int getMaxUnconsciousTime() {
        return maxUnconsciousTime;
    }

    // Pregnancy Turns
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

    // Attack Turns
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

    // Water Level
    /**
     * Get the current water level of dinosaurs
     * @return int represents current water level of dinosaurs
     */
    public int getWaterLevel() {
        return waterLevel;
    }

    /**
     * Set the waterLevel of dinosaur
     * @param waterLevel int that represents the water level to be set
     */
    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    /**
     * Set the min water level of dinosaur
     * @param minWaterLevel int that represents the min water level to be set
     */
    public void setMinWaterLevel(int minWaterLevel) {
        this.minWaterLevel = minWaterLevel;
    }

    /**
     * Set the max water level of dinosaur
     * @param maxWaterLevel int that represents the max water level to be set
     */
    public void setMaxWaterLevel(int maxWaterLevel) {
        this.maxWaterLevel = maxWaterLevel;
    }

    /**
     * Get the minimum water level of dinosaurs
     * @return int represents minimum water level of dinosaurs
     */
    public int getMinWaterLevel() {
        return minWaterLevel;
    }

    /**
     * Get the maximum water level of dinosaurs
     * @return int represents maximum water level of dinosaurs
     */
    public int getMaxWaterLevel() {
        return maxWaterLevel;
    }

    /**
     * Increase the water level of dinosaur
     * @param incValue the increment value
     */
    public void incWaterLevel(int incValue){
        waterLevel = Math.min(getWaterLevel()+incValue, getMaxWaterLevel());
    }

    // Flying for Pterodactyl
    /**
     * To set the boolean whether Pterodactyl is flying or not
     * @param flying a boolean, if true means Pterodactyl is flying, false Pterodactyl
     */
    public void setFlying(boolean flying) {
        this.isFlying = flying;
    }

    /**
     * To determine whether Pterodactyl is flying or not
     * @return a boolean, if true means Pterodactyl is flying, false Pterodactyl
     */
    public boolean isFlying() {
        return isFlying;
    }

    public boolean getOnTree(){
        boolean ret = onTree;
        return ret;
    }

    public void setOnTree(boolean rest){
        this.onTree = rest;
    }
}
