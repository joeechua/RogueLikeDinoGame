package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actions.GrowAction;
import game.actors.BabyBrachiosaur;
import game.actors.BabyDinosaur;
import game.actors.BabyPterodactyl;
import game.actors.BabyStegosaur;

/**
 * Behaviour subclass that allows dinosaur to grow
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 3.0
 * @see Behaviour
 */
public class GrowBehaviour implements Behaviour {

    private final int MATURE_TURN_FOR_STEG_PTERO = 30;
    private final int MATURE_TURN_FOR_BRAC_ALLO = 50;

    /**
     * Returns Action to be performed in order for dinosaur to grow
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return GrowAction instance or null
     * @see Actor
     * @see GameMap
     * @see BabyStegosaur
     * @see BabyDinosaur#getTurnsSinceHatch()
     * @see GrowAction
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        BabyDinosaur babyDino = (BabyDinosaur) actor;
        //stegosaur or pterodactyl babies case
        if((babyDino instanceof BabyStegosaur || babyDino instanceof BabyPterodactyl) &&
                babyDino.getTurnsSinceHatch() >= MATURE_TURN_FOR_STEG_PTERO){
            return new GrowAction();
        }
        //other dinosaurs (allosaur and brachiosaur)
        else if(babyDino.getTurnsSinceHatch() >= MATURE_TURN_FOR_BRAC_ALLO){
            return new GrowAction();
        }
        return null;
    }


}
