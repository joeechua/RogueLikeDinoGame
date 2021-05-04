package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.GrowAction;
public abstract class BabyDinosaur extends Dinosaur {
    protected char dinoChar;
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

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public void tick() {
        super.tick();
        turnsSinceHatch++;
    }

    public int getTurnsSinceHatch() {
        return turnsSinceHatch;
    }
}
