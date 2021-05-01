package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.actions.HatchAction;
import game.actors.Dinosaur;
import game.enums.Gender;
import game.enums.Species;

import java.util.Random;

public abstract class Egg extends PortableItem {
    private int timeHatch;
    private Location birthLocation;
    private Gender gender;
    private Random random = new Random();

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public Egg(String name, char displayChar) {

        super(name, displayChar);
        this.gender = randGen();
        capabilities.addCapability(ItemCapabilities.EATEN);
    }

    public void tick(Location location) {
        super.tick(location);

        if(timeHatch == 0){
            //Hatch Action
        }
        else{
            timeHatch--;
        }

    }

    public void tick(Location location, Actor actor) {
        super.tick(location, actor);

        if(timeHatch == 0){
            //Drop Action
            //It could also do nothing we should discuss
            //or i could raise an exception where it can ask user whether want to drop or not
        }
        else{
            timeHatch--;
        }
    }

    public Gender randGen(){
        int rand = random.nextInt(100);
        if(rand%2 == 0){
            return Gender.F;
        }
        else{
            return Gender.M;
        }
    }


    public int getTimeHatch(){
        return this.timeHatch;
    }

    public void setBirthLocation(Location birthLocation) {
        this.birthLocation = birthLocation;
    }
    public Location getBirthLocation() {
        return birthLocation;
    }

}
