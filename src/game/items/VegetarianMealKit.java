package game.items;

import game.enums.ItemCapabilities;

/**
 * Class for vegetarian meal kit
 */
public class VegetarianMealKit extends PortableItem {
    /**
     * Constructor.
     * @see PortableItem
     * @see ItemCapabilities
     */
    public VegetarianMealKit() {
        super("Vegetarian Meal Kit", 'v');
        capabilities.addCapability(ItemCapabilities.EATEN);
    }
}
