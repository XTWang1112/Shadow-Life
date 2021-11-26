/** Inherited from Actor, represents a pad
 * @author xiaotongwang
 */
public class Pad extends Actor {
    protected static final String TYPE = "Pad";

    /** Constructor of pad
     * @param xCoord x coordinate
     * @param yCoord y coordinate
     */
    public Pad(int xCoord, int yCoord){
        super("res/images/pad.png", TYPE, xCoord, yCoord);
    }

    /** Update state of pad
     */
    @Override
    public void update() {}
}
