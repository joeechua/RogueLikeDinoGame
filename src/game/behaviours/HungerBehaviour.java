package game.behaviours;

import edu.monash.fit2099.engine.*;

public class HungerBehaviour implements Behaviour {

    private Actor target;

    public HungerBehaviour(Actor subject){this.target = subject;}

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (map.contains(target)) {
            map.contains(actor);
        }

        /*
        if(actor.getFoodLevel < x && no food around them){
            return MoveActorAction(destination, getExitName())
        }
        else{
            return EatingAction()
        }

         */

        return null;
    }

}
