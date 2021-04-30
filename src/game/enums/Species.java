package game.enums;

public enum Species {
    A("ALLOSAUR"),
    B("BRACHIOSAUR"),
    S("STEGOSAUR");

    public final String label;

    Species(String name){
        this.label = name;
    }

    public String getLabel() {
        return this.label;
    }
}
