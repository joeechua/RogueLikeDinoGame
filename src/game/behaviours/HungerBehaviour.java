package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.EatingAction;
import game.actions.LandingTakeOffAction;
import game.actors.Allosaur;
import game.actors.BabyPterodactyl;
import game.actors.Dinosaur;
import game.actors.Pterodactyl;
import game.enums.DinosaurCapabilities;
import game.ground.Bush;
import game.ground.Lake;
import game.ground.Tree;
import game.items.*;

import java.util.List;
import java.util.Random;

/**
 * Behaviour that allows dinosaur to eat or prey on other dinosaurs
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 3.0
 * @see Behaviour
 */
public class HungerBehaviour implements Behaviour {

    private Random random = new Random();
    private Location here;

    /**
     * Returns action to be performed by dinosaur so it can relief hunger
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return EatingAction instance or MoveActorAction instance or AttackAction instance depending on circumstance
     * @see Actor
     * @see Dinosaur#getDisplayChar()
     * @see GameMap
     * @see Location#getExits()
     * @see AttackAction
     * @see EatingAction
     * @see Bush#getBushFruit()
     * @see Tree#getTreeFruit()
     * @see Item#getClass()
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;
        Action ret = null;

        here = map.locationOf(dino);

        if (!dino.isHerbivore()) {
            //check all adjacent squares for food
            for (Exit exit : here.getExits()) {
                if (dino instanceof Allosaur && exit.getDestination().containsAnActor() && map.getActorAt(exit.getDestination()).getDisplayChar() != '@') {
                    Dinosaur carnivoreFood = (Dinosaur) map.getActorAt(exit.getDestination());
                    if (carnivoreFood.getDisplayChar() == 'S' || carnivoreFood.getDisplayChar() == 's') {
                        ret = new AttackAction(carnivoreFood);
                    }
                    else if((carnivoreFood.getDisplayChar() == 'P' || carnivoreFood.getDisplayChar() == 'p')
                    && !carnivoreFood.getOnTree() && !carnivoreFood.isFlying()){
                        ret = new EatingAction(carnivoreFood, here);
                    }
                }
                //for Pterodactyl to eat fish
                else if((dino instanceof Pterodactyl || dino instanceof BabyPterodactyl)
                        && exit.getDestination().getGround() instanceof Lake){
                    Lake lake = (Lake) exit.getDestination().getGround();
                    if(lake.gotFish()){
                        ret = new EatingAction(lake.getFish().get(0), lake);
                    }

                }
                //no live dinosaurs so eat the possible item
                else{
                    for(Item food: exit.getDestination().getItems()){
                        if(dino instanceof Pterodactyl && food instanceof Corpse){
                            //if corpse then land on it
                            if(dino.isFlying()){
                                ret = new LandingTakeOffAction(exit.getDestination(), false);
                            }
                            else{
                                ret = new EatingAction(food, exit.getDestination());
                            }
                        }
                        else if(dino.canEat(food)){
                            ret = new EatingAction(food, exit.getDestination());
                        }
                    }
                }
            }

        }
        //herbivore
        else {
            //go through all the items in the square to find food
            List<Item> items = here.getItems();
            if (items.size() == 0) {
                Ground g = here.getGround();
                if (g instanceof Bush) {
                    Bush b = (Bush) g;
                    if (b.gotFruit()) {
                        ret = new EatingAction(b.getBushFruit().get(0),b);
                    }
                } else if (g instanceof Tree) {
                    Tree t = (Tree) g;
                    if (t.gotFruit() && dino.hasCapability(DinosaurCapabilities.LONG_NECK)) {
                        ret = new EatingAction(t.getTreeFruit().get(0), t);
                    }
                }
            } else {
                for (Item it : items) {
                    if (dino.canEat(it)) {
                        ret = new EatingAction(it,here.getGround());
                    }
                }
            }
        }
        //move towards a target that we need to find
        if (ret == null) {
            ret = moveCloser(dino, map);
        }

        dino.setPrevLoc(map.locationOf(dino));
        return ret;
    }

    /**
     * Allows dinosaur to move closer to it's target food
     * @param dino dinosaur to be moved
     * @param map map where dinosaur is on
     * @return MoveActorAction instance
     */
    public Action moveCloser(Dinosaur dino, GameMap map) {
        Action possible = null;
        Action ret = null;
        Location food = null;
        Exit temp = null;

        //if dino is carnivore
        if(!dino.isHerbivore()){
            for (Exit exits : here.getExits()) {
                //go through exits of all exits to see if there is possible target to follow
                for(Exit exit: exits.getDestination().getExits()){
                    if (exit.getDestination().containsAnActor() && map.getActorAt(exit.getDestination()).getDisplayChar() != '@') {
                        Dinosaur carnivoreFood = (Dinosaur) map.getActorAt(exit.getDestination());
                        if (carnivoreFood.getDisplayChar() == 'S' || carnivoreFood.getDisplayChar() == 's'){
                            if(carnivoreFood.getAttackTurns() == 0){
                                Action fB = new FollowBehaviour(carnivoreFood).getAction(dino, map);
                                return fB;
                            }
                        }
                    }
                    //for Pterodactyl to get fish
                    else if(exit.getDestination().getGround() instanceof Lake
                            && (dino instanceof Pterodactyl || dino instanceof BabyPterodactyl)){
                        Lake lake = (Lake) exit.getDestination().getGround();
                        if(lake.gotFish()){
                            return new MoveActorAction(exits.getDestination(), exits.getName());
                        }
                    }
                    //or else look for food
                    else{
                        for(Item it: exit.getDestination().getItems()){
                            if(it.getClass() == Corpse.class || it.getClass() == Egg.class){
                                return new MoveActorAction(exits.getDestination(), exits.getName());
                            }
                        }
                    }
                }
            }
        }
        //if dinosaur is herbivore go through the adjacent squares for items
        else{
            for (Exit exit : here.getExits()) {
                food = exit.getDestination();
                if (food.getItems().size() > 0) {
                    for (Item it : food.getItems()) {
                        if (dino.canEat(it)) {
                            ret= new MoveActorAction(food, exit.getName());
                        }
                    }
                }
                //the floor dont have so go through the bush and trees
                else {
                    Ground ok = food.getGround();
                    if (ok.getClass() == Tree.class) {
                        Tree t = (Tree) ok;
                        if (possible == null && food != dino.getPrevLoc() && t.gotFruit()) {
                            possible= new MoveActorAction(food, exit.getName());
                        }
                    } else if (ok.getClass() == Bush.class) {
                        Bush b = (Bush) ok;
                        if (possible == null && food != dino.getPrevLoc() && b.gotFruit()) {
                            possible = new MoveActorAction(food, exit.getName());
                        }
                    }
                }
                if (ret == null && possible != null) {
                    ret = possible;
                }
                if (ret == null) {
                    do {
                        temp = here.getExits().get(random.nextInt(here.getExits().size()));
                        food = temp.getDestination();
                    }
                    while (food == dino.getPrevLoc());
                    if(food.canActorEnter(dino))
                        ret = new MoveActorAction(food, temp.getName());
                }
                //just to make sure the dinosaur doesn't go back and forth in the same two squares
                dino.setPrevLoc(food);
                return ret;
            }
        }
        WanderBehaviour wB = new WanderBehaviour();
        ret = wB.getAction(dino, map);
        return ret;
    }
}

