package game.items;

import edu.monash.fit2099.engine.Item;

public class Fish extends PortableItem {
    /***
     * Constructor.
     */
    public Fish() {
        super("Fish", '鱼');
    }

    //simple but doing this will make it easy to extend (i want to make it available in vending machine)
}
