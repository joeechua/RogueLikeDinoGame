package game.ground;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import game.actions.PurchasingAction;
import game.actors.Player;
import game.enums.VendingItems;
import game.items.*;

import java.util.Scanner;

public class VendingMachine extends Ground {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public VendingMachine(char displayChar) {
        super('V');
    }


    //if kena Vending machine then will print a menu of what u wanna buy
    //cliick what u wanna buy
    //if u got nough money then gautim u get it in inventory
    //not enough money then print "not rnough money"


    public void selectMenu(Player player) {
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
            //player.purchasingAction
            purchase = new PurchasingAction(price, VendingItems.FRUIT);

        }
        else if(choice == 2){
            int price = VendingItems.VEGETARIAN_MEAL_KIT.value;
            //player.purchasingAction
            purchase = new PurchasingAction(price, VendingItems.VEGETARIAN_MEAL_KIT);
        }
        else if(choice == 3){
            int price = VendingItems.CRANIVORE_MEAL_KIT.value;
            //player.purchasingAction
            purchase = new PurchasingAction(price, VendingItems.CRANIVORE_MEAL_KIT);
        }
        else if(choice == 4){
            int price = VendingItems.STEGOSAUR_EGG.value;
            //player.purchasingAction
            purchase = new PurchasingAction(price, VendingItems.STEGOSAUR_EGG);
        }
        else if(choice == 5){
            int price = VendingItems.BRACHIOSAUR_EGG.value;
            //player.purchasingAction
            purchase = new PurchasingAction(price, VendingItems.BRACHIOSAUR_EGG);
        }
        else if(choice == 6){
            int price = VendingItems.ALLOSAUR_EGG.value;
            //player.purchasingAction
            purchase = new PurchasingAction(price, VendingItems.ALLOSAUR_EGG);
        }
        else if(choice == 7){
            int price = VendingItems.LASER_GUN.value;
            //player.purchasingAction
            purchase = new PurchasingAction(price, VendingItems.LASER_GUN);
        }
        else{
            System.out.println("No purchase made from vending machine");
        }
    }


}
