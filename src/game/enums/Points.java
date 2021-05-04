package game.enums;

public enum Points {
    RIPE_FRUIT_PRODUCED(1),
    RIPE_FRUIT_HARVESTED(10),
    FRUIT_FED(10),
    STEGOSAUR_HATCHED(100),
    BRACHIOSAUR_HATCHED(1000),
    ALLOSAUR_HATCHED(1000);

    public final int points;

    Points(int points){
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }
}
