package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.EatingAction;
import game.actions.FeedingAction;
import game.behaviours.*;
import game.enums.DinosaurCapabilities;
import game.enums.Food;
import game.enums.Gender;
import game.ground.Bush;
import game.ground.Dirt;
import game.items.Egg;
import game.items.Fruit;

import java.util.ArrayList;
import java.util.Random;

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
     * @see GameMap
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
     * @return a
     */
    public boolean setPrevLoc(Location prev){
        this.prevLoc = prev;
        return true;
    }

    /**
     *
     * @return int that represents rot time
     */
    public int getRotTime(){
        return rotTime;
    }

    /**
     *
     */
    public void decRotTime(){
        rotTime = Math.max(getRotTime()-1, 0);
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
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
     *
     * @return
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
     *
     * @return
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
     *
     * @param food
     * @return
     */
    public boolean canEat(Item food){
        for(Food f: getEdibleFoodList()){
            if(food.getClass() == f.getClassType()){
                return true;
            }
            else if(this.hasCapability(DinosaurCapabilities.CARNIVORE) && food instanceof Egg){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param t
     */
    public void setPregnancyTurns(int t){
        if(t >= 0){
            pregnancyTurns = t;
        }
    }

    /**
     *
     * @return
     */
    public boolean isHungry(){
        return this.foodLevel < getMinFoodLevel();
    }

    /**
     *
     * @return
     */
    public boolean isUnconscious(){
        return foodLevel <= 0;
    }

    /**
     *
     * @return
     */
    public boolean isDead(){
        return (getUnconsciousTime() >= getMaxUnconsciousTime() || hitPoints <= 0);
    }

    /**
     *
     * @param incValue
     */
    public void incFoodLevel(int incValue){
        foodLevel = Math.min(getFoodLevel()+incValue, getMaxFoodLevel());
    }

    /**
     *
     * @param decValue
     */
    public void decFoodLevel(int decValue){
        foodLevel = Math.max(getFoodLevel()-decValue, 0);
    }

    /**
     *
     * @return
     */
    public ArrayList<Behaviour> getBehaviours() {
        return behaviours;
    }

    /**
     *
     * @param behaviour
     */
    public void addBehaviour(Behaviour behaviour){
        behaviours.add(behaviour);
    }

    /**
     *
     * @param behaviour
     */
    public void removeBehaviour(Behaviour behaviour){
        behaviours.remove(behaviour);
    }

    /**
     *
     * @return
     */
    public int getFoodLevel() {
        return foodLevel;
    }

    /**
     *
     * @return
     */
    public Gender getGender() {
        return gender;
    }

    /**
     *
     * @param foodLevel
     */
    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }

    /**
     *
     * @return
     */
    public int getUnconsciousTime() {
        return unconsciousTime;
    }

    /**
     *
     * @return
     */
    public int getMinFoodLevel() {
        return minFoodLevel;
    }

    /**
     *
     * @return
     */
    public int getMaxFoodLevel() {
        return maxFoodLevel;
    }

    /**
     *
     * @return
     */
    public int getMaxUnconsciousTime() {
        return maxUnconsciousTime;
    }

    /**
     *
     * @return
     */
    public int getTurns() {
        return pregnancyTurns;
    }

    /**
     *
     * @param attackTurns
     */
    public void setAttackTurns(int attackTurns) {
        this.attackTurns = attackTurns;
    }

    /**
     *
     * @return
     */
    public int getAttackTurns() {
        return attackTurns;
    }

    /**
     *
     * @return
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
     *
     * @return
     */
    public ArrayList<DinosaurCapabilities> getCapabilities() {
        return capabilities;
    }
}
