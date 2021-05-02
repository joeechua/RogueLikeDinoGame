package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.HatchAction;
import game.actions.LayEggAction;
import game.actors.Allosaur;
import game.actors.Brachiosaur;
import game.actors.Dinosaur;
import game.actors.Stegosaur;
import game.enums.DinosaurCapabilities;
import game.items.Egg;

import java.util.ArrayList;
import java.util.Random;

import static game.behaviours.BreedBehaviour.locList;

public class PregnantBehaviour implements Behaviour{
    private Random random = new Random();

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;
        Action layEgg= null;

        if(dino instanceof Allosaur && dino.getTurns() >= 10){
            layEgg = new LayEggAction();
        }
        else if(dino instanceof Brachiosaur && dino.getTurns() >= 30){
            //so it is more likely to go extinct bcos higher possbility someone eats it
            layEgg = new LayEggAction();
        }
        else if(dino instanceof Stegosaur && dino.getTurns() >= 10){
            layEgg = new LayEggAction();
        }
        else{
            int[] coords;
            do{
                coords = locList[random.nextInt(locList.length)];
            }while(coords[0] >= map.getXRange().max() || coords[1] >= map.getYRange().max()
                    || coords[0] <= map.getXRange().min() || coords[1]<= map.getYRange().min());
            Location lc = map.at(coords[0], coords[1]);
            layEgg = new MoveActorAction(lc,BreedBehaviour.direction[coords[2]]);
        }
        return layEgg;
    }

}

