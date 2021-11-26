import bagel.Image;

/** Inherited from Actor, represents a sign
 * @author xiaotongwang
 */
public class Sign extends Actor {
    protected static final String TYPE = "Sign";
    //0:up 1:left 2:down 3:right
    private final int DIRECTION;

    /** Constructor of Sign
     * @param xCoord x coordinate
     * @param yCoord y coordinate
     * @param direction direction of the sign
     */
    public Sign(int xCoord, int yCoord, int direction){
        super(TYPE, xCoord, yCoord);
        DIRECTION = direction;
        switch(direction){
            case(0):
                image = new Image("res/images/up.png");
                break;
            case(1):
                image = new Image("res/images/left.png");
                break;
            case(2):
                image = new Image("res/images/down.png");
                break;
            case(3):
                image = new Image("res/images/right.png");
                break;
        }
    }

    /** Getter of direction
     * @return direction of the sign
     */
    public int getDIRECTION() {
        return DIRECTION;
    }

    /** Update the state of the sign
     */
    @Override
    public void update() {}
}
