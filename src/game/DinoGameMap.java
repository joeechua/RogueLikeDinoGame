package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import game.actors.Dinosaur;
import game.ground.Lake;

import java.util.List;
import java.util.Random;

public class DinoGameMap extends GameMap {

    private Random random = new Random();

    public DinoGameMap(GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
    }

//    @Override
//    public void tick() {
//        super.tick();
//        //this.rain();
//    }

    public void rain(){
        //int turns = 0;
        for (int y : heights) {
            for (int x : widths) {
                if(this.at(x, y).getGround() instanceof Lake){
                    Lake lake = (Lake) this.at(x, y).getGround();
                    //turns = lake.getTurns();
                }
            }
        }

        //if(turns % 10 == 0 && random.nextDouble() <= 0.2){
        //System.out.println("It's raining !!");
        float rainfall = (float) (((random.nextInt(6)) + 1) * 0.1);
        for (int y : heights) {
            for (int x : widths) {
                if(this.at(x, y).getGround() instanceof Lake){
                    Lake lake = (Lake) this.at(x, y).getGround();
                    lake.incWaterSips((int) rainfall * 20);
                }
            }
        }
        for(Actor actor: this.actorLocations){
            if (actor instanceof Dinosaur){
                Dinosaur dino = (Dinosaur) actor;
                if(dino.isThirsty() && dino.isUnconscious()){
                    System.out.println(dino + " wakes up after rain!");
                    dino.incWaterLevel(10);
                }
            }
        }
        //}
    }
}
