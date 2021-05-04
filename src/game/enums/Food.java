package game.enums;

import game.actors.Dinosaur;
import game.items.*;

import java.util.ArrayList;

/***
 * A collection of food items with foodLevel values, class types, and a vegetarian check
 */
public enum Food {
    EGG(10, Egg.class, false),
    BRACH_CORPSE(100, Corpse.class, false),
    CORPSE(50, Corpse.class, false),
    STEG_FRUIT(10, Fruit.class, true),
    BRACH_FRUIT(5, Fruit.class, true),
    FED_FRUIT(20, Fruit.class, true),
    VEGETARIAN_MEAL_KIT(100, VegetarianMealKit.class, true),
    CARNIVORE_MEAL_KIT(100, CarnivoreMealKit.class, false);

    private final int upLevel;
    private Class<?> classType;
    private final boolean isVeg;

    /***
     * Returns the class that the item is associated to
     * @return Class type of the item
     */
    public Class<?> getClassType() {
        return classType;
    }

    /***
     * Constructor.
     * @param upLevel the nummber that foodLevel will increase by if this food is eaten
     * @param classType class type of the food
     * @param isVeg boolean representing whether or not the item is for vegetarians
     */
    Food(int upLevel, Class<?> classType, boolean isVeg){
        this.upLevel = upLevel;
        this.classType = classType;
        this.isVeg = isVeg;
    }

    /***
     * Allows outside classes to get the upValue of the food item
     * @param food name of the food item
     * @return upLevel of the food item
     */
    public int getUpLevel(String food) {
        for(Food f: Food.values()){
            if(f.name() == food){
                return f.upLevel;
            }
        }
        return 0;
    }

    /***
     * Returns a list of food items that a dinosaur can consume
     * @param dino dinosaur that food list is for
     * @return a list of food items
     * @see Dinosaur
     */
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

    /***
     * Lets outside classes tell whether the food is vegetarian friendly
     * @return boolean representing whether food is vegetarian friendly
     */
    public boolean getIsVeg(){
        return isVeg;
    }
}
