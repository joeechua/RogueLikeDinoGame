package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actions.GrowAction;
import game.actors.BabyBrachiosaur;
import game.actors.BabyDinosaur;

/**
 * Behaviour subclass that allows dinosaur to grow
 */
public class GrowBehaviour implements Behaviour {

    private final int MATURE_TURN_FOR_STEGO_ALLO = 30;
    private final int MATURE_TURN_FOR_BRAC = 50;

    /**
     * Returns Action to be performed in order for dinosaur to grow
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return GrowAction instance or null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        BabyDinosaur babyDino = (BabyDinosaur) actor;
        if(babyDino instanceof BabyBrachiosaur && babyDino.getTurnsSinceHatch() >= MATURE_TURN_FOR_BRAC){
            return new GrowAction();
        }
        else if(babyDino.getTurnsSinceHatch() >= MATURE_TURN_FOR_STEGO_ALLO){
            return new GrowAction();
        }
        return null;
    }


}
