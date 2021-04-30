package game.enums;

public enum Gender {
    M("MALE"),
    F("FEMALE");

    public final String label;

    private Gender(String label){
        this.label = label;
    }
}
