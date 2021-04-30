package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.*;
import game.items.AllosaurEgg;
import game.items.Egg;
import game.items.StegosaurEgg;

public class HatchAction extends Action {
    private Egg egg;

    public HatchAction(Egg egg) {
        this.egg = egg;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location location = egg.getBirthLocation();
        for (Item item: location.getItems()){
            if(item.equals(egg) && egg.getTimeHatch() == 0){
                BabyDinosaur babyDino;
                int ecoPoint;
                if(egg instanceof StegosaurEgg){
                    babyDino = new BabyStegosaur();
                    ecoPoint = 100;
                }
                else if(egg instanceof AllosaurEgg){
                    babyDino = new BabyAllosaur();
                    ecoPoint = 1000;
                }
                else {
                    babyDino = new BabyBrachiosaur();
                    ecoPoint = 1000;
                }
                Player.wallet.addEcoPoints(ecoPoint);
                location.addActor(babyDino);
                location.removeItem(item);
                return menuDescription(babyDino);
            }
        }
        return egg + " is not ready to be hatched.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " hatches from egg.";
    }
}
