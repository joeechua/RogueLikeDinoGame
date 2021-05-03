package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.EatingAction;
import game.actions.MateAction;
import game.actors.Dinosaur;
import game.enums.DinosaurCapabilities;
import game.ground.Bush;
import game.ground.Tree;
import game.items.*;

import java.util.List;
import java.util.Random;

public class BreedBehaviour implements Behaviour {

    private Random random = new Random();
    private Actor target;
    private Location here;
    private Class dinoClass;

    public BreedBehaviour(){}

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
                    && target.getGender() != dino.getGender()){
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

    public Action moveCloser(Dinosaur dino, GameMap map){
        Exit temp = null;
        Location mate;
        for(Exit exits: here.getExits()){
            for(Exit exit:exits.getDestination().getExits()){
                target = map.getActorAt(exit.getDestination());
                if(target != null && target instanceof Dinosaur){
                    Dinosaur targetDino = (Dinosaur) target;
                    if(targetDino.getGender() != dino.getGender() &&
                    targetDino.getDisplayChar() == dino.getDisplayChar()){
                        return new MoveActorAction(exits.getDestination(),exits.getName());
                    }
                }
            }
        }
        do {
            temp = here.getExits().get(random.nextInt(here.getExits().size()));
            mate = temp.getDestination();
        }
        while (mate == dino.getPrevLoc());
        return new MoveActorAction(mate, temp.getName());
    }

}
