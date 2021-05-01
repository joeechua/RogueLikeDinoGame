package game.items;


import edu.monash.fit2099.engine.Item;

public class CarnivoreMealKit extends PortableItem {
    /***
     * Constructor.
     *  @param name the name of this Item
     */
    public CarnivoreMealKit() {
        super("Carnivore Meal Kit", 'c');
        capabilities.addCapability(ItemCapabilities.EATEN);
    }
}
