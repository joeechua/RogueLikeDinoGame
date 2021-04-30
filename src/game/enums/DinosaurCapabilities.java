package game.enums;

public enum DinosaurCapabilities {
    //subject to changes
    FEMALE(new String[]{"Pregnancy", "Breed", "Hunger", "Wander", "Follow"}),
    MALE(new String[]{"Breed", "Hunger", "Wander", "Follow"}),
    HERBIVORE(new String[]{"Pregnancy", "Breed", "Hunger", "Wander", "Follow"}),
    CARNIVORE(new String[]{"Pregnancy", "Breed", "Hunger", "Wander", "Follow", "ATTACK"});

    String[] caps;
    private DinosaurCapabilities(String[] caps){
        this.caps = caps;
    }
}
