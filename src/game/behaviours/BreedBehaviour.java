package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.EatingAction;
import game.actions.MateAction;
import game.actors.Dinosaur;
import game.enums.DinosaurCapabilities;
import game.items.*;

import java.util.List;

public class BreedBehaviour implements Behaviour {

    private Actor target;

    public BreedBehaviour(){}

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;

        Location here = map.locationOf(dino);
        Location left = new Location(map, here.x()-1, here.y());
        Location right = new Location(map, here.x()+1, here.y());
        Location up = new Location(map, here.x(), here.y()+1);
        Location down = new Location(map, here.x(), here.y()-1);
        Location leftUp = new Location(map, here.x()-1, here.y()+1);
        Location leftDown = new Location(map, here.x()-1, here.y()-1);
        Location rightUp = new Location(map, here.x()+1, here.y()+1);
        Location rightDown = new Location(map, here.x()+1, here.y()-1);
        Location[] loclist = new Location[]{here, left, right, up, down, leftUp, rightUp, leftDown, rightDown};
//        if (map.contains(target)) {
//            map.contains(actor);
//        }

        if(dino.getFoodLevel()>100){
            for(Location loc:loclist){
                //find and mate
                if(map.isAnActorAt(loc)){
                    Actor target = map.getActorAt(loc);
                    if(target.isConscious() && target.getDisplayChar() == dino.getDisplayChar()){
                        Dinosaur mate = (Dinosaur) target;
                        return new MateAction(mate);
                    }
                }
            }
        }
        return null;
    }
}
