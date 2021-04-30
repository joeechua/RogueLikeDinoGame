package game.ground;

import game.actions.PurchasingAction;
import game.enums.VendingItems;
import game.items.*;

import java.util.Scanner;

public class VendingMachine {

    //if kena Vending machine then will print a menu of what u wanna buy
    //cliick what u wanna buy
    //if u got nough money then gautim u get it in inventory
    //not enough money then print "not rnough money"

    public void selectMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter option: ");
        String c = scanner.nextLine();
        int choice = 7;
        PurchasingAction purchase = null;
        try {
            choice = Integer.parseInt(c);

        } catch (Exception e) {
            System.out.println("Choice entered is not a numeral!\n");
        }
        if(choice == 1){
            int price = VendingItems.FRUIT.value;
            purchase = new PurchasingAction(price, new Fruit("fruit"));

        }
        else if(choice == 2){
            int price = VendingItems.VEGETARIAN_MEAL_KIT.value;
            purchase = new PurchasingAction(price, new VegetarianMealKit("vege vending"));
        }
        else if(choice == 3){
            int price = VendingItems.CRANIVORE_MEAL_KIT.value;
            purchase = new PurchasingAction(price, new CarnivoreMealKit("carnivore vending"));
        }
        else if(choice == 4){
            int price = VendingItems.STEGOSAUR_EGG.value;
            purchase = new PurchasingAction(price, new StegosaurEgg("steg egg"));
        }
        else if(choice == 5){
            int price = VendingItems.BRACHIOSAUR_EGG.value;
            purchase = new PurchasingAction(price, new BrachiosaurEgg("brach egg"));
        }
        else if(choice == 6){
            int price = VendingItems.ALLOSAUR_EGG.value;
            purchase = new PurchasingAction(price, new AllosaurEgg("allo egg"));
        }
        else if(choice == 7){
            int price = VendingItems.LASER_GUN.value;
            purchase = new PurchasingAction(price, new LaserGun("laser gun"));
        }
        else{
            System.out.println("No purchase made from vending machine");
        }
    }


}
