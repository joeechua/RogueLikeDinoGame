package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.EatingAction;
import game.actions.MateAction;
import game.actors.Dinosaur;
import game.enums.DinosaurCapabilities;
import game.ground.Bush;
import game.ground.Tree;
import game.items.*;

import java.util.List;
import java.util.Random;

public class BreedBehaviour implements Behaviour {

    private Random random = new Random();
    private Actor target;
    private String[] direction = new String[]{"west", "east", "south", "north",
            "south west", "north west", "south east", "north east"};
    private int[][] locList;
    private Location here;

    public BreedBehaviour(){}

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;

        here = map.locationOf(dino);
        int[] sini = new int[]{here.x(), here.y(), -1};
        int[] left = new int[]{here.x() - 1, here.y(), 0};
        int[] right = new int[]{here.x() + 1, here.y(), 1};
        int[] up = new int[]{here.x(), here.y() + 1, 2};
        int[] down = new int[]{here.x(), here.y() - 1, 3};
        int[] leftUp = new int[]{here.x() - 1, here.y() + 1, 4};
        int[] leftDown = new int[]{here.x() - 1, here.y() - 1, 5};
        int[] rightUp = new int[]{here.x() + 1, here.y() + 1, 6};
        int[] rightDown = new int[]{here.x() + 1, here.y() - 1, 7};
        locList = new int[][]{left, right, up, down, leftUp, rightUp, leftDown, rightDown};
//        if (map.contains(target)) {
//            map.contains(actor);
//        }


        for(int[] coords:locList) {
            if(coords[0] < map.getXRange().max() && coords[1] < map.getYRange().max()
                    && coords[0] > map.getXRange().min() && coords[1]> map.getYRange().min()){
                Location loc = map.at(coords[0], coords[1]);
                //find and mate
                if (map.isAnActorAt(loc)) {
                    if(map.getActorAt(loc).getDisplayChar() != '@'){
                        Dinosaur target = (Dinosaur) actor;
                        if (!target.isPregnant() && target.getDisplayChar() == dino.getDisplayChar()) {
                            return new MateAction(target);
                        }
                    }
                }
            }
        }
        int[] use = locList[random.nextInt(locList.length)];
        Location lc = map.at(use[0], use[1]);
        return new MoveActorAction(lc,direction[use[2]]);
    }

}
