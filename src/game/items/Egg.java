package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import game.actors.*;
import game.enums.Gender;

import java.util.Random;

/**
 * Abstract class for Egg object
 */
public abstract class Egg extends PortableItem {
    private int timeHatch;
    private Location birthLocation;
    private final Gender gender;
    protected final Random random = new Random();

    /**
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @see Random
     * @see Gender
     * @see PortableItem
     */
    public Egg(String name, char displayChar) {
        super(name, displayChar);
        this.gender = randGen();
        if(this instanceof AllosaurEgg){
            this.timeHatch = 50;
        }
        else{
            this.timeHatch = 10 + random.nextInt(10);
        }
    }

    /**
     * Tick function to let egg keep track of time, and when egg is due to hatch
     * @param location location of egg
     * @see Location
     */
    public void tick(Location location) {
        super.tick(location);
        timeHatch--;
    }

    /**
     * tick function to let egg keep track of time when it is in the player's inventory
     * @param location location where actor is
     * @param actor Actor that is holding an egg item
     * @see Location
     * @see Actor
     * @see PortableItem
     */
    public void tick(Location location, Actor actor) {
        super.tick(location, actor);

        if(timeHatch == 0){
            System.out.println("Egg is ready to hatch, you can drop it.");
        }
        else{
            timeHatch--;
        }
    }

    /**
     * Randomly generates a gender for the egg
     * @return Gender enum type (F or M)
     * @see Gender
     * @see Random
     */
    public Gender randGen(){
        int rand = random.nextInt(100);
        if(rand%2 == 0){
            return Gender.F;
        }
        else{
            return Gender.M;
        }
    }

    /**
     * Gives outisde classes access to location where egg was laid
     * @param birthLocation location of where egg was laid
     * @see Location
     */
    public void setBirthLocation(Location birthLocation) {
        this.birthLocation = birthLocation;
    }

    /**
     * Lets outside classes have access to the time before egg hatches
     * @return rounds until the egg hatches
     */
    public int getTimeHatch() {
        return timeHatch;
    }

    /**
     * Lets outside classes get gender of the egg
     * @return Gender
     */
    public Gender getGender(){return gender;}
}
