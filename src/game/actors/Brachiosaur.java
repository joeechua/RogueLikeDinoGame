package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.behaviours.Behaviour;
import game.enums.DinosaurCapabilities;
import game.enums.Species;

import java.util.ArrayList;


public class Brachiosaur extends Dinosaur {

    public Brachiosaur() {
        super(Species.B.name(), 'd', 100);
        capabilities.add(DinosaurCapabilities.HERBIVORE);
        capabilities.add(DinosaurCapabilities.LONG_NECK);
    }


    /**
     * Figure out what to do next.
     *
     * FIXME: Stegosaur wanders around at random, or if no suitable MoveActions are available, it
     * just stands there.  That's boring.
     *
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
//    @Override
//    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
//        //Action wander = behaviour.getAction(this, map);
//        //
//        //eat
//        //breed
//        //attack
//
//        return new DoNothingAction();
//    }
}
