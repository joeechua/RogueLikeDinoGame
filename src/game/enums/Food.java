package game.enums;

import edu.monash.fit2099.engine.Item;
import game.actors.Dinosaur;
import game.items.*;

import java.util.ArrayList;


public enum Food {
    EGG(10, Egg.class, false),
    CORPSE(100, Corpse.class, false),
    STEG_FRUIT(10, Fruit.class, true),
    BRACH_FRUIT(5, Fruit.class, true),
    FED_FRUIT(20, Fruit.class, true),
    VEGETARIAN_MEAL_KIT(100, VegetarianMealKit.class, true),
    CARNIVORE_MEAL_KIT(100, CarnivoreMealKit.class, false);

    private final int upLevel;
    private Class<?> classType;
    private final boolean isVeg;


    public Class<?> getClassType() {
        return classType;
    }

    Food(int upLevel, Class<?> classType, boolean isVeg){
        this.upLevel = upLevel;
        this.classType = classType;
        this.isVeg = isVeg;
    }

    public int getUpLevel(String food) {
        for(Food f: Food.values()){
            if(f.name() == food){
                return f.upLevel;
            }
        }
        return 0;
    }

    public static ArrayList<Food> getFoodList(Dinosaur dino){
        ArrayList<Food> foodList = new ArrayList<>();
        for(Food f: Food.values()){
            if (dino.isHerbivore() && f.isVeg){
                foodList.add(f);
            }
            else if(!dino.isHerbivore() && !f.isVeg){
                foodList.add(f);
            }
        }
        return foodList;
    }
}
