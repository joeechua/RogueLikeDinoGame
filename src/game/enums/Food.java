package game.enums;

import edu.monash.fit2099.engine.Item;
import game.items.CarnivoreMealKit;
import game.items.Corpse;
import game.items.Egg;
import game.items.VegetarianMealKit;

public enum Food {
    EGG(10, Egg.class),
    CORPSE(100, Corpse.class),
    VEGETARIAN_MEAL_KIT(100, VegetarianMealKit.class),
    CARNIVORE_MEAL_KIT(100, CarnivoreMealKit.class);

    public final int upLevel;
    private Class<?> classType;

    public Class<?> getClassType() {
        return classType;
    }

    Food(int upLevel, Class<?> classType){
        this.upLevel = upLevel;
        this.classType = classType;
    }

    public int getUpLevel(Item food) {
        return this.upLevel;
    }
}
