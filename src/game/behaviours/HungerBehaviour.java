package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.DieAction;
import game.actions.EatingAction;
import game.actors.Dinosaur;
import game.actors.Stegosaur;
import game.enums.DinosaurCapabilities;
import game.enums.Food;
import game.ground.Bush;
import game.ground.Tree;
import game.items.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HungerBehaviour implements Behaviour {


    public HungerBehaviour() {
    }

    private Random random = new Random();
    private Location here;


    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;
        Action ret = null;

        //Locations to check
        here = map.locationOf(dino);

        //attack and Eat?
        if (!dino.isHerbivore()) {
            for (Exit exit : here.getExits()) {
                if (exit.getDestination().containsAnActor() && map.getActorAt(exit.getDestination()).getDisplayChar() != '@') {
                    Dinosaur carnivoreFood = (Dinosaur) map.getActorAt(exit.getDestination());
                    if (carnivoreFood.getDisplayChar() == 'S' || carnivoreFood.getDisplayChar() == 's') {
                        ret = new AttackAction(carnivoreFood);
                    }
                }
                else{
                    for(Item food: exit.getDestination().getItems()){
                        if(dino.canEat(food)){
                            ret = new EatingAction(food, exit.getDestination());
                        }
                    }
                }
            }

        }
        else {
            List<Item> items = here.getItems();
            if (items.size() == 0) {
                Ground g = here.getGround();
                if (g instanceof Bush) {
                    Bush b = (Bush) g;
                    if (b.gotFruit()) {
                        ret = new EatingAction(b.getBushFruit(),b);
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


    public Action moveCloser(Dinosaur dino, GameMap map) {
        Action possible = null;
        Action ret = null;
        Location food = null;
        Exit temp = null;

        if(!dino.isHerbivore()){
            for (Exit exits : here.getExits()) {
                for(Exit exit: exits.getDestination().getExits()){
                    if (exit.getDestination().containsAnActor() && map.getActorAt(exit.getDestination()).getDisplayChar() != '@') {
                        Dinosaur carnivoreFood = (Dinosaur) map.getActorAt(exit.getDestination());
                        if (carnivoreFood.getDisplayChar() == 'S' || carnivoreFood.getDisplayChar() == 's'){
                            if(carnivoreFood.getAttackTurns() == 0){
                                ret= new MoveActorAction(exits.getDestination(), exits.getName());
                            }
                        }
                    }
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
                    System.out.println();
                    ret = possible;
                }
                if (ret == null) {
                    do {
                        temp = here.getExits().get(random.nextInt(here.getExits().size()));
                        food = temp.getDestination();
                    }
                    while (food == dino.getPrevLoc());
                    ret = new MoveActorAction(food, temp.getName());
                }
                dino.setPrevLoc(food);
                return ret;
            }
        }
        return ret;
    }
}

