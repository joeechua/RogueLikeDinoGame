package game.enums;

/**
 * Collection of items available in the vending machine
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see game.ground.VendingMachine
 */
public enum VendingItems {
    FRUIT(30),
    VEGETARIAN_MEAL_KIT(100),
    CRANIVORE_MEAL_KIT(500),
    STEGOSAUR_EGG(200),
    BRACHIOSAUR_EGG(500),
    ALLOSAUR_EGG(1000),
    PTERODACTYL_EGG(200),
    LASER_GUN(500);

    public final int value;

    /**
     * Constructor
     * @param value "price" of the item in ecoPoints
     */
    VendingItems(int value){
        this.value = value;
    }

    /**
     * Allows outside classes (VendingMachine) to access the "price" of the vending machine item
     * @return value associated with the vendingItem.
     */
    public int getValue() {
        return this.value;
    }

}
