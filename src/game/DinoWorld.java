package game;

import edu.monash.fit2099.engine.ActorLocations;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.World;

public class DinoWorld extends World{
    /**
     * Constructor.
     *
     * @param display the Display that will display this World.
     */
    public DinoWorld(Display display) {
        super(display);
    }

    public ActorLocations getActorLocations(){
        ActorLocations ret = this.actorLocations;
        return ret;
    }

}