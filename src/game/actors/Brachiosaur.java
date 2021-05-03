package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.behaviours.Behaviour;
import game.enums.DinosaurCapabilities;
import game.enums.Food;
import game.enums.Gender;
import game.enums.Species;

import java.util.ArrayList;


public class Brachiosaur extends Dinosaur {

    public Brachiosaur() {
        super(Species.B.name(), 'B', 100);
        capabilities.add(DinosaurCapabilities.HERBIVORE);
        capabilities.add(DinosaurCapabilities.LONG_NECK);
        setEdibleFoodList(Food.getFoodList(this));
    }

    public Brachiosaur(Gender inputGender){
        super(Species.B.name(), 'B', 100);
        capabilities.add(DinosaurCapabilities.HERBIVORE);
        capabilities.add(DinosaurCapabilities.LONG_NECK);
        setEdibleFoodList(Food.getFoodList(this));
        gender = inputGender;
    }
}
