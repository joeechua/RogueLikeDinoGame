package game;


import edu.monash.fit2099.engine.*;

public class MateAction extends Action{

    protected Actor target;
    public MateAction(Actor target){this.target = target;}

    @Override
    public String execute(Actor actor, GameMap map) {

        Weapon weapon = actor.getWeapon();


        int damage = weapon.damage();
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

        //stuff about breeding, hatch within x days, success etc

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target;
    }
}
