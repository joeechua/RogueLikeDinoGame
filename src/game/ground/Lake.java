package game.ground;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.items.Fish;

import java.util.ArrayList;
import java.util.Random;

public class Lake extends Ground {
    private ArrayList<Fish> fishes;
    private int waterSips;
    private int turns;
    private int noOfFish;
    private int maxNoOfFish = 25;
    private Random random = new Random();

    public Lake(){
        super('~');
        turns = 0;
        waterSips = 25;
        for(int i = 1; i <= 5; i++){
            fishes.add(new Fish("Fish", '鱼', true));
        }
        noOfFish = 5;
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        turns ++;

        if (turns % 10 == 0 && random.nextDouble() <= 0.2){
            System.out.println("It's raining.");
            float rainfall = (float) (((random.nextInt(6)) + 1) * 0.1);
            waterSips += (int) (rainfall * 20);
        }

        if(fishes.size() < maxNoOfFish && random.nextDouble() <= 0.6){
            fishes.add(new Fish("Fish", '鱼', true));
            noOfFish++;
        }
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
     * @return a boolean, if true bush got water sips, else dry
     */
    public boolean gotWater(){
        return waterSips > 0;
    }
}
