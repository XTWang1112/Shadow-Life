import bagel.Font;

/** Inherited from Actor, represents a tree
 * @author xiaotongwang
 */
public class Tree extends Actor {
    protected static final String TYPE = "Tree";
    private int numOfFruits = 3;
    private static final Font showFruit = new Font("res/VeraMono.ttf", 20);

    /** Constructor of tree
     * @param xCoord x coordinate
     * @param yCoord y coordinate
     */
    public Tree(int xCoord, int yCoord){
        super("res/images/tree.png", TYPE, xCoord, yCoord);
    }

    /** Getter of numOfFruits
     * @return number of fruits that the tree is holding
     */
    public int getNumOfFruits() {
        return numOfFruits;
    }

    /** Setter of numOfFruits
     * @param numOfFruits number of fruits that the tree will hold
     */
    public void setNumOfFruits(int numOfFruits) {
        this.numOfFruits = numOfFruits;
    }

    /** Draw tree and display number of fruits the tree has
     */
    @Override
    public void render() {
        image.drawFromTopLeft(this.getxCoord(), this.getyCoord());
        showFruit.drawString(Integer.toString(numOfFruits), this.getxCoord(), this.getyCoord());

    }

    /** Update the state of the tree
     */
    @Override
    public void update() {}
}