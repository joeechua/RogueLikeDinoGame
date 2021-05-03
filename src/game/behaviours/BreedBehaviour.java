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
    protected static String[] direction = new String[]{"west", "east", "south", "north",
            "south west", "north west", "south east", "north east"};
    protected static int[][] locList;
    private Location here;

    public BreedBehaviour(){}

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;

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
//        if (map.contains(target)) {
//            map.contains(actor);
//        }
        Action ret = null;
        for(Exit exit: here.getExits()){
            if (map.isAnActorAt(exit.getDestination())) {
                if(map.getActorAt(exit.getDestination()).getDisplayChar() != '@'){
                    Dinosaur target = (Dinosaur) map.getActorAt(exit.getDestination());
                    if (!target.isPregnant() && target.getDisplayChar() == dino.getDisplayChar()
                    && target.getGender() != dino.getGender()){
                        ret= new MateAction(target);
                    }
                }
            }
        }
        if(ret == null){
            Exit exit = here.getExits().get(random.nextInt(here.getExits().size()));
            ret= new MoveActorAction(exit.getDestination(),exit.getName());
        }
        return ret;
    }

//        for(int[] coords:locList) {
//            if(coords[0] < map.getXRange().max() && coords[1] < map.getYRange().max()
//                    && coords[0] > map.getXRange().min() && coords[1]> map.getYRange().min()){
//                Location loc = map.at(coords[0], coords[1]);
//                //find and mate
//                if (map.isAnActorAt(loc)) {
//                    if(map.getActorAt(loc).getDisplayChar() != '@'){
//                        Dinosaur target = (Dinosaur) map.getActorAt(loc);
//                        if (!target.isPregnant() && target.getDisplayChar() == dino.getDisplayChar()){
//                            ret= new MateAction(target);
//                        }
//                    }
//                }
//            }
//        }

}
