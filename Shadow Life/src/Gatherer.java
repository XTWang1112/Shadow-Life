/** Inherited from Actor, represents a gatherer
 * @author xiaotongwang
 */
public class Gatherer extends Actor{
    protected static final String TYPE = "Gatherer";

    private boolean carrying;
    private boolean active;
    private int direction;
    protected boolean destroy = false;

    /** Constructor of gatherer, developed from Algorithm 1
     * @param x x coordinate
     * @param y y coordinate
     */
    public Gatherer(int x, int y){
        super("res/images/gatherer.png", TYPE, x, y);
        //set direction to left
        direction = 1;
        carrying = false;
        active = true;
    }

    /** Constructor of gatherer, this is specifically for creating a doppelganger
     * @param x x coordinate
     * @param y y coordinate
     * @param direction initial direction of the gatherer
     */
    public Gatherer(int x, int y, int direction){
        super( "res/images/gatherer.png", TYPE, x, y);
        this.direction = direction;
        carrying = false;
        active = true;
    }

    /** Getter of carrying
     * @return if the gatherer can carry
     */
    public boolean isCarrying() {
        return carrying;
    }

    /** Setter of carrying
     * @param carrying the value that carrying is changing to
     */
    public void setCarrying(boolean carrying) {
        this.carrying = carrying;
    }

    /** Getter of active
     * @return if the gatherer is active
     */
    public boolean isActive() {
        return active;
    }

    /** Setter of active
     * @param active the value that active is changing to
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /** Getter of direction
     * @return current direction of the gatherer
     */
    public int getDirection() {
        return direction;
    }

    /** Setter of direction
     * @param direction value that the direction is changing to
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    protected void onPool(){
        active = false;
        destroy = true;
        ShadowLife.doppelgangers.add(new Gatherer(this.getxCoord(), this.getyCoord(), (this.direction + 1) % 4));
        ShadowLife.doppelgangers.add(new Gatherer(this.getxCoord(), this.getyCoord(), (this.direction + 4 - 1) % 4));
    }

    protected void onGoldenTree(){
        this.carrying = true;
        //rotate 180 degrees
        this.direction = (this.direction + 2) % 4;
    }

    protected void onSign(Sign sign){
        this.direction = sign.getDIRECTION();
    }

    protected void onTree(Tree tree){
        if(tree.getNumOfFruits() >= 1){
            tree.setNumOfFruits(tree.getNumOfFruits() - 1);
            this.carrying = true;
            //rotate 180 degrees
            this.direction = (this.direction + 2) % 4;
        }
    }

    protected void onHoard(Hoard hoard){
        if(carrying){
            carrying = false;
            hoard.setNumOfFruits(hoard.getNumOfFruits() + 1);
        }
        //rotate 180 degrees
        direction = (this.direction + 2) % 4;
    }

    protected void onPile(Stockpile pile){
        if(carrying){
            carrying = false;
            pile.setNumOfFruits(pile.getNumOfFruits() + 1);
        }
        //rotate 180 degrees
        direction = (this.direction + 2) % 4;
    }

    protected void onFence(){
        active = false;
        this.setxCoord(this.getLastX());
        this.setyCoord(this.getLastY());
    }

    /** Update the state of gatherer
     */
    @Override
    public void update() {
        if (active) {
            move(direction, ShadowLife.TILE_SIZE);
            for (Actor actor : ShadowLife.actors) {
                if (this.getxCoord() == actor.getxCoord() && this.getyCoord() == actor.getyCoord()) {
                    switch (actor.type) {
                        case ("Fence"):
                            onFence();
                            break;
                        case ("Pool"):
                            onPool();
                            break;
                        case ("Sign"):
                            onSign((Sign) actor);
                            break;
                        case("GoldenTree"):
                            onGoldenTree();
                            break;
                        case ("Tree"):
                            if (!carrying) {
                                onTree((Tree) actor);
                            }
                            break;
                        case ("Hoard"):
                            onHoard((Hoard) actor);
                            break;
                        case ("Stockpile"):
                            onPile((Stockpile) actor);
                            break;
                    }
                }
            }
        }
    }
}
