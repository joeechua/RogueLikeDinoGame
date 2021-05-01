package game.ecopoint;

public class EcoPointWallet {
    private int ecoPoints;

    public EcoPointWallet(int ecoPoints){
        this.ecoPoints = ecoPoints;
    }

    public void setEcoPoints(int points){
        this.ecoPoints = points;
    }

    public int getEcoPoints(){
        return this.ecoPoints;
    }

    public void addEcoPoints(int points){
        int newPoints = this.ecoPoints + points;
        setEcoPoints(newPoints);
    }

    public void payEcoPoints(int points){
        int newPoints = this.ecoPoints - points;
        if(newPoints < 0){
            System.out.println("Cannot no money");
        }
        else{
            setEcoPoints(newPoints);
        }
    }
}
