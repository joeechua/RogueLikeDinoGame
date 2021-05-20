package game.actors;

import edu.monash.fit2099.engine.*;
import game.Application;
import game.actions.QuitAction;
import game.ecopoint.EcoPointWallet;

/**
 * Class representing the Player.
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 3.0
 * @see edu.monash.fit2099.engine.Actor
 */
public class Player extends Actor {

	private Menu menu = new Menu();
	public static EcoPointWallet wallet;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 * @see EcoPointWallet
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		// set a wallet for player to purchase
		wallet = new EcoPointWallet(500);
	}

	/**
	 * Select and return an action to perform on the current turn.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if(Application.choice == 1 && Application.challengeRounds == 0){
			int option = 0;
			if(this.wallet.getEcoPoints() >= Application.challengePoints){
				option = 1;
			}
			return new QuitAction(option);
		}
		else if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		actions.add(new QuitAction(2));
		Application.challengeRounds--;
		return menu.showMenu(this, actions, display);
	}

}
