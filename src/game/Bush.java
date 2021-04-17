package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class Bush extends Ground{
    private int age = 0;
    //fruits?

    public Bush() {
        super('+');
    }

    @Override
    public void tick(Location location) {
        super.tick(location);

        age++;
        if (age == 10)
            displayChar = 't';
        if (age == 20)
            displayChar = 'T';
    }
}
