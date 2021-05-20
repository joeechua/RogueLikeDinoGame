package game.ground;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enums.DinosaurCapabilities;
import game.items.Fish;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class that represents Lake.
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see edu.monash.fit2099.engine.Ground
 */

public class Lake extends Ground {
    private ArrayList<Fish> fishes;
    private int waterSips;
    private final int MAX_NO_OF_FISH = 25;
    private Random random = new Random();

    /**
     * Constructor.
     */
    public Lake(){
        super('~');
        waterSips = 25;
        fishes = new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            fishes.add(new Fish());
        }
    }

    /**
     * Called once per turn, so that lake can experience the passage of time.
     * @param location The location of the Lake Ground
     * @see Location
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        waterSips--;

        // add new fish
        if(fishes.size() < MAX_NO_OF_FISH && random.nextDouble() <= 0.6){
            fishes.add(new Fish());
        }

        // lake drys out
        if(!gotWater()){
            location.setGround(new Dirt());
        }
    }

    /**
     * Returns true if an Actor can enter lake
     *
     * @param actor the Actor who might be moving to the lake
     * @return true if the Actor can enter lake
     * @see Actor
     * @see DinosaurCapabilities
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if(actor.hasCapability(DinosaurCapabilities.FLY)){
                return true;
        }
        return false;
    }

    /**
     * Get the fish array list from lake
     * @return a fish array list from lake
     */
    public ArrayList<Fish> getFish() {
        return fishes;
    }

    /**
     * Determine whether lake contains fish or not
     * @return a boolean, if true bush got fish, else no fish
     */
    public boolean gotFish(){
        return fishes.size() > 0;
    }

    /**
     * Determine whether lake contains water sips or not
     * @return a boolean, if true lake got water sips, else dry
     */
    public boolean gotWater(){
        return waterSips > 0;
    }

    public int getWaterSips() {
        return waterSips;
    }

    /**
     * Increase the water sips of lake
     * @param incValue the increment value
     */
    public void incWaterSips(int incValue){
        this.waterSips = getWaterSips() + incValue;
    }

    /**
     * Decrease the water sips of lake
     * @param decValue the decrement value
     */
    public void decWaterSips(int decValue){
        this.waterSips = Math.max(getWaterSips()-decValue, 0);
    }

}
