package game.actors;

import edu.monash.fit2099.engine.IntrinsicWeapon;
import game.enums.DinosaurCapabilities;

public class BabyAllosaur extends BabyDinosaur{
    /**
     * Constructor.
     */
    public BabyAllosaur() {
        super("Baby Allosaur",'a',1000);
        dinoChar = 'a';
        capabilities.add(DinosaurCapabilities.CARNIVORE);
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(20,"claws");
    }
}
