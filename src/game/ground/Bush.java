package game.ground;

import edu.monash.fit2099.engine.*;
import game.actions.HarvestAction;
import game.actors.Player;
import game.enums.Points;
import game.items.Fruit;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class that represents Bush.
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see edu.monash.fit2099.engine.Ground
 */

public class Bush extends Ground{
    private int age = 0;
    private Random random = new Random();
    private ArrayList<Fruit> bushFruit;

    /**
     * Constructor.
     */
    public Bush() {
        super('⺾');
        bushFruit = new ArrayList<>();
    }

    /**
     * Called once per turn, so that maps can experience the passage of time.
     * @param location The location of the Ground
     * @see Location
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        age++;
        if (age == 10)
            displayChar = 'm';
        if (age == 20)
            displayChar = 'M';

        if(bushFruit == null && random.nextDouble() <= 0.1) {
            bushFruit.add(new Fruit());
            Player.wallet.addEcoPoints(Points.RIPE_FRUIT_PRODUCED.getPoints());
        }
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A collection of Actions.
     * @see Actor
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if(actor instanceof Player && this.bushFruit.size() > 0){
            return new Actions(new HarvestAction(bushFruit.get(0), this));
        }
        return new Actions(null);
    }

    /**
     * Set the fruit from bush
     * @param bushFruit fruit from bush
     */
    public void setBushFruit(ArrayList<Fruit> bushFruit) {
        this.bushFruit = bushFruit;
    }

    /**
     * Determine whether bush contains fruits or not
     * @return a boolean, if true bush got fruit, else no fruit
     */
    public boolean gotFruit(){
        return bushFruit.size() != 0;
    }

    /**
     * Get the fruit from bush
     * @return a fruit item from bush
     */
    public ArrayList<Fruit> getBushFruit(){
        return bushFruit;
    }
}
