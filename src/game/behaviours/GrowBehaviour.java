package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import game.actions.GrowAction;
import game.actors.BabyDinosaur;
import game.actors.Dinosaur;

public class GrowBehaviour implements Behaviour {

    private int turns;

    public GrowBehaviour(int turns){this.turns = turns;}
    @Override
    public Action getAction(Actor actor, GameMap map) {
        BabyDinosaur babyDino = (BabyDinosaur) actor;
        if(babyDino.getDisplayChar() == 'a' && this.turns == 40){
            return new GrowAction();
        }
        else if(babyDino.getDisplayChar() == 's' && this.turns == 10){
            return new GrowAction();
        }
        else if(babyDino.getDisplayChar() == 'b' && this.turns == 25){
            return new GrowAction();
        }
        return null;
    }


}
