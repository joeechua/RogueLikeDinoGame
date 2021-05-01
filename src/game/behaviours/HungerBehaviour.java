package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.DieAction;
import game.actions.EatingAction;
import game.actors.Dinosaur;
import game.enums.DinosaurCapabilities;
import game.enums.Food;
import game.items.*;

import java.util.List;

public class HungerBehaviour implements Behaviour {


    public HungerBehaviour(){}


    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;

        //Locations to check
        Location here = map.locationOf(dino);
        int[] sini = new int[]{here.x(), here.y()};
        int[] left = new int[]{here.x()-1, here.y()};
        int[] right = new int[]{here.x()+1, here.y()};
        int[] up = new int[]{here.x(), here.y()+1};
        int[] down = new int[]{here.x(), here.y()-1};
        int[] leftUp = new int[]{here.x()-1, here.y()+1};
        int[] leftDown = new int[]{here.x()-1, here.y()-1};
        int[] rightUp = new int[]{here.x()+1, here.y()+1};
        int[] rightDown = new int[]{here.x()+1, here.y()-1};
        int[][] loclist = new int[][]{sini, left, right, up, down, leftUp, rightUp, leftDown, rightDown};


        //if hungry
        if (dino.getFoodLevel() < 100 && dino.isConscious()) {
            //go through each location in list
            for (int[] coords : loclist) {
                Location loc = map.at(coords[0], coords[1]);
                //attack and Eat?
                if (map.isAnActorAt(loc) && dino.hasCapability(DinosaurCapabilities.CARNIVORE)) {
                    Actor target = map.getActorAt(loc);
                    if(target.getDisplayChar() == 'b'){
                        return new AttackAction(target);
                    }
                }
                //eat the stuff available
                else {
                    List<Item> items = loc.getItems();
                    for (Item it : items) {
                        Class c = it.getClass();
                        //if Carnivore eat the more filling food
                        if ((c == AllosaurEgg.class || c == StegosaurEgg.class
                                || c == CarnivoreMealKit.class)
                                && dino.hasCapability(DinosaurCapabilities.CARNIVORE)) {
                            return new EatingAction(it);
                        }
                        //if no choice only check for these
                        else if (c == Corpse.class || c == Fruit.class || c == VegetarianMealKit.class) {
                            return new EatingAction(it);
                        }

                    }
                }
            }
            //move towards a target that we need to find
            //or we could just set it to go towards the player so it can be fed
            return new MoveActorAction(map.at(right[0], right[1]),"west");
        } else if (dino.getFoodLevel() < 100 && dino.isUnconscious()) {
            return new DoNothingAction();
        }
        return null;
    }
}
