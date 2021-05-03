package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.HarvestAction;
import game.actors.Player;
import game.items.Fruit;

import java.util.Random;

public class Bush extends Ground{
    private int age = 0;
    private Random random = new Random();
    private Fruit bushFruit;

    public Bush() {
        super('⺾');
    }

    @Override
    public void tick(Location location) {
        super.tick(location);

        age++;
        if (age == 10)
            displayChar = 'm';
        if (age == 20)
            displayChar = 'M';

        if(bushFruit == null && random.nextDouble() <= 0.1) {
            bushFruit = new Fruit();
        }
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if(actor instanceof Player && this.bushFruit != null){
            return new Actions(new HarvestAction(bushFruit, this));
        }
        return new Actions(null);
    }

    public void setBushFruit(Fruit bushFruit) {
        this.bushFruit = bushFruit;
    }

    public boolean gotFruit(){
        return bushFruit != null;
    }

    public Fruit getBushFruit(){
        return bushFruit;
    }
}
