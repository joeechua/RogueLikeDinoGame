package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;


public class Brachiosaur extends Actor {
    private ArrayList<Behaviour> behaviour;
    public Brachiosaur(String name) {
        super(name, 'd', 100);

        //behaviour = new WanderBehaviour();
        //behaviour.add(new WanderBehaviour());
        //behaviour.add(new EatBehaviour());
        //behaviour.add(new BreedBehaviour());
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        //i think need to return a list of actions
        return new Actions(new AttackAction(this));
    }

    /**
     * Figure out what to do next.
     *
     * FIXME: Stegosaur wanders around at random, or if no suitable MoveActions are available, it
     * just stands there.  That's boring.
     *
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        //Action wander = behaviour.getAction(this, map);
        //
        //eat
        //breed
        //attack

        return new DoNothingAction();
    }
}
