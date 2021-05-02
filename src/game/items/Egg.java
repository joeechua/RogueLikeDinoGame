package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.actions.HatchAction;
import game.actors.*;
import game.behaviours.BreedBehaviour;
import game.enums.Gender;
import game.enums.Species;

import java.util.Random;

public abstract class Egg extends PortableItem {
    private int timeHatch = 5;
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
    }

    public void tick(Location location) {
        System.out.println("egg time " + timeHatch);
        super.tick(location);

        if(timeHatch == 0){
            BabyDinosaur babyDino;
            int ecoPoint;
            if(this instanceof StegosaurEgg){
                babyDino = new BabyStegosaur();
                ecoPoint = 100;
            }
            else if(this instanceof AllosaurEgg){
                babyDino = new BabyAllosaur();
                ecoPoint = 1000;
            }
            else {
                babyDino = new BabyBrachiosaur();
                ecoPoint = 1000;
            }
            Player.wallet.addEcoPoints(ecoPoint);
            location.removeItem(this);
            location.addActor(babyDino);
        }
        else{
            timeHatch--;
        }

    }

    public void tick(Location location, Actor actor) {
        super.tick(location, actor);

        if(timeHatch == 0){
            System.out.println("Egg is ready to hatch, you can drop it.");
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
