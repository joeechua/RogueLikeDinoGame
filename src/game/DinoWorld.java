package game;

import edu.monash.fit2099.engine.*;
import game.actors.Dinosaur;
import game.ground.Lake;

import java.util.Random;

public class DinoWorld extends World{

    private Random random = new Random();
    /**
     * Constructor.
     *
     * @param display the Display that will display this World.
     */
    public DinoWorld(Display display) {
        super(display);
    }

//    public ActorLocations getActorLocations(){
//        ActorLocations ret = this.actorLocations;
//        return ret;
//    }

    public void rain(GameMap map, Location loca){
        if(gameMaps.contains(map)){
            Location loc = map.at(loca.x(), loca.y());
            if(loc.getGround() instanceof Lake){
                Lake lake = (Lake) loc.getGround();
                if (lake.getTurns() % 10 == 0 && random.nextDouble() <= 0.2) {
                    float rainfall = (float) (((random.nextInt(6)) + 1) * 0.1);
                    lake.incWaterSips(((int) (rainfall * 20)));
                    for(Actor actor: actorLocations){
                        if(actor instanceof Dinosaur){
                            Dinosaur dino = (Dinosaur) actor;
                            if(dino.isThirsty() && dino.isUnconscious() && !dino.isHungry()){
                                dino.setWaterLevel(10);
                                //make it conscious
                            }
                        }
                    }
                }
            }
        }
    }
}
