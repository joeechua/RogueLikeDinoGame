package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.*;
import game.actors.*;
import game.items.Corpse;

/**
 * Special Action for attacking other Actors.
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 3.0
 * @see edu.monash.fit2099.engine.Action
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 * @see Actor
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	/**
	 * Perform the Attack action.
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 * @see Actor
	 * @see Actions
	 * @see Weapon
	 * @see Dinosaur
	 * @see Corpse
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		// there's a chance where it misses target
		if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}

		// get weapon damage
		int damage = weapon.damage();
		String result = "";

		Dinosaur dinoTarget = (Dinosaur) target;
		Dinosaur actorDino;
		// if actor is dinosaur(Allosaurs)
		if(dinoTarget.getAttackTurns() == 0 && actor instanceof Dinosaur){
			actorDino = (Dinosaur) actor;
			// increase and decrease hit points with damage for actor and target respectively
			dinoTarget.hurt(damage);
			actor.heal(damage);
			// attacked dinosaurs food level decrease by 20
			dinoTarget.decFoodLevel(20);
			// baby dino food level increase by 10
			if(actor instanceof BabyAllosaur){
				actorDino.incFoodLevel(10);
			}
			// adult dino food level increase by 10
			else{
				actorDino.incFoodLevel(20);
			}
			// set attackTurns 20
			dinoTarget.setAttackTurns(20);
			result = actor + " " + weapon.verb() + " " + dinoTarget + " for " + damage + " damage.";
			// if target is unconscious - die
			if (!dinoTarget.isConscious()) {
				result += System.lineSeparator() + new DieAction().execute(dinoTarget, map) + " and killed.";

				Actions dropActions = new Actions();
				for (Item item : dinoTarget.getInventory())
					dropActions.add(item.getDropAction());
				for (Action drop : dropActions)
					drop.execute(dinoTarget, map);
				map.removeActor(dinoTarget);
			}
		}
		// if actor is the Player
		else if(actor instanceof Player){
			// decrease dinoTarget hit point
			dinoTarget.hurt(damage);
			// if dinoTarget is unconscious - die
			if(!dinoTarget.isConscious()){
				result += System.lineSeparator() + new DieAction().execute(target, map) + " and killed.";

				Actions dropActions = new Actions();
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction());
				for (Action drop : dropActions)
					drop.execute(target, map);
				map.removeActor(target);

			}
		}
		// when attackTurns > 0
		else {
			result = actor + " cannot attack " + target + " due to the remaining attack turns: " + dinoTarget.getAttackTurns() ;
		}
		return result;
	}

	/**
	 * Returns a descriptive string.
	 *
	 * @param actor The actor performing the action.
	 * @return a description of the action
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
