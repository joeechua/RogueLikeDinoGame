package game.items;

import edu.monash.fit2099.engine.Item;

public class Fish extends Item {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Fish(String name, char displayChar, boolean portable) {
        super("Fish", '鱼', true);
    }

    //simple but doing this will make it easy to extend (i want to make it available in vending machine)
}
