package game.ground;

import edu.monash.fit2099.engine.*;
import game.actions.PurchasingAction;
import game.actors.Player;
import game.enums.VendingItems;
import game.items.*;

import java.util.Scanner;

public class VendingMachine extends Ground {

    /**
     * Constructor.
     */
    public VendingMachine() {
        super('⌻');
        for (VendingItems item: VendingItems.values()){
            addCapability(item);
        }
    }

    public static String selectMenu(Actor actor, GameMap map) {
        int option = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Purchasing Item \n1. Fruit \n2. Vegetarian Meal Kit \n3. Carnivore Meal Kit \n4. Stegosaur Egg " +
                "\n5. Brachiosaur Egg \n6. Allosaur Egg \n7. Laser Gun \n");
        System.out.print("Select an option: ");

        try {
            option = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Choice entered is not a numeral!\n");
        }
        return switch (option) {
            case 1 -> getPurchasingAction(VendingItems.FRUIT.value, VendingItems.FRUIT).execute(actor, map);
            case 2 -> getPurchasingAction(VendingItems.VEGETARIAN_MEAL_KIT.value, VendingItems.VEGETARIAN_MEAL_KIT).execute(actor, map);
            case 3 -> getPurchasingAction(VendingItems.CRANIVORE_MEAL_KIT.value, VendingItems.CRANIVORE_MEAL_KIT).execute(actor, map);
            case 4 -> getPurchasingAction(VendingItems.STEGOSAUR_EGG.value, VendingItems.STEGOSAUR_EGG).execute(actor, map);
            case 5 -> getPurchasingAction(VendingItems.BRACHIOSAUR_EGG.value, VendingItems.BRACHIOSAUR_EGG).execute(actor, map);
            case 6 -> getPurchasingAction(VendingItems.ALLOSAUR_EGG.value, VendingItems.ALLOSAUR_EGG).execute(actor, map);
            case 7 -> getPurchasingAction(VendingItems.LASER_GUN.value, VendingItems.LASER_GUN).execute(actor, map);
            default -> "";
        };
    }

    public static PurchasingAction getPurchasingAction(int price, VendingItems item) {
        return new PurchasingAction(price, item);
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        for(VendingItems item: VendingItems.values()){
            actions.add(getPurchasingAction(item.getValue(), item));
        }
        return actions;
    }
}
