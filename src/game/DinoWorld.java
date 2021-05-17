package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

public class DinoWorld extends World{
    /**
     * Constructor.
     *
     * @param display the Display that will display this World.
     */
    public DinoWorld(Display display) {
        super(display);
    }

    private int turns;
    private Random random = new Random();

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

            if(turns % 10 == 0 && random.nextDouble() <= 0.2) {
                System.out.println("it's raining!!");
                for (GameMap gameMap : gameMaps) {
                    DinoGameMap dMap = (DinoGameMap) gameMap;
                    dMap.rain();
                }
            }
            turns++;



        }
        display.println(endGameMessage());
    }

    public void rain(){
        for(GameMap map: gameMaps){
            DinoGameMap dMap = (DinoGameMap) map;
            dMap.rain();
        }
    }
}