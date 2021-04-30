package game.items;

import edu.monash.fit2099.engine.WeaponItem;

public class LaserGun extends WeaponItem {

    /**
     * Constructor.
     *
     * @param name        name of the item
     */
    public LaserGun(String name) {
        super(name, 'L', 100, "zaps");
    }
}
