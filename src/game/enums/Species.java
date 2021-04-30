package game.enums;

public enum Species {
    A("ALLOSAUR"),
    B("BRACHIOSAUR"),
    S("STEGOSAUR");

    public final String label;

    private Species(String name){
        this.label = name;
    }
}
