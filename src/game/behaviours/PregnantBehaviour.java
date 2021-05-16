package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.LayEggAction;
import game.actors.*;

import java.util.Random;

/**
 * Behaviour subclass that allows dinosaur to be pregnant and give birth
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see Behaviour
 */
public class PregnantBehaviour implements Behaviour{
    private Random random = new Random();

    /**
     * Returns action pertaining to what the actor should do(layEgg or MoveActor)
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an Action that actor can perform.
     * @see Actor
     * @see Dinosaur
     * @see GameMap#locationOf(Actor)
     * @see LayEggAction
     * @see Location#getExits()
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;
        Action layEgg= null;
        Location here = map.locationOf(dino);

        if(dino instanceof Allosaur && dino.getPregnancyTurns() >= 20){
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
        else if(dino instanceof Pterodactyl && dino.getPregnancyTurns() >= 10
        && dino.getOnTree()){
            layEgg = new LayEggAction();
            dino.removeBehaviour(this);
        }
        else if(dino instanceof Pterodactyl && dino.getPregnancyTurns() >= 10
                && !dino.getOnTree()){
            //land the dino if its due but not on a tree
            Behaviour lB = new LandingBehaviour();
            layEgg = lB.getAction(actor, map);
        }
        else{
            Exit exit = here.getExits().get(random.nextInt(here.getExits().size()));
            layEgg= new MoveActorAction(exit.getDestination(),exit.getName());
        }
        return layEgg;
    }

}

