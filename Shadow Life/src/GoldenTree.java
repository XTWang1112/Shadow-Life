/** Inherited from Actor, represents a golden tree
 * @author xiaotongwang
 */
public class GoldenTree extends Actor {
    protected static final String TYPE = "GoldenTree";

    /** Constructor of golden tree
     * @param xCoord x coordinate
     * @param yCoord y coordinate
     */
    public GoldenTree(int xCoord, int yCoord){
        super("res/images/gold-tree.png", TYPE, xCoord, yCoord);
    }

    @Override
    public void update() {}
}
