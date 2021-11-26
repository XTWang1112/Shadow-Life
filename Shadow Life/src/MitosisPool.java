/** Inherited from Actor, represents a Mitosis pool
 * @author xiaotongwang
 */
public class MitosisPool extends Actor {
    protected static final String TYPE = "Pool";

    /** Constructor of Mitosis Pool
     * @param xCoord x coordinate
     * @param yCoord y coordinate
     */
    public MitosisPool(int xCoord, int yCoord){
        super("res/images/pool.png", TYPE, xCoord, yCoord);
    }

    /** Update state of Mitosis Pool
     */
    @Override
    public void update() {}
}
