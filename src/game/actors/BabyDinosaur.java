package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.GrowAction;
//After 30 turns for the stegosaur and 50 turns for the brachiosaur, the baby dinosaur should grow into an adult.
public abstract class BabyDinosaur extends Dinosaur {
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
        if(this instanceof BabyAllosaur){
            initFoodLevel = foodLevel = 20;
        }
        else{
            initFoodLevel = foodLevel = 10;
        }
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        tick();
        if(dinoChar == 'b' && getTurns() >= MATURE_TURN_FOR_BRAC){
            return new GrowAction();
        }
        else if((dinoChar == 's' || dinoChar == 'a') && getTurns() >= MATURE_TURN_FOR_STEGO_ALLO){
            return new GrowAction();
        }
        else{
            return super.playTurn(actions, lastAction, map, display);
        }
    }

    @Override
    public void tick() {
        super.tick();
    }
}
