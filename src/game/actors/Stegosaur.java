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
import game.enums.Gender;
import game.enums.Species;

/**
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends Dinosaur {
	/**
	 * Constructor.
	 * All Stegosaurs are represented by a 'S' and have 100 hit points.
	 */
	public Stegosaur() {
		super(Species.S.name(), 'S', 100);
		capabilities.add(DinosaurCapabilities.HERBIVORE);
		attackTurns = 0;
	}

	public Stegosaur(Gender inputGender){
		super(Species.S.name(), 'S', 100);
		capabilities.add(DinosaurCapabilities.HERBIVORE);
		gender = inputGender;
		attackTurns = 0;
	}

	@Override
	public void tick() {
		super.tick();
		if(attackTurns != 0){
			attackTurns--;
		}
	}

	//	@Override
//	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
//		return new Actions(new AttackAction(this));
//	}
//
//	/**
//	 * Figure out what to do next.
//	 *
//	 * FIXME: Stegosaur wanders around at random, or if no suitable MoveActions are available, it
//	 * just stands there.  That's boring.
//	 *
//	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
//	 */
//	@Override
//	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
//		Action wander = behaviour.getAction(this, map);
//		if (wander != null)
//			return wander;
//
//		return new DoNothingAction();
//	}
}