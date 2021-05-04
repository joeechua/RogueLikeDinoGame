package game.items;

import game.enums.ItemCapabilities;

/**
 * Class for vegetarian meal kit
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see PortableItem
 */
public class VegetarianMealKit extends PortableItem {
    /**
     * Constructor.
     * @see PortableItem
     * @see ItemCapabilities#EATEN
     */
    public VegetarianMealKit() {
        super("Vegetarian Meal Kit", 'v');
        capabilities.addCapability(ItemCapabilities.EATEN);
    }
}
