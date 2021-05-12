package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class QuitAction extends Action {
    private int action = 0;

    public QuitAction(int act){
        this.action = act;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        String ret = "You have ended the game";
        if(action == 0){
            ret = "You have lost the game!";
        }
        else if(action == 1){
            ret = "You have won the game!";
        }
        return ret + "\n" + menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "End game";
    }
}
