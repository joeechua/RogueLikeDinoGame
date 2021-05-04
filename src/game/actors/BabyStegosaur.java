package game.actors;

import game.enums.DinosaurCapabilities;
import game.enums.Food;

public class BabyStegosaur extends BabyDinosaur{
    /**
     * Constructor.
     */
    public BabyStegosaur() {
        super("Baby Stegosaur",'s',100);
        dinoChar = 's';
        capabilities.add(DinosaurCapabilities.HERBIVORE);
        setEdibleFoodList(Food.getFoodList(this));
        attackTurns = 0;
    }

    @Override
    public void tick() {
        super.tick();
        if(attackTurns != 0){
            attackTurns--;
        }
    }
}
