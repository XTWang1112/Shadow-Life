import bagel.Font;

/** Inherited from Actor, represents a hoard
 * @author xiaotongwang
 */
public class Hoard extends Actor{
    protected static final String TYPE = "Hoard";
    private int numOfFruits = 0;
    private static final Font showFruit = new Font("res/VeraMono.ttf", 20);

    /** Constructor of hoard
     * @param x x coordinate
     * @param y y coordinate
     */
    public Hoard(int x, int y) {
        super("res/images/hoard.png", TYPE, x, y);
    }

    /** Getter of numOfFruits
     * @return number of fruits that the Hoard is currently holding
     */
    public int getNumOfFruits() {
        return numOfFruits;
    }

    /** Setter of numOfFruits
     * @param numOfFruits number of fruits that the hoard will holding
     */
    public void setNumOfFruits(int numOfFruits) {
        this.numOfFruits = numOfFruits;
    }

    /** Draw hoard and display number of fruits it contains
     */
    @Override
    public void render() {
        image.drawFromTopLeft(this.getxCoord(), this.getyCoord());
        showFruit.drawString(Integer.toString(numOfFruits), this.getxCoord(), this.getyCoord());

    }

    /** Update state of hoard
     */
    @Override
    public void update() {}
}
