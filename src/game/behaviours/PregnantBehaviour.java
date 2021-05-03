package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.LayEggAction;
import game.actors.Allosaur;
import game.actors.Brachiosaur;
import game.actors.Dinosaur;
import game.actors.Stegosaur;
import java.util.Random;


public class PregnantBehaviour implements Behaviour{
    private Random random = new Random();

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;
        Action layEgg= null;
        Location here = map.locationOf(dino);

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
            Exit exit = here.getExits().get(random.nextInt(here.getExits().size()));
            layEgg= new MoveActorAction(exit.getDestination(),exit.getName());
        }
        return layEgg;
    }

}

