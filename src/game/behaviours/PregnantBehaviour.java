package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.LayEggAction;
import game.actors.Allosaur;
import game.actors.Brachiosaur;
import game.actors.Dinosaur;
import game.actors.Stegosaur;
import java.util.Random;

/**
 * Behaviour subclass that allows dinosaur to be pregnant and give birth
 */
public class PregnantBehaviour implements Behaviour{
    private Random random = new Random();

    /**
     * Returns action pertaining to what the actor should do(layEgg or MoveActor)
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;
        Action layEgg= null;
        Location here = map.locationOf(dino);

        if(dino instanceof Allosaur && dino.getPregnancyTurns() >= 10){
            layEgg = new LayEggAction();
            dino.removeBehaviour(this);
        }
        else if(dino instanceof Brachiosaur && dino.getPregnancyTurns() >= 30){
            //so it is more likely to go extinct bcos higher possbility someone eats it
            layEgg = new LayEggAction();
            dino.removeBehaviour(this);
        }
        else if(dino instanceof Stegosaur && dino.getPregnancyTurns() >= 10){
            layEgg = new LayEggAction();
            dino.removeBehaviour(this);
        }
        else{
            Exit exit = here.getExits().get(random.nextInt(here.getExits().size()));
            layEgg= new MoveActorAction(exit.getDestination(),exit.getName());
        }
        return layEgg;
    }

}

