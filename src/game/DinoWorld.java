package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

/**
 * Class representing the game dino world, including the locations of all Actors, the
 * player, and the playing grid.
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see edu.monash.fit2099.engine.World
 */

public class DinoWorld extends World{

    private int turns;
    private Random random = new Random();

    /**
     * Constructor.
     *
     * @param display the Display that will display this World.
     */
    public DinoWorld(Display display) {
        super(display);
    }

    /**
     * Run the dino game.
     *
     * On each iteration the gameloop does the following: - displays the player's
     * map - processes the actions of every Actor in the game, regardless of map
     *
     * We could either only process the actors on the current map, which would make
     * time stop on the other maps, or we could process all the actors. We chose to
     * process all the actors.
     *
     * @throws IllegalStateException if the player doesn't exist
     * @see DinoGameMap
     */
    @Override
    public void run() {
        if (player == null)
            throw new IllegalStateException();

        // initialize the last action map to nothing actions;
        for (Actor actor : actorLocations) {
            lastActionMap.put(actor, new DoNothingAction());
        }

        // This loop is basically the whole game
        while (stillRunning()) {
            GameMap playersMap = actorLocations.locationOf(player).map();
            playersMap.draw(display);

            // Process all the actors.
            for (Actor actor : actorLocations) {
                if (stillRunning())
                    processActorTurn(actor);
            }

            // Tick over all the maps. For the map stuff.
            for (GameMap gameMap : gameMaps) {
                gameMap.tick();
            }

            // 20% chance to rain every 10 turns
            if(turns % 10 == 0 && random.nextDouble() <= 0.2) {
                System.out.println("It's raining !!");
                for (GameMap gameMap : gameMaps) {
                    DinoGameMap dMap = (DinoGameMap) gameMap;
                    dMap.rain();
                }
            }
            turns++;

        }
        display.println(endGameMessage());
    }
}