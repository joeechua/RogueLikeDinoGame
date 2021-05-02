package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actions.DieAction;
import game.actors.BabyDinosaur;
import game.actors.Dinosaur;
import game.enums.DinosaurCapabilities;

import java.util.ArrayList;

public class DinosaurBehaviour implements Behaviour{

    private Action a;
    public DinosaurBehaviour() {
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;
        int locX = map.locationOf(actor).x();
        int locY = map.locationOf(actor).y();
        String loc = "(" + locX + ", " + locY + ")";

        if(dino.hasCapability(DinosaurCapabilities.PREGNANT) && dino.isPregnant()){
            System.out.println(dino.getName() + " at " + loc + " is pregnant!");
            PregnantBehaviour pB = new PregnantBehaviour();
            a = pB.getAction(dino, map);
        }
        else if(dino instanceof BabyDinosaur){
            GrowBehaviour gB = new GrowBehaviour(dino.getTurns());
            a = gB.getAction(dino,map);
        }
        else if(((dino.getDisplayChar() == 'S' && dino.getFoodLevel() >= 50) ||
                (dino.getDisplayChar() == 'B' && dino.getFoodLevel() >= 70))
        && !dino.isPregnant() && !(dino instanceof BabyDinosaur)){
            System.out.println(dino.getName() + " gender " + dino.getGender() + " at " + loc + " wants to mate!");
            BreedBehaviour bB = new BreedBehaviour();
            a = bB.getAction(dino, map);
        }
        else if(dino.isHungry()){
            System.out.println(dino.getName() + " at " + loc + " is hungry!");
            HungerBehaviour hB = new HungerBehaviour();
            a = hB.getAction(dino, map);
        }
        else{
            System.out.println(dino.getName() + " at " + loc + " is wandering!");
            WanderBehaviour wB = new WanderBehaviour();
            a = wB.getAction(dino, map);
        }
        return a;

    }
}
