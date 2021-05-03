package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.behaviours.Behaviour;
import game.behaviours.BreedBehaviour;
import game.behaviours.HungerBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.DinosaurCapabilities;
import game.enums.Food;
import game.enums.Gender;
import game.enums.Species;

import java.util.ArrayList;

public class Allosaur extends Dinosaur {

    private ArrayList<Behaviour> behaviour;

    public Allosaur() {
        super(Species.A.name(), 'A', 100);
        capabilities.add(DinosaurCapabilities.CARNIVORE);
        setEdibleFoodList(Food.getFoodList(this));
    }

    public Allosaur(Gender inputGender) {
        super(Species.A.name(), 'A', 100);
        capabilities.add(DinosaurCapabilities.CARNIVORE);
        setEdibleFoodList(Food.getFoodList(this));
        gender = inputGender;
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(20,"claws");
    }
}
