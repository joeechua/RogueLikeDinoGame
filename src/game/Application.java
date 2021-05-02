package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.actors.Brachiosaur;
import game.actors.Player;
import game.actors.Stegosaur;
import game.ecopoint.EcoPointWallet;
import game.enums.Gender;
import game.ground.*;
import game.items.Fruit;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new Bush(), new VendingMachine());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		".....#######....................................................................",
		".....#_____#....................................................................",
		".....#_____#....................................................................",
		".....###.###....................................................................",
		"................................................................................",
		"......................................+++.......................................",
		".......................................++++.....................................",
		"...................................+++++........................................",
		".....................................++++++.....................................",
		"......................................+++.......................................",
		".....................................+++........................................",
		"................................................................................",
		"............+++.................................................................",
		".............+++++..............................................................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);
		
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(9, 4));

		gameMap.at(5,7).setGround(groundFactory.newGround('⌻'));
		//gameMap.at(35,12).addItem(new Fruit());

		// Place a pair of stegosaurs in the middle of the map
		gameMap.at(30, 12).addActor(new Stegosaur(Gender.F));
		gameMap.at(32, 12).addActor(new Stegosaur(Gender.M));
		gameMap.at(10,12).addActor(new Brachiosaur(Gender.M));
		gameMap.at(52,12).addActor(new Brachiosaur(Gender.M));
		gameMap.at(40,14).addActor(new Brachiosaur(Gender.F));
		gameMap.at(40,13).addActor(new Brachiosaur(Gender.F));
		world.run();
	}
}
