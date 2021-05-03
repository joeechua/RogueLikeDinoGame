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
    private String[] direction = new String[]{"west", "east", "south", "north",
            "south west", "north west", "south east", "north east"};
    private int[][] locList;
    private Location here;


    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;
        Action ret = null;

        //Locations to check
        here = map.locationOf(dino);
        int[] sini = new int[]{here.x(), here.y(), -1};
        int[] left = new int[]{here.x() - 1, here.y(), 0};
        int[] right = new int[]{here.x() + 1, here.y(), 1};
        int[] down = new int[]{here.x(), here.y() + 1, 2};
        int[] up = new int[]{here.x(), here.y() - 1, 3};
        int[] leftDown = new int[]{here.x() - 1, here.y() + 1, 4};
        int[] leftUp = new int[]{here.x() - 1, here.y() - 1, 5};
        int[] rightDown = new int[]{here.x() + 1, here.y() + 1, 6};
        int[] rightUp = new int[]{here.x() + 1, here.y() - 1, 7};
        //locList = new int[][]{left, right, up, down, leftUp, rightUp, leftDown, rightDown};

        //go through each location in list
        //attack and Eat?
        if (!dino.isHerbivore()) {
            for (Exit exit : here.getExits()) {
                if (exit.getDestination().containsAnActor() && map.getActorAt(exit.getDestination()).getDisplayChar() != '@') {
                    Dinosaur carnivoreFood = (Dinosaur) map.getActorAt(exit.getDestination());
                    if (carnivoreFood.getDisplayChar() == 'S' || carnivoreFood.getDisplayChar() == 's') {
                        ret = new AttackAction(carnivoreFood);
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
                        ret = new EatingAction(b.getBushFruit());
                    }
                } else if (g instanceof Tree) {
                    Tree t = (Tree) g;
                    if (t.gotFruit() && dino.hasCapability(DinosaurCapabilities.LONG_NECK)) {
                        ret = new EatingAction(new Fruit());
                    }
                }
            } else {
                for (Item it : items) {
                    if (dino.canEat(it)) {
                        ret = new EatingAction(it);
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
                        if (carnivoreFood.getDisplayChar() == 'S' || carnivoreFood.getDisplayChar() == 's') {
                            return new MoveActorAction(exits.getDestination(), exits.getName());
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
                            System.out.println(exit.getName() + " got fruit");
                            return new MoveActorAction(food, exit.getName());
                        }
                    }
                }
                else {
                    Ground ok = food.getGround();
                    if (ok.getClass() == Tree.class) {
                        Tree t = (Tree) ok;
                        if (possible == null && food != dino.getPrevLoc()) {
                            if (t.gotFruit())
                                System.out.println(exit.getName() + " tree got fruit");
                            return new MoveActorAction(food, exit.getName());
                        }
                    } else if (ok.getClass() == Bush.class) {
                        Bush b = (Bush) ok;
                        if (possible == null && food != dino.getPrevLoc()) {
                            if (b.gotFruit())
                                System.out.println(exit.getName() + " bush got fruit");
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
                    ret = new MoveActorAction(food, temp.getName());
                }
                dino.setPrevLoc(food);
            }
        }
        return ret;
    }
}

       // for (int[] i : locList) {
//            if(i[0] < map.getXRange().max() && i[1] < map.getYRange().max()
//            && i[0] > map.getXRange().min() && i[1]> map.getYRange().min()) {
//                food = map.at(i[0], i[1]);
//                if (food.getItems().size() > 0) {
//                    for (Item it : food.getItems()) {
//                        if(dino.canEat(it)){
//                            ret = new MoveActorAction(food, direction[i[2]]);
//                        }
//                    }
//                } else {
//                    Ground ok = food.getGround();
//                    if (ok.getClass() == Tree.class) {
//                        Tree t = (Tree) ok;
//                        if (possible == null && !food.containsAnActor() && food != dino.getPrevLoc()) {
//                            if (t.gotFruit())
//                                possible = new MoveActorAction(food, direction[i[2]]);
//                        }
//                    } else if (ok.getClass() == Bush.class) {
//                        Bush b = (Bush) ok;
//                        if (possible == null && !food.containsAnActor() && food != dino.getPrevLoc()) {
//                            if (b.gotFruit())
//                                possible = new MoveActorAction(food, direction[i[2]]);
//                        }
//                    }
//                }
            //}
//            if (ret == null && possible != null) {
//                ret = possible;
//            }
//            int[] use = null;
//            if (ret == null) {
//                do {
//                    use = locList[random.nextInt(locList.length)];
//                    food = map.at(use[0], use[1]);
//                }
//                while (food == dino.getPrevLoc());
//                ret = new MoveActorAction(food, direction[use[2]]);
//            }
//            dino.setPrevLoc(food);
//            }
//        return ret;

