package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.MateAction;
import game.actors.BabyDinosaur;
import game.actors.Dinosaur;
import game.ground.Tree;

import java.util.Random;

/**
 * Behaviour subclass for Dinosaur breeding
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see game.behaviours.Behaviour
 */
public class BreedBehaviour implements Behaviour {

    private final Random random;
    private Actor target;
    private Location here;
    private Class dinoClass;

    /**
     * Constructor
     * @see Random
     */
    public BreedBehaviour(){
        this.target = null;
        this.dinoClass = null;
        this.random = new Random();
    }

    /**
     * Returns action to be executed for actor (dinosaur) to breed
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return Action that actor should perform in order to breed
     * @see Actor
     * @see GameMap#isAnActorAt(Location)
     * @see Location#getExits()
     * @see Exit
     * @see MateAction
     * @see Dinosaur#getClass()
     * @see BabyDinosaur
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;
        dinoClass = dino.getClass();
        here = map.locationOf(dino);

        Action ret = null;
        for(Exit exit: here.getExits()){
            if (map.isAnActorAt(exit.getDestination())) {
                if(map.getActorAt(exit.getDestination()).getDisplayChar() != '@'){
                    Dinosaur target = (Dinosaur) map.getActorAt(exit.getDestination());
                    if (!target.isPregnant() && target.getDisplayChar() == dino.getDisplayChar()
                    && target.getGender() != dino.getGender() && !(target instanceof BabyDinosaur)){
                        if(dino instanceof Pterodactyl && !(here.getGround() instanceof Tree)){
                            ret= moveCloser(dino, map);
                        }
                        ret= new MateAction(target);
                    }
                }
            }
        }
        if(ret == null){
            ret = moveCloser(dino, map);
        }
        return ret;
    }

    /**
     * Returns a MoveActorAction instance with a specific direction dinosaur should move to
     * @param dino dinosaur that is supposed to move
     * @param map map where this movement is occuring
     * @return MoveActorAction or null if no movement is possible
     * @see MoveActorAction
     * @see Dinosaur
     * @see Location
     * @see Exit
     * @see FollowBehaviour
     */
    public Action moveCloser(Dinosaur dino, GameMap map){
        //for each exit
        for(Exit exits: here.getExits()){
            //eget exits from that exit
            for(Exit exit:exits.getDestination().getExits()){
                target = map.getActorAt(exit.getDestination());
                //check for mate
                if(target != null && target instanceof Dinosaur){
                    Dinosaur targetDino = (Dinosaur) target;
                    if(targetDino.getGender() != dino.getGender() &&
                    targetDino.getDisplayChar() == dino.getDisplayChar()){
                        Action fB = new FollowBehaviour(targetDino).getAction(dino, map);
                        return fB;
                    }
                }
            }
        }
        return null;
    }

}
