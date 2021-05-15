package game.actors;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.actions.AttackAction;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.enums.DinosaurCapabilities;
import game.enums.Food;
import game.enums.Gender;
import game.enums.Species;

/**
 * A herbivorous dinosaur (Stegosaur).
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see Dinosaur
 */
public class Stegosaur extends Dinosaur {

//	/**
//	 * Constructor.
//	 * All Stegosaurs are represented by a 'S' and have 100 hit points.
//	 * has dinosaur capabilities herbivore
//	 * @see Dinosaur
//	 * @see Species
//	 * @see DinosaurCapabilities
//	 * @see Food
//	 */
//	public Stegosaur() {
//		super(Species.S.name(), 'S', 100);
//		capabilities.add(DinosaurCapabilities.HERBIVORE);
//		setEdibleFoodList(Food.getFoodList(this));
//		attackTurns = 0;
//		// init food level of 50
//		setFoodLevel(50);
//		setMinFoodLevel(90);
//		setMaxFoodLevel(100);
//		setMaxUnconsciousTime(20);
//		setMaxWaterLevel(100);
//		setRotTime(20);
//	}

	/**
	 * Constructor.
	 * All Stegosaurs are represented by a 'S' and have 100 hit points.
	 * has dinosaur capabilities herbivore
	 * @param inputGender the gendeer of stegosaur
	 * @see Dinosaur
	 * @see Species
	 * @see DinosaurCapabilities
	 * @see Food
	 */
	public Stegosaur(Gender inputGender){
		super(Species.S.name(), 'S', 100);
		this.addCapability(DinosaurCapabilities.HERBIVORE);
		setEdibleFoodList(Food.getFoodList(this));
		gender = inputGender;
		attackTurns = 0;
		// init food level of 50
		setFoodLevel(50);
		setMinFoodLevel(90);
		setMaxFoodLevel(100);
		setMaxUnconsciousTime(20);
		setMaxWaterLevel(100);
		setRotTime(20);
	}

	/**
	 * Called once per turn, so that maps can experience the passage of time.
	 * @see Dinosaur
	 */
	@Override
	public void tick() {
		super.tick();
		// decrease attack turns by 1 for each tick()
		if(attackTurns != 0){
			attackTurns--;
		}
	}

	/**
	 * Returns a collection of the Actions that the otherActor can do to the current Actor.
	 * @param otherActor the Actor that might be performing attack
	 * @param direction String representing the direction of the other Actor
	 * @param map current GameMap
	 * @return A collection of Actions.
	 * @see Actor
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		return new Actions(new AttackAction(this));
	}
}