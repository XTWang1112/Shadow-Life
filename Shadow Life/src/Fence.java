/** Inherited from Actor, represents a fence
 * @author xiaotongwang
 */
public class Fence extends Actor {
    protected static final String TYPE = "Fence";


    /** Constructor of fence
     * @param xCoord x coordinate
     * @param yCoord y coordinate
     */
    public Fence(int xCoord, int yCoord){
        super("res/images/fence.png", TYPE, xCoord, yCoord);
    }

    /** Update the state of fence
     */
    @Override
    public void update() {}
}
