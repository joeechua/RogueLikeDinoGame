package game.actors;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actions.AttackAction;
import game.enums.DinosaurCapabilities;
import game.enums.Food;
import game.enums.Gender;
import game.enums.Species;

public class BabyStegosaur extends BabyDinosaur{
    /**
     * Constructor.
     * All Stegosaurs are represented by a 's' and have 100 hit points.
     * has dinosaur capabilities herbivore
     */
    public BabyStegosaur(Gender g) {
        super("Baby " + Species.S.name(),'s',100, g);
        capabilities.add(DinosaurCapabilities.HERBIVORE);
        setEdibleFoodList(Food.getFoodList(this));
        attackTurns = 0;
    }

    /**
     * Called once per turn, so that maps can experience the passage of time.
     * @see Dinosaur
     * @see GameMap
     */
    @Override
    public void tick() {
        super.tick();
        if(attackTurns != 0){
            attackTurns--;
        }
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     * @param otherActor the Actor that might be performing attack
     * @param direction String representing the direction of the other Actor
     * @param map current GameMap
     * @return A collection of Actions.
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        return new Actions(new AttackAction(this));
    }
}
