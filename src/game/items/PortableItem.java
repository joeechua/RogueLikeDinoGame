package game.items;

import edu.monash.fit2099.engine.*;

import java.util.List;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {

	/**
	 * Constructor
	 * @param name name of the portable item
	 * @param displayChar display character of the portable item
	 * @see Item
	 */
	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}


}
