package game.ground;

import edu.monash.fit2099.engine.*;


import java.util.ArrayList;
import java.util.Random;

// need Fruit and HarvestAction

public class Tree extends Ground {
	private int age = 0;
	private Random random = new Random();
//	private Fruit fruit;
//	private ArrayList<Fruit> dropFruitsArray;

	public Tree() {
		super('+');
//		fruit = new Fruit();
//		dropFruitsArray = new ArrayList<>();
	}

	@Override
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';

//		boolean hasDroppedFruit = false;
//		for (Item item: location.getItems()){
////			if(dropFruitsArray.contains(item)){
//				hasDroppedFruit = true;
//			}
//		}
//		if(!hasDroppedFruit && random.nextDouble() <= 0.05){
//			Fruit dropFruit = new Fruit();
//			location.addItem(dropFruit);
//			dropFruitsArray.add(dropFruit);
		}

//		public Actions allowableActions(Actor actor, Location location, String direction){
//			return new Actions();
//		}
	}

