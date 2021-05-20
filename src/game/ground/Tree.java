package game.ground;

import edu.monash.fit2099.engine.*;
import game.actions.HarvestAction;
import game.actors.Dinosaur;
import game.actors.Player;
import game.enums.Points;
import game.items.Fruit;


import java.util.ArrayList;
import java.util.Random;

/**
 * A class that represents Tree.
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 3.0
 * @see edu.monash.fit2099.engine.Ground
 */
public class Tree extends Ground {
	/**
	 * Age of tree
	 */
	private int age = 0;
	/**
	 * Random number generator
	 */
	private Random random = new Random();
	/**
	 * ArrayList of tree fruit
	 */
	private ArrayList<Fruit> treeFruit;
	/**
	 * ArrayList of drop fruits
	 */
	private ArrayList<Fruit> dropFruitsArray;
	/**
	 * ArrayList of dinosaurs on tree
	 */
	private ArrayList<Dinosaur> dinosaurs;

	/**
	 * Constructor.
	 */
	public Tree() {
		super('+');
		treeFruit = new ArrayList<>();
		dropFruitsArray = new ArrayList<>();
		dinosaurs = new ArrayList<>();
	}

	/**
	 * Called once per turn, so that maps can experience the passage of time.
	 * @param location The location of the Ground
	 * @see Location
	 * @see Item
	 * @see Fruit
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';

		// scan if there's drop fruits at current location
		boolean hasDroppedFruit = false;
		for (Item item : location.getItems()) {
			if (dropFruitsArray.contains(item)) {
				hasDroppedFruit = true;
			}
		}
		// if no drop fruits and has tree fruit and has 5% chance
		if (!hasDroppedFruit && random.nextDouble() <= 0.05 && treeFruit.size() > 0) {
			Fruit dropFruit = treeFruit.remove(0);
			location.addItem(dropFruit);
			dropFruit.setOnGround(true); //fruit on ground
			dropFruitsArray.add(dropFruit);
		}
		// tree has a 50% chance to produce one ripe fruit
		if(random.nextDouble() <= 0.5){
			treeFruit.add(new Fruit());
			Player.wallet.addEcoPoints(Points.RIPE_FRUIT_PRODUCED.getPoints());
		}
	}

	/**
	 * Returns a collection of the Actions that the otherActor can do to the current Actor.
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return A collection of Actions.
	 * @see Actor
	 * @see Location
	 * @see Tree
	 * @see HarvestAction
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		Actions actions = new Actions();
		if(location.getGround() instanceof Tree){
			if(gotFruit()){
				actions.add(new HarvestAction(treeFruit.get(0), this));
			}
		}
		return actions;
	}

	/**
	 * Determine whether the tree contains fruit
	 * @return a boolean, if true tree got fruit, else no fruit
	 */
	public boolean gotFruit(){
		return treeFruit.size() > 0;
	}

	/**
	 * Get array list of tree fruit that contains all fruits on tree
	 * @return An ArrayList of treeFruit that contains all fruits on tree
	 */
	public ArrayList<Fruit> getTreeFruit() {
		return treeFruit;
	}

	/**
	 * Determine whether it has dinosaur on tree
	 * @return a boolean, if true means there's a dinosaur on tree, false there is no dinosaur on tree
	 */
	public boolean hasDinosaur(){return dinosaurs.size() == 1;}

	/**
	 * Remove the dinosaur on tree
	 */
	public void removeDinosaur(){dinosaurs.remove(0);}

	/**
	 * Add the dinosaur on tree
	 * @param dino the dinosaur that is on tree
	 * @see Dinosaur
	 */
	public void addDinosaur(Dinosaur dino){dinosaurs.add(dino);}
}
