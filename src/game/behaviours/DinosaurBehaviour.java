package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.actions.DieAction;
import game.actors.BabyDinosaur;
import game.actors.BabyPterodactyl;
import game.actors.Dinosaur;
import game.actors.Pterodactyl;
import game.enums.DinosaurCapabilities;

/**
 * A behaviour subclass that contains all possible behaviours a Dinosaur can have
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see Behaviour
 */
public class DinosaurBehaviour implements Behaviour{

    private Action a;


    /**
     * Selects behaviour and gets the appropriate action to be performed in order
     * to fulfill certain needs
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return Action to be performed by actor
     * @see Dinosaur
     * @see Actor
     * @see GameMap#locationOf(Actor)
     * @see DieAction
     * @see DoNothingAction
     * @see DinosaurCapabilities#PREGNANT
     * @see PregnantBehaviour#getAction(Actor, GameMap)
     * @see GrowBehaviour#getAction(Actor, GameMap)
     * @see BreedBehaviour#getAction(Actor, GameMap)
     * @see HungerBehaviour#getAction(Actor, GameMap)
     * @see WanderBehaviour#getAction(Actor, GameMap)
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;
        int locX = map.locationOf(actor).x();
        int locY = map.locationOf(actor).y();
        String loc = "(" + locX + ", " + locY + ")";
        //dinosaur is unconscious and might die
        if(dino.isUnconscious()){
            if(dino.isDead()){
                a = new DieAction();
            }
            else{
                a = new DoNothingAction();
            }
        }
        //dinosaur is landed and needs to fly
        else if((dino instanceof Pterodactyl || dino instanceof BabyPterodactyl) && dino.getSquares() == 0){
            System.out.println("hello land??");
            LandingBehaviour lB = new LandingBehaviour();
            a = lB.getAction(actor, map);
            System.out.println(a);

        }
        //dinosaur is pregnant
        else if(dino.hasCapability(DinosaurCapabilities.PREGNANT) && dino.isPregnant()){
            PregnantBehaviour pB = new PregnantBehaviour();
            a = pB.getAction(dino, map);
        }
        //dinosaur is a babydinosaur and is ready to grow
        else if(dino instanceof BabyDinosaur){
            GrowBehaviour gB = new GrowBehaviour();
            a = gB.getAction(dino,map);
        }
        //dinosaur is a male / dinosaur is not pregnant
        else if((((dino.getDisplayChar() == 'S' || dino.getDisplayChar() == 'A') && dino.getFoodLevel() >= 50) ||
                (dino.getDisplayChar() == 'B' && dino.getFoodLevel() >= 70))
        && !dino.isPregnant()){
            BreedBehaviour bB = new BreedBehaviour();
            a = bB.getAction(dino, map);
        }
        if(a == null && dino.isThirsty()){
            System.out.println(dino.getName() + " at " + loc + " is getting thirsty!");
            ThirstyBehaviour tB = new ThirstyBehaviour();
            a = tB.getAction(dino, map);
        }
        //dinosaur is hungry or has noting to do
        if(a == null && dino.isHungry()){
            System.out.println(dino.getName() + " at " + loc + " is getting hungry!");
            HungerBehaviour hB = new HungerBehaviour();
            a = hB.getAction(dino, map);
        }
        //if dinosaur is full, it will wander
        if(a == null){
            WanderBehaviour wB = new WanderBehaviour();
            a = wB.getAction(dino, map);
        }
        return a;

    }
}
