package game.actors;

import edu.monash.fit2099.engine.IntrinsicWeapon;
import game.enums.DinosaurCapabilities;
import game.enums.Food;

public class BabyAllosaur extends BabyDinosaur{
    /**
     * Constructor.
     */
    public BabyAllosaur() {
        super("Baby Allosaur",'a',1000);
        dinoChar = 'a';
        capabilities.add(DinosaurCapabilities.CARNIVORE);
        setEdibleFoodList(Food.getFoodList(this));
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(20,"claws");
    }
}
