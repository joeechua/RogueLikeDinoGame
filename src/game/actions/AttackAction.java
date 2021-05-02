package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.actors.*;
import game.items.Corpse;
import game.items.PortableItem;

/**
 * Special Action for attacking other Actors.
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
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = "";

		// decrease and increase food level for Stegosaur and Allosaurs respectively by 20
		Dinosaur dinoTarget = (Dinosaur) target;
		Dinosaur actorDino;
		if(dinoTarget.getAttackTurns() == 0 && actor instanceof Dinosaur){
			target.hurt(damage);
			actor.heal(damage);
			dinoTarget.decFoodLevel(20);
			if(actor instanceof BabyAllosaur){
				actorDino = (Dinosaur) actor;
				actorDino.incFoodLevel(10);
			}
			else if(actor instanceof Dinosaur){
				actorDino = (Dinosaur) actor;
				actorDino.incFoodLevel(20);
			}
			dinoTarget.setAttackTurns(20);
			result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
			if (!target.isConscious()) {
				Item corpse1 = new PortableItem("dead " + target, '%');
				Corpse corpse = new Corpse(dinoTarget);
				map.locationOf(target).addItem(corpse);

				Actions dropActions = new Actions();
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction());
				for (Action drop : dropActions)
					drop.execute(target, map);
				map.removeActor(target);

				result += System.lineSeparator() + target + " is killed.";
			}
		}
		else if(actor instanceof Player){
			dinoTarget.hurt(damage);
			if(!dinoTarget.isConscious()){
				Corpse corpse = new Corpse(dinoTarget);
				map.locationOf(dinoTarget).addItem(corpse);
				Actions dropActions = new Actions();
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction());
				for (Action drop : dropActions)
					drop.execute(target, map);
				map.removeActor(target);

				result += System.lineSeparator() + target + " is killed.";
			}
		}
		else {
			result = actor + " cannot attack " + target + " due to the remaining attack turns: " + dinoTarget.getAttackTurns() ;
		}
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
