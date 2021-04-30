package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.*;

public class GrowAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur adultDino = null;
        if(actor instanceof Allosaur){
            adultDino = new Allosaur();
        }
        else if (actor instanceof Stegosaur){
            adultDino = new Stegosaur();
        }
        else{
            adultDino = new Brachiosaur();
        }
        Location currentLocation = map.locationOf(actor);
        map.removeActor(actor);
        currentLocation.addActor(adultDino);
        return menuDescription(adultDino);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Baby dinosaur has grown into " + actor;
    }
}
