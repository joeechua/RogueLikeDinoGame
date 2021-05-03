package game.ground;

import edu.monash.fit2099.engine.*;

import java.util.Random;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	private Random random = new Random();

	public Dirt() {
		super('.');
	}

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

			if(trees > 0 || bush < 2){
				if(random.nextDouble() <= 0.1){
					location.setGround(new Bush());
				}
			}
			else if(location.getGround() instanceof Tree){
			}
			else {
				if(random.nextDouble() <= 0.01){
					location.setGround(new Bush());
				}
			}
		}
	}

	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		return new Actions(null);
	}
}
