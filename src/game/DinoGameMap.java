package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import game.actors.Dinosaur;
import game.ground.Lake;

import java.util.List;
import java.util.Random;

/**
 * A class that represents Dino Game Map.
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see edu.monash.fit2099.engine.GameMap
 */

public class DinoGameMap extends GameMap {

    private Random random = new Random();

    /**
     * Constructor that creates a dino map from a sequence of ASCII strings.
     *
     * @param groundFactory Factory to create Ground objects
     * @param lines         List of Strings representing rows of the dino map
     */
    public DinoGameMap(GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
    }

    /**
     * Rain method where increase the waterSips of all lakes contain in the game map and
     * increase the water level for all unconscious dino.
     * @see edu.monash.fit2099.engine.GameMap
     * @see edu.monash.fit2099.engine.Location
     * @see Dinosaur
     * @see Lake
     */
    public void rain(){
        // increase water sips of lake
        float rainfall = (float) (((random.nextInt(6)) + 1) * 0.1);
        for (int y : heights) {
            for (int x : widths) {
                if(this.at(x, y).getGround() instanceof Lake){
                    Lake lake = (Lake) this.at(x, y).getGround();
                    lake.incWaterSips((int) (rainfall * 20));
                }
            }
        }
        // increase water level of dino
        for(Actor actor: this.actorLocations){
            if (actor instanceof Dinosaur){
                Dinosaur dino = (Dinosaur) actor;
                if(dino.isThirsty() && dino.isUnconscious()){
                    System.out.println(dino + " wakes up after rain!");
                    dino.incWaterLevel(10);
                }
            }
        }
    }
}
