package game.behaviours;

import edu.monash.fit2099.engine.*;

public class BreedBehaviour implements Behaviour {

    private Actor target;
    public BreedBehaviour(Actor subject){this.target = subject;}

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (map.contains(target)) {
            map.contains(actor);
        }

        //smt about breeding (check sex, distance etc)
        //call MateAction()

        return null;
    }
}
