package game;


import edu.monash.fit2099.engine.*;

public class FeedingAction extends Action {

    protected Actor target;

    public FeedingAction(Actor subject){this.target = subject;}

    @Override
    public String execute(Actor actor, GameMap map) {

        Weapon weapon = actor.getWeapon();


        int damage = weapon.damage();
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

        //stuff about eating (decrease hunger etc)


        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats " + target;
    }
}
