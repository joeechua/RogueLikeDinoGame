package game.ground;

import edu.monash.fit2099.engine.*;

import java.util.Random;

/**
 * A class that represents bare dirt.
 * @see Ground
 */
public class Dirt extends Ground {

	private Random random = new Random();

	/**
	 * Constructor.
	 */
	public Dirt() {
		super('.');
	}

	/**
	 * Called once per turn, so that maps can experience the passage of time.
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);
		if(!(location.getGround() instanceof Bush)){
			int trees = 0;
			int bush = 0;

			for (Exit nearby: location.getExits()){
				if(nearby.getDestination().getGround() instanceof Bush){
					bush++;
				}
				else if(nearby.getDestination().getGround() instanceof Tree){
					trees++;
				}
			}
			if(random.nextDouble() <= 0.01){
				location.setGround(new Bush());
			}
			// if at least two squares of bush
			else if(bush >= 2){
				if(random.nextDouble() <= 0.1){
					location.setGround(new Bush());
				}
			}
			// if there's tree no chance to grow
			else if(trees > 0){

			}

		}
	}

	/**
	 * Returns a collection of the Actions that the otherActor can do to the current Actor.
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return A collection of Actions.
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		return new Actions(null);
	}
}
