package game.ground;

import edu.monash.fit2099.engine.*;
import game.items.Fruit;


import java.util.ArrayList;
import java.util.Random;

public class Tree extends Ground {
	private int age = 0;
	private Random random = new Random();
	private Fruit treeFruit;
	private ArrayList<Fruit> dropFruitsArray;

	public Tree() {
		super('+');
		treeFruit = new Fruit();
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
		if (!hasDroppedFruit && random.nextDouble() <= 0.05) {
			Fruit dropFruit = new Fruit();
			location.addItem(dropFruit);
			dropFruitsArray.add(dropFruit);
		}
		if(treeFruit == null && random.nextDouble() == 0.5){
			treeFruit = new Fruit();
		}
	}

	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		return new Actions(treeFruit.getHarvestAction());
	}
}
