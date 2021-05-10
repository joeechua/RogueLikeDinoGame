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
    private Random random = new Random();

    public Lake(){
        super('~');
            turns = 0;
            waterSips = 25;
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        turns ++;

        if (turns % 10 == 0 && random.nextDouble() <= 0.2){
            System.out.println("It's raining.");
            double rainfall = (random.nextInt(6) + 1) * 0.1;
            waterSips = (int) (rainfall * 20);
        }
    }
}
