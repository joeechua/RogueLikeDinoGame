package game.behaviours;

import edu.monash.fit2099.engine.*;

public class BreedBehaviour implements Behaviour {

    private Actor target;
    public BreedBehaviour(){
    }
    public BreedBehaviour(Actor subject){this.target = subject;}

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (map.contains(target)) {
            map.contains(actor);
        }

        /*
        if(actor is not hungry, there is someone in the area){
            return MateAction();
        }
        else if(actor is not hungry, no one in the area){
            return MoveActorAction(destionation, getExitName());
        }
        else if(actor is hungry){
            return "Actor is too hungry to mate" + maybe something in the dino part to inhibit this behaviour
            if they are hungry
        }
        else{
            return null;
        }
         */

        //smt about breeding (check sex, distance etc)
        //call MateAction()

        return null;
    }
}
