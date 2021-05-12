package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actors.Dinosaur;

public class LandingAction extends Action {

    //// INCOMPLETE
    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;
        System.out.println(dino.getName() + " lands to walk.");
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " lands on a tree.";
    }
}
