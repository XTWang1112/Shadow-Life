import bagel.Font;

/**Inherited from Actor, represents a stockpile
 * @author xiaotongwang
 */
public class Stockpile extends Actor {
    protected static final String TYPE = "Stockpile";
    private int numOfFruits = 0;
    private static final Font showFruit = new Font("res/VeraMono.ttf", 20);

    /** Constructor of stockpile
     * @param xCoord x coordinate
     * @param yCoord y coordinate
     */
    public Stockpile(int xCoord, int yCoord){
        super("res/images/cherries.png", TYPE, xCoord, yCoord);
    }

    /** Getter of numOfFruits
     * @return number of fruits that the stockpile is holding
     */
    public int getNumOfFruits() {
        return numOfFruits;
    }

    /** Setter of numOfFruits
     * @param numOfFruits number of fruits that the stockpile will hold
     */
    public void setNumOfFruits(int numOfFruits) {
        this.numOfFruits = numOfFruits;
    }

    /** Draw stockpile and display the number of fruits it holding
     */
    @Override
    public void render() {
        image.drawFromTopLeft(this.getxCoord(), this.getyCoord());
        showFruit.drawString(Integer.toString(numOfFruits), this.getxCoord(), this.getyCoord());

    }

    /** Update the state of the stockpile
     */
    @Override
    public void update() {}
}
