package game.ground;

import edu.monash.fit2099.engine.*;
import game.actions.EatingAction;
import game.actors.Player;
import game.enums.VendingItems;
import game.items.Fruit;


import java.util.ArrayList;
import java.util.Random;

public class Tree extends Ground {
	private int age = 0;
	private Random random = new Random();
	private ArrayList<Fruit> treeFruit;
	private ArrayList<Fruit> dropFruitsArray;

	public Tree() {
		super('+');
		treeFruit = new ArrayList<>();
		dropFruitsArray = new ArrayList<>();
	}

	@Override
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';

		boolean hasDroppedFruit = false;
		for (Item item : location.getItems()) {
			if (dropFruitsArray.contains(item)) {
				hasDroppedFruit = true;
			}
		}
		if (!hasDroppedFruit && random.nextDouble() <= 0.05 && treeFruit.size() > 0) {
			/*
			i think instead of making a new fruit, can take treeFruit.get(0)
			i changed the fruit there so we have an attribute onTree so it wont rot in tree
			 */
			//Fruit dropFruit = new Fruit();
			Fruit dropFruit = treeFruit.remove(0);
			location.addItem(dropFruit);
			dropFruit.setOnTree(false); //fruit on ground
			dropFruitsArray.add(dropFruit);
			Player.wallet.addEcoPoints(1);

		}
		if(treeFruit.size() == 0 && random.nextDouble() <= 0.5){
			//changed this from treeFruit == null because it will never be nnull since u initiated edi
			treeFruit.add(new Fruit());
		}
	}

	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		Actions actions = new Actions();
		for(Fruit item: treeFruit){
			actions.add(item.getHarvestAction());
		}
		return actions;
	}

	public boolean gotFruit(){
		return treeFruit == null;
	}
}
