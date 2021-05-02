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
        locList = new int[][]{left, right, up, down, leftUp, rightUp, leftDown, rightDown};

        //go through each location in list
        //attack and Eat?
        Location loc = map.at(sini[0], sini[1]);
        if (map.isAnActorAt(loc) && !dino.isHerbivore()) {
            Actor target = map.getActorAt(loc);
            if (target.getDisplayChar() == 'S' || target.getDisplayChar() == 's') {
                ret = new AttackAction(target);
                
            }
        }
        //eat the stuff available
        else {
            List<Item> items = loc.getItems();
            if(items.size() == 0){
                Ground g = loc.getGround();
                if(g instanceof Bush){
                    Bush b = (Bush) g;
                    if(b.gotFruit()){
                        ret = new EatingAction(b.getBushFruit());
                    }
                }
                else if(g instanceof Tree){
                    Tree t = (Tree) g;
                    if(t.gotFruit() && dino.hasCapability(DinosaurCapabilities.LONG_NECK)){
                        ret = new EatingAction(new Fruit());
                    }
                }
            }
            else{
                for (Item it : items) {
                    Class c = it.getClass();
                    if(dino.canEat(it)){
//                    if(it instanceof Fruit){
//                        Fruit fruit = (Fruit) it;
//                        if(fruit.isOnTree() && dino.hasCapability(DinosaurCapabilities.LONG_NECK)){
//                            ret = new EatingAction(it);
//                        }
//                    }
//                    else{
//                        ret = new EatingAction(it);
//                    }
                        ret = new EatingAction(it);
                    }
                }
            }
            //move towards a target that we need to find
            if (ret == null) {
                ret = moveCloser(dino, map);
            }
        }
        dino.setPrevLoc(map.locationOf(dino));
        return ret;
    }


    public Action moveCloser (Dinosaur dino, GameMap map){
        Action possible = null;
        Action ret = null;
        Location food = null;

        for (int[] i : locList) {
            if(i[0] < map.getXRange().max() && i[1] < map.getYRange().max()
            && i[0] > map.getXRange().min() && i[1]> map.getYRange().min()) {
                food = map.at(i[0], i[1]);
                if (map.isAnActorAt(food) && !dino.isHerbivore()) {
                    Actor target = map.getActorAt(food);
                    if (target.getDisplayChar() == 'b') {
                        System.out.println("got meat");
                        ret = new MoveActorAction(food, direction[i[2]]);
                    }
                }
                else if (food.getItems().size() > 0) {
                    for (Item it : food.getItems()) {
                        if(dino.canEat(it)){
                            System.out.println("got vege");
                            ret = new MoveActorAction(food, direction[i[2]]);
                        }
                    }
                } else {
                    Ground ok = food.getGround();
                    if (ok.getClass() == Tree.class) {
                        Tree t = (Tree) ok;
                        if (possible == null && !food.containsAnActor() && food != dino.getPrevLoc()) {
                            if (t.gotFruit())
                                System.out.println("tree got fruit");
                                possible = new MoveActorAction(food, direction[i[2]]);
                        }
                    } else if (ok.getClass() == Bush.class) {
                        Bush b = (Bush) ok;
                        if (possible == null && !food.containsAnActor() && food != dino.getPrevLoc()) {
                            if (b.gotFruit())
                                System.out.println("bush got fruit ");
                                possible = new MoveActorAction(food, direction[i[2]]);
                        }
                    }
                }
            }
            if (ret == null && possible != null) {
                ret = possible;
            }
            int[] use = null;
            if (ret == null) {
                do {
                    use = locList[random.nextInt(locList.length)];
                    food = map.at(use[0], use[1]);
                }
                while (food == dino.getPrevLoc());
                ret = new MoveActorAction(food, direction[use[2]]);
            }
            dino.setPrevLoc(food);
            }
        return ret;
    }
}
