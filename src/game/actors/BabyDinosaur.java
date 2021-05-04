package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.GrowAction;
import game.enums.Gender;

/**
 * A baby dinosaur class.
 * @see Dinosaur
 */

public abstract class BabyDinosaur extends Dinosaur {
    protected int turnsSinceHatch;
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public BabyDinosaur(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        turnsSinceHatch = 0;
        if(this instanceof BabyAllosaur){
            initFoodLevel = foodLevel = 20;
            rotTime = 40;
        }
        else{
            initFoodLevel = foodLevel = 10;
            rotTime = 20;
        }
    }

    /**
     * Constructor.
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param gender      the gender of the baby dino
     */
    public BabyDinosaur(String name, char displayChar, int hitPoints, Gender gender) {
        super(name, displayChar, hitPoints);
        this.gender = gender;
        turnsSinceHatch = 0;
        if(this instanceof BabyAllosaur){
            initFoodLevel = foodLevel = 20;
            rotTime = 20;
        }
        else if(this instanceof BabyBrachiosaur){
            initFoodLevel = foodLevel = 10;
            rotTime = 40;
        }
        else{
            initFoodLevel = foodLevel = 10;
            rotTime = 20;
        }
    }

    /**
     * Select and return an action to perform on the current turn.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     * @see Actor
     * @see Dinosaur
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Called once per turn, so that maps can experience the passage of time.
     * @see GameMap
     */
    @Override
    public void tick() {
        super.tick();
        turnsSinceHatch++;
    }

    /**
     * Get the turns since the egg hatch
     * @return int represents the turns since the egg hatch
     */
    public int getTurnsSinceHatch() {
        return turnsSinceHatch;
    }
}
