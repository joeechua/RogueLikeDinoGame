package game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.monash.fit2099.engine.*;
import game.actors.*;
import game.enums.Gender;
import game.ground.*;

/**
 * The main class for the Jurassic World game.
 * º
 */
public class Application {
	public static int challengeRounds = 100;
	public static int challengePoints = 20000;
	public static int choice = 0;
	private static boolean endGame = false;

	public static void main(String[] args) {

		//print a menu
		do {
			boolean printMenu = true;
			while (printMenu) {
				System.out.println("Select a game mode:");
				Scanner scanner = new Scanner(System.in);
				System.out.println("1) Challenge Mode \n2) Sandbox Mode \n3) Exit Game");
				String c = scanner.nextLine();
				try {
					choice = Integer.parseInt(c);
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
						printMenu = false;
					} catch (Exception e) {
						//do nothing
					}
				}
				else if(choice == 2){
					printMenu = false;
				}
				else if(choice == 3){
					endGame = true;
					return;
				}
			}
			DinoWorld world = new DinoWorld(new Display());
			//World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new Bush(), new VendingMachine(), new Lake());

			List<String> map = Arrays.asList(
			"....................................................................~~~~~~~~~~~.",
			".......................................................................~~~~~~~~~",
			".....#######..............................................................~~~~~~",
			".....#_____#..............~~~~~~................................................",
			".....#_____#..............~~~~~~................................................",
			".....###.###................~~~~................................................",
			"................................................................................",
			"......................................+++.......................................",
			".......................................++++.....................................",
			"...................................+++++........................................",
			".....................................++++++~~~~~................................",
			"......................................+++.~~~~~~................................",
			"....~~~~.....~~~~....................+++~~~~~~~~................................",
			"....~~~~~~~~~~~~~...............................................................",
			"....~~~~~~~~+++.................................................................",
			".............+++++..............................................................",
			"...............++........................................+++++..................",
			".............+++....................................++++++++....................",
			"............+++.......................................+++~~~~~~~~~~.............",
			"..........................................................~~~~~~~~~.............",
			"~~~~~~~~~............................~~~...................~~~~~~~~......++.....",
			"~~~~~~~~~...........................~~~~~~..................~~~~~~~.....++.++...",
			".....~~~~~~~~~........................~~.~...............................++++...",
			".~~~~~~~~~~............................~~~................................++....",
			"................................................................................");
			DinoGameMap gameMap = new DinoGameMap(groundFactory, map);
			//GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(), new Tree(), new Lake());

			List<String> map2 = Arrays.asList(
			"....................................................................~~~~~~~~~~~.",
			".........................................................................~~~~~~~",
			"....~~~~~~~~................................+++++++++.......................~~~~",
			"...~~~~~~~...................++++++++...........................................",
			"...~~~~~~.....................................................+++++++...........",
			"..~~~~~~++++........++++++.........~~~~~~~~.....................................",
			".................................~~~~~~~~~~~~...................................",
			".................................~~~~~+++~~~~..........................++++++++.",
			".......................................++++.....................................",
			"...................................+++++........................................",
			"..++++++++...........................++++++.....................................",
			"......................................+++~~~....................................",
			"~~~~~................................+++~~~~.................~~~~~..............",
			"~~~.......................................................~~~~~~~~..............",
			"~~..........+++.................................................................",
			"~............+++++~~~~~~~.......................................................",
			"...............++~~~~~...............+....+..~~~~~~~.....+++++..................",
			".............+++.............................~~~~~~~++++++++....................",
			"......~~~~~~+++..................................~~~~~+++.......................",
			"........~~~~....................................................................",
			"..................~~~~~~~~~~..................................~~~~~~~~~~~++.....",
			"................................................................~~~~~~~~++.++...",
			"....................................................................~~~~~++++...",
			".........~~~~~~~~~.......................~~~~~~~...~~~~~..................++....",
			"............~~~~~~.....................~~~~~~~~~~~~~~~~~~.......................");
			DinoGameMap gameMap2 = new DinoGameMap(groundFactory2, map2);
//			GameMap gameMap2 = new GameMap(groundFactory2, map2);
			world.addGameMap(gameMap2);

			// link both maps
			linkMapVertically(gameMap, gameMap2);

			Actor player = new Player("Player", '@', 100);

			gameMap.at(5,7).setGround(groundFactory.newGround('⌻'));

			// Place a pair of stegosaurs in the middle of the map
			gameMap.at(30, 12).addActor(new Stegosaur(Gender.F));
			gameMap.at(32, 12).addActor(new Stegosaur(Gender.M));
			gameMap.at(48, 11).addActor(new Stegosaur(Gender.M));
			gameMap.at(49,12).addActor(new Brachiosaur(Gender.M));
			gameMap.at(52,12).addActor(new Brachiosaur(Gender.M));
			gameMap.at(40,14).addActor(new Brachiosaur(Gender.F));
			gameMap.at(40,22).addActor(new Brachiosaur(Gender.F));
			gameMap.at(41, 14).addActor(new Pterodactyl(Gender.M));
//			Pterodactyl ptero = new Pterodactyl(Gender.F);
//			ptero.setFlying(false);
//			gameMap.at(40, 22).addActor(ptero);
			gameMap.at(42,23).addActor(new Allosaur(Gender.M));
			gameMap.at(41, 1).addActor(new Pterodactyl(Gender.M));

			world.addPlayer(player, gameMap.at(9, 4));
			System.out.println("world class: " + world.getClass());
			world.run();
		}
		while(!endGame);

	}

	/**
	 * Link 2 maps vertically by adding exits to the borders of map
	 *
	 * @param belowMap map that are supposed to be place below
	 * @param aboveMap map that are supposed to be place above
	 */
	public static void linkMapVertically(DinoGameMap belowMap, DinoGameMap aboveMap){
		int topOfMap =  belowMap.getYRange().min();
		int leftOfMap = belowMap.getXRange().min();
		int bottomOfMap = belowMap.getYRange().max();
		int rightOfMap = belowMap.getXRange().max();

		for(int x : aboveMap.getXRange()){
			Location loc = aboveMap.at(x, bottomOfMap);
			loc.addExit(new Exit("South", belowMap.at(x, topOfMap), "2"));
			if(x < rightOfMap){
				loc.addExit(new Exit("South-East", belowMap.at(x + 1, topOfMap), "3"));
			}
			if(x > leftOfMap){
				loc.addExit(new Exit("South-West", belowMap.at(x - 1, topOfMap), "1"));
			}
		}

		for(int x: belowMap.getXRange()){
			Location loc = belowMap.at(x, topOfMap);
			loc.addExit(new Exit("North", aboveMap.at(x, bottomOfMap), "8"));
			if(x < rightOfMap){
				loc.addExit(new Exit("North-East", aboveMap.at(x + 1, bottomOfMap), "9"));
			}
			if(x > leftOfMap){
				loc.addExit(new Exit("North-West", aboveMap.at(x - 1, bottomOfMap), "7"));
			}
		}
	}
}
