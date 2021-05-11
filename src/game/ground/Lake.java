package game.ground;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actors.BabyPterodactyl;
import game.actors.Pterodactyl;
import game.items.Fish;

import java.util.ArrayList;
import java.util.Random;

public class Lake extends Ground {
    private ArrayList<Fish> fishes;
    private int waterSips;
    private int turns;
    private final int MAX_NO_OF_FISH = 25;
    private Random random = new Random();

    public Lake(){
        super('~');
        turns = 0;
        waterSips = 25;
        fishes = new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            fishes.add(new Fish("Fish", '鱼', true));
        }
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        turns ++;

        if (turns % 10 == 0 && random.nextDouble() <= 0.2){
            System.out.println("It's raining.");
            float rainfall = (float) (((random.nextInt(6)) + 1) * 0.1);
            incWaterSips((int) (rainfall * 20));
        }

        if(fishes.size() < MAX_NO_OF_FISH && random.nextDouble() <= 0.6){
            fishes.add(new Fish("Fish", '鱼', true));
        }
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor instanceof Pterodactyl || actor instanceof BabyPterodactyl;
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
        waterSips = Math.min(getWaterSips()+incValue, 25);
    }

    /**
     * Decrease the water sips of lake
     * @param decValue the decrement value
     */
    public void decWaterSips(int decValue){
        waterSips = Math.max(getWaterSips()-decValue, 0);
    }

}