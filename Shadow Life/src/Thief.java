import bagel.Image;

/** Inherited from Gatherer, represents a thief
 * @author xiaotongwang
 */
public class Thief extends Gatherer{
    protected static final String TYPE = "Thief";
    private boolean consuming;

    /** Constructor of Thief, developed from Algorithm 3
     * @param x x coordinate
     * @param y y coordinate
     */
    public Thief(int x, int y){
        super(x, y);
        //Set direction to UP
        this.setDirection(0);
        consuming = false;
        image = new Image("res/images/thief.png");
    }

    /** Constructor of Thief, this is specifically for creating doppelganger
     * @param x x coordinate
     * @param y y coordinate
     * @param direction initial direction of the thief
     */
    public Thief(int x, int y, int direction){
        super(x, y, direction);
        consuming = false;
        image = new Image("res/images/thief.png");
    }

    @Override
    protected void onPool(){
        this.setActive(false);
        destroy = true;
        ShadowLife.doppelgangers.add(new Gatherer(this.getxCoord(), this.getyCoord(), (this.getDirection() + 1) % 4));
        ShadowLife.doppelgangers.add(new Gatherer(this.getxCoord(), this.getyCoord(), (this.getDirection() + 4 - 1) % 4));
    }

    @Override
    protected void onHoard(Hoard hoard){
        if(consuming){
            consuming = false;
            if(!this.isCarrying()){
                if(hoard.getNumOfFruits() >= 1){
                    this.setCarrying(true);
                    hoard.setNumOfFruits(hoard.getNumOfFruits() - 1);
                }else{
                    //rotate 90 degree clockwise
                    this.setDirection((this.getDirection() + 3) % 4);
                }
            }
        }else if(this.isCarrying()){
            this.setCarrying(false);
            hoard.setNumOfFruits(hoard.getNumOfFruits() + 1);
            //rotate 90 degree clockwise
            this.setDirection((this.getDirection() + 3) % 4);
        }
    }

    @Override
    protected void onPile(Stockpile pile){
        if(!this.isCarrying()) {
            if (pile.getNumOfFruits() >= 1) {
                this.setCarrying(true);
                consuming = false;
                pile.setNumOfFruits(pile.getNumOfFruits() - 1);
                //rotate 90 degree clockwise
                this.setDirection((this.getDirection() + 3) % 4);            }
        }else{
            //rotate 90 degree clockwise
            this.setDirection((this.getDirection() + 3) % 4);        }
    }

    @Override
    protected void onTree(Tree tree){
        if(tree.getNumOfFruits() >= 1){
            tree.setNumOfFruits(tree.getNumOfFruits() - 1);
            this.setCarrying(true);
        }
    }

    @Override
    protected void onGoldenTree(){
        this.setCarrying(true);
    }

    /** Update the state of thief
     */
    @Override
    public void update(){
        if(this.isActive()){
            move(this.getDirection(), ShadowLife.TILE_SIZE);
            for(Actor actor: ShadowLife.actors) {
                if (actor != this) {
                    if (this.getxCoord() == actor.getxCoord() && this.getyCoord() == actor.getyCoord()) {
                        switch (actor.type) {
                            case ("Fence"):
                                onFence();
                                break;
                            case ("MitosisPool"):
                                onPool();
                                break;
                            case ("Sign"):
                                onSign((Sign) actor);
                                break;
                            case ("Pad"):
                                consuming = true;
                                break;
                            case("Gatherer"):
                                //turn 270 degree clockwise
                                this.setDirection((this.getDirection() + 1) % 4);
                                break;
                            case ("Tree"):
                                if (!this.isCarrying()) {
                                    onTree((Tree) actor);
                                }
                                break;
                            case ("GoldenTree"):
                                if (!this.isCarrying()) {
                                    onGoldenTree();
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
}
