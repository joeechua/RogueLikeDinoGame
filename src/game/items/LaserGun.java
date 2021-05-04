package game.items;

import edu.monash.fit2099.engine.WeaponItem;

/***
 * Class for Laser Gun item
 */
public class LaserGun extends WeaponItem {

    /**
     * Constructor.
     * @see WeaponItem
     */
    public LaserGun() {
        //damage is set to 140 because assignment says it will kill dinosaurs in 1 or 2 zaps
        //140 will kill stegosaur in 1 zap but brachiosaur in 2
        super("Laser Gun", 'L', 140, "zaps");
    }


}
