package game.items;

import edu.monash.fit2099.engine.Item;

public class VegetarianMealKit extends PortableItem {
    /***
     * Constructor.
     *  @param name the name of this Item
     */
    public VegetarianMealKit() {
        super("Vegetarian Meal Kit", 'v');
        capabilities.addCapability(ItemCapabilities.EATEN);
    }
}
