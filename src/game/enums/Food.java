package game.enums;

public enum Food {
    EGG(10),
    CORPSE(100),
    VEGETARIAN_MEAL_KIT(100),
    CARNIVORE_MEAL_KIT(100);

    public final int upLevel;

    private Food(int upLevel){
        this.upLevel = upLevel;
    }

    public int getUpLevel() {
        return this.upLevel;
    }
}
