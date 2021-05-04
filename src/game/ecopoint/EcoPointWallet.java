package game.ecopoint;

/***
 * Class that represents a player's wallet. Restricts any impossible transactions and can
 * be extended to keep track of all transactions
 * @see game.actors.Player
 */
public class EcoPointWallet {
    private int ecoPoints;

    /***
     * Constructor
     * @param ecoPoints number of eco points in the wallet when initialized
     */
    public EcoPointWallet(int ecoPoints){
        this.ecoPoints = ecoPoints;
    }

    /***
     * Allows outside classes to set the number of points the wallet has
     * @param points number of points to be in the wallet
     */
    public void setEcoPoints(int points){
        this.ecoPoints = points;
    }

    /***
     * Allows outside classes to get the number of points in the wallet
     * @return points in the wallet
     */
    public int getEcoPoints(){
        return this.ecoPoints;
    }

    /***
     * Allows outside classes to add eco points to the wallet
     * @param points number of points to be added to wallet
     */
    public void addEcoPoints(int points){
        int newPoints = this.ecoPoints + points;
        setEcoPoints(newPoints);
    }

    /***
     * Allows outside classes to take eco points away from the wallet
     * @param points points to be taken away from the wallet
     */
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
