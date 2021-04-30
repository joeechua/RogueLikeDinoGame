package game.enums;

public enum VendingItems {
    FRUIT(30),
    VEGETARIAN_MEAL_KIT(100),
    CRANIVORE_MEAL_KIT(500),
    STEGOSAUR_EGG(200),
    BRACHIOSAUR_EGG(500),
    ALLOSAUR_EGG(1000),
    LASER_GUN(500);

    public final int value;

    private VendingItems(int value){
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
