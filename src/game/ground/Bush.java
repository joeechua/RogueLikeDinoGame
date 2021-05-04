package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.HarvestAction;
import game.actors.Player;
import game.enums.Points;
import game.items.Fruit;

import java.util.Random;

/**
 * A class that represents Bush.
 * @see Ground
 */

public class Bush extends Ground{
    private int age = 0;
    private Random random = new Random();
    private Fruit bushFruit;

    /**
     * Constructor.
     */
    public Bush() {
        super('⺾');
    }

    /**
     * Called once per turn, so that maps can experience the passage of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if(bushFruit == null && random.nextDouble() <= 0.1) {
            bushFruit = new Fruit();
            Player.wallet.addEcoPoints(Points.RIPE_FRUIT_PRODUCED.getPoints());
        }
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A collection of Actions.
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if(actor instanceof Player && this.bushFruit != null){
            return new Actions(new HarvestAction(bushFruit, this));
        }
        return new Actions(null);
    }

    /**
     * Set the fruit from bush
     * @param bushFruit fruit from bush
     */
    public void setBushFruit(Fruit bushFruit) {
        this.bushFruit = bushFruit;
    }

    /**
     * Determine whether bush contains fruits or not
     * @return a boolean, if true bush got fruit, else no fruit
     */
    public boolean gotFruit(){
        return bushFruit != null;
    }

    /**
     * Get the fruit from bush
     * @return a fruit item from bush
     */
    public Fruit getBushFruit(){
        return bushFruit;
    }
}
