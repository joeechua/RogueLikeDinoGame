package game.ground;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents Wall.
 * @see Ground
 */
public class Wall extends Ground {

	/**
	 * Constructor.
	 */
	public Wall() {
		super('#');
	}

	/**
	 * Determine whether an actor can enter this location
	 * @param actor the Actor to check
	 * @return a boolean, true if the Actor can enter this location
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
