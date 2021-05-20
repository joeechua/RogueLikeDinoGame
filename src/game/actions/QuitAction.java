package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Quit Action for Player.
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 3.0
 * @see edu.monash.fit2099.engine.Action
 */

public class QuitAction extends Action {
    /**
     * integer that represents the action
     */
    private int action = 0;

    /**
     * Constructor
     * @param act integer that represents the action
     */
    public QuitAction(int act){
        this.action = act;
    }

    /**
     * Performing the Quit action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     * @see Actor
     * @see GameMap
     */
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

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "End game";
    }
}
