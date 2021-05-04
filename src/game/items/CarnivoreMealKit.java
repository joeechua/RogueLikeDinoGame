package game.items;


import game.enums.ItemCapabilities;

/**
 * Class for a Carnivore meal kit object
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see PortableItem
 */
public class CarnivoreMealKit extends PortableItem {
    /**
     * Constructor.
     * @see edu.monash.fit2099.engine.Capabilities
     * @see ItemCapabilities
     */
    public CarnivoreMealKit() {
        super("Carnivore Meal Kit", 'c');
        capabilities.addCapability(ItemCapabilities.EATEN);
    }
}
