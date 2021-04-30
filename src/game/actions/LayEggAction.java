package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.actors.Allosaur;
import game.actors.Stegosaur;
import game.items.AllosaurEgg;
import game.items.BrachiosaurEgg;
import game.items.StegosaurEgg;

public class LayEggAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        Location birthLocation = map.locationOf(actor);
        if(actor instanceof Allosaur){
            AllosaurEgg egg = new AllosaurEgg();
            egg.setBirthLocation(birthLocation);
            birthLocation.addItem(egg);
        }
        else if(actor instanceof Stegosaur){
            StegosaurEgg egg = new StegosaurEgg();
            egg.setBirthLocation(birthLocation);
            birthLocation.addItem(egg);
        }
        else {
            BrachiosaurEgg egg = new BrachiosaurEgg();
            egg.setBirthLocation(birthLocation);
            birthLocation.addItem(egg);
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " lays an egg.";
    }
}
