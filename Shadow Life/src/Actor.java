import bagel.Image;

/** An abstract class that represent an actor
 * @author xiaotongwang
 */
public abstract class Actor {
    private int xCoord;
    private int yCoord;
    private int lastX;
    private int lastY;

    protected Image image;
    protected final String type;

    /** A constructor of Actor, this is created for creating a Sign
     * @param type type of the actor
     * @param xCoord x coordinate
     * @param yCoord y coordinate
     */
    public Actor(String type, int xCoord, int yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        image = null;
        this.type = type;
    }

    /** A constructor of Actor, refer to the sample answer
     * @param filename pathway of the image that is used to draw actors
     * @param type type of the actor
     * @param xCoord x coordinate
     * @param yCoord y coordinate
     */
    public Actor(String filename, String type, int xCoord, int yCoord) {
        image = new Image(filename);
        this.type = type;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    /** Getter of x coordinate
     * @return x coordinate
     */
    public int getxCoord() {
        return xCoord;
    }

    /** Setter of x coordinate
     * @param xCoord value that the x coordinate is changing to
     */
    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    /** Getter of y coordinate
     * @return y coordinate
     */
    public int getyCoord() {
        return yCoord;
    }

    /** Setter of y coordinate
     * @param yCoord value that the y coordinate is changing to
     */
    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    /** Getter of the x coordinate in the last tick
     * @return x coordinate in the last tick
     */
    public int getLastX() {
        return lastX;
    }

    /** Getter of the y coordinate in the last tick
     * @return y coordinate in the last tick
     */
    public int getLastY() {
        return lastY;
    }

    /** Make actor move
     * @param direction direction that the actor is moving in
     * @param pace number of pixel moved per tick
     */
    public void move(int direction, int pace) {
        lastX = xCoord;
        lastY = yCoord;
        switch(direction){
            case(0):
                yCoord -= pace;
                break;
            case(1):
                xCoord -= pace;
                break;
            case(2):
                yCoord += pace;
                break;
            case(3):
                xCoord += pace;
                break;
        }
    }

    /** Draw image of the actor
     */
    public void render(){
        image.drawFromTopLeft(xCoord, yCoord);
    }

    /** Actions that the actor is taking per tick
     */
    public final void tick(){
        update();
    }

    /** Update the state of the actor
     */
    public abstract void update();
}
