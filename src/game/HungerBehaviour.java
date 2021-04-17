package game;

import edu.monash.fit2099.engine.*;

public class HungerBehaviour implements Behaviour {

    private Actor target;

    public HungerBehaviour(Actor subject){this.target = subject;}

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (map.contains(target)) {
            map.contains(actor);
        }

        //smt about eating
        //call ConsumeAction()

        return null;
    }

}
