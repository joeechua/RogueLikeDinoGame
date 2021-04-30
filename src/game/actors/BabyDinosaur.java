package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.GrowAction;

public abstract class BabyDinosaur extends Dinosaur {
    private int turns;
    protected char dinoChar;
    private final int MATURE_TURN_FOR_STEGO_ALLO = 30;
    private final int MATURE_TURN_FOR_BRAC = 50;
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public BabyDinosaur(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        turns = 0;
        initFoodLevel = foodLevel = 10;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        tick();
        if(dinoChar == 'b' && turns >= MATURE_TURN_FOR_BRAC){
            return new GrowAction();
        }
        else if((dinoChar == 's' || dinoChar == 'a') && turns >= MATURE_TURN_FOR_STEGO_ALLO){
            return new GrowAction();
        }
        else{
            return super.playTurn(actions, lastAction, map, display);
        }
    }

    public int getTurns() {
        return turns;
    }

    public char getDinoChar() {
        return dinoChar;
    }

    public int getMATURE_TURN_FOR_STEGO_ALLO() {
        return MATURE_TURN_FOR_STEGO_ALLO;
    }

    public int getMATURE_TURN_FOR_BRAC() {
        return MATURE_TURN_FOR_BRAC;
    }

    @Override
    public void tick() {
        super.tick();
        turns++;
    }
}
