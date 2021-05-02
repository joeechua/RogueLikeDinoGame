package game.items;

import edu.monash.fit2099.engine.*;

import java.util.List;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {

	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}

	@Override
	public void tick(Location currentLocation) {
		super.tick(currentLocation);
	}

	@Override
	public void tick(Location currentLocation, Actor actor) {
		super.tick(currentLocation, actor);
	}


}
