package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.behaviours.Behaviour;
import game.behaviours.BreedBehaviour;
import game.behaviours.HungerBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.DinosaurCapabilities;
import game.enums.Species;

import java.util.ArrayList;

public class Allosaur extends Dinosaur {

    private ArrayList<Behaviour> behaviour;

    public Allosaur() {
        super(Species.A.name(), 'd', 100);
        capabilities.add(DinosaurCapabilities.CARNIVORE);
    }

}
