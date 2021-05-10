package game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.monash.fit2099.engine.*;
import game.actors.Brachiosaur;
import game.actors.Player;
import game.actors.Stegosaur;
import game.ecopoint.EcoPointWallet;
import game.enums.Gender;
import game.ground.*;
import game.items.Fruit;
import game.items.StegosaurEgg;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {
	public static int challengeRounds = 100;
	public static int challengePoints = 20000;
	public static int choice = 0;
	private static boolean endGame = false;

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new Bush(), new VendingMachine(), new Lake());
		
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

		FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new VendingMachine(), new Lake());

		List<String> map2 = Arrays.asList(
				"....................................................................+++........",
				".....................................~~~~......................................",
				".....~~~~~~~....................................................................",
				"...+++~~~~~+++++.....................................+++++........~~~~~~~.......",
				"..........................++++++~~~~~~~~~.......................................",
				".....~~~~~~~.............................................~~~~~~~................",
				"..................................................................+++...........",
				"......................................+++.......................................",
				".......................................++++.....................................",
				"........................~~~........+++++...........~~~~.........................",
				".....................................++++++.....................................",
				"......................................+++.......................................",
				".............~~~~~~~~~~..............+++........................................",
				"................................................................~~~~............",
				"............+++.................................................................",
				".............+++++...............~~~~~~~~~~.....................................",
				".~~~~~~~.......++........................................+++++..................",
				".............+++.~~~................................++++++++....................",
				"............+++.......................................+++.......................",
				"................................................................................",
				".........................................................................++.....",
				".........~~~~~..........................................................++.++...",
				".........................................................................++++...",
				"...................................~~~~~~.................................++....",
				"..................................................................~~~~~~........");
		GameMap gameMap2 = new GameMap(groundFactory2, map2);
		world.addGameMap(gameMap2);

		// link both maps
//		for(int x : gameMap2.getXRange()){
//			Location loc = gameMap2.at(x, gameMap.getYRange().min());
//			loc.addExit(new Exit("North", gameMap2.at(x - 1, gameMap.getYRange().max()), "8"));
//		}
//
		Actor player = new Player("Player", '@', 100);

		gameMap.at(5,7).setGround(groundFactory.newGround('⌻'));

		// Place a pair of stegosaurs in the middle of the map
		gameMap.at(30, 12).addActor(new Stegosaur(Gender.F));
		gameMap.at(32, 12).addActor(new Stegosaur(Gender.M));
		gameMap.at(50,12).addActor(new Brachiosaur(Gender.M));
		gameMap.at(52,12).addActor(new Brachiosaur(Gender.M));
		gameMap.at(40,14).addActor(new Brachiosaur(Gender.F));
		gameMap.at(42,14).addActor(new Brachiosaur(Gender.F));

		//print a menu
		do {
			boolean printMenu = true;
			while (printMenu) {
				System.out.println("Select a game mode:");
				Scanner scanner = new Scanner(System.in);
				System.out.println("1) Challenge Mode \n2) Sandbox Mode");
				String c = scanner.nextLine();
				try {
					choice = Integer.parseInt(c);
					printMenu = false;
				} catch (Exception e) {
					//do nothing
				}

				if (choice == 1) {
					System.out.printf("Enter points:");
					Scanner points = new Scanner(System.in);
					String p = points.nextLine();
					System.out.printf("Enter rounds:");
					Scanner rounds = new Scanner(System.in);
					String r = rounds.nextLine();
					try {
						challengeRounds = Integer.parseInt(r);
						challengePoints = Integer.parseInt(p);
					} catch (Exception e) {
						//do nothing
					}
				}
			}
			world.addPlayer(player, gameMap.at(9, 4));
			world.run();
			System.out.println("Do you want to play again?");
			System.out.printf("1) Yes\n2) No\n");
			Scanner end = new Scanner(System.in);
			String e = end.nextLine();
			int no = 0;
			try{
				no = Integer.parseInt(e);
			}catch(Exception m){};
			if(no == 2){
				endGame = true;
			}
		}
		while(!endGame);


	}
}
