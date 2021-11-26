import bagel.AbstractGame;
import bagel.Image;
import bagel.Input;
import bagel.Window;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/** Class that contains the main, include every element of the Universe
 * @author xiaotongwang
 */
public class ShadowLife extends AbstractGame {
    protected static final int TILE_SIZE = 64;
    private final int tickRate;
    private final int maxNumTick;
    private int numOfTicks = 0;
    protected static ArrayList<Actor> actors = new ArrayList<>();
    private long lastTick = 0;
    private final Image background = new Image("res/images/background.png");
    private final String[] actorType = {"Fence", "Gatherer", "GoldenTree", "Hoard", "Pool", "Pad", "SignUp",
            "SignDown", "SignLeft", "SignRight", "Stockpile", "Thief", "Tree"};
    private int lineNumber = 1;
    protected static ArrayList<Gatherer> doppelgangers = new ArrayList<>();

    /** Constructor of ShadowLife
     * @param strings path of the csv file that the game is loading actors from
     * @throws InvalidInputException checking whether the argument input is valid
     */
    public ShadowLife(String[] strings) throws InvalidInputException{
        if(strings.length != 3 || Integer.parseInt(strings[0]) < 0 || Integer.parseInt(strings[1]) < 0){
            throw new InvalidInputException();
        }
        tickRate = Integer.parseInt(strings[0]);
        maxNumTick = Integer.parseInt(strings[1]);
        try {
            loadActors(strings[2]);
        }catch(InvalidCSV e){
            System.out.println("error: in file \"" + strings[2] + "\" at line " + lineNumber);
            System.exit(-1);
        }
    }

    private static String[] argsFromFile() {
        try {
            return Files.readString(Path.of("res/args.txt"), Charset.defaultCharset()) .split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //based on code of the sample answer
    protected void loadActors(String fileName) throws InvalidCSV {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                // Line format is: type,x,y
                if(validLine(line)){
                    lineNumber++;
                    String[] parts = line.split(",");
                    String type = parts[0];
                    int x = Integer.parseInt(parts[1]);
                    int y = Integer.parseInt(parts[2]);
                    switch (type) {
                        case Tree.TYPE:
                            actors.add(new Tree(x, y));
                            break;
                        case Fence.TYPE:
                            actors.add( new Fence(x, y));
                            break;
                        case Gatherer.TYPE:
                            actors.add(new Gatherer(x, y));
                            break;
                        case Thief.TYPE:
                            actors.add(new Thief(x, y));
                            break;
                        case Stockpile.TYPE:
                            actors.add(new Stockpile(x, y));
                            break;
                        case MitosisPool.TYPE:
                            actors.add(new MitosisPool(x, y));
                            break;
                        case Hoard.TYPE:
                            actors.add(new Hoard(x, y));
                            break;
                        case GoldenTree.TYPE:
                            actors.add(new GoldenTree(x, y));
                            break;
                        case Pad.TYPE:
                            actors.add(new Pad(x, y));
                            break;
                        case "SignUp":
                            actors.add(new Sign(x, y, 0));
                            break;
                        case "SignLeft":
                            actors.add(new Sign(x, y, 1));
                            break;
                        case "SignDown":
                            actors.add(new Sign(x, y, 2));
                            break;
                        case "SignRight":
                            actors.add(new Sign(x, y, 3));
                            break;
                    }
                }else{
                    throw new InvalidCSV();
                }
            }
        } catch (IOException e) {
            System.out.println("error: file \"" + fileName + "\" not found");
            System.exit(-1);
        }
    }

    private boolean validLine(String string){
        String[] parts = string.split(",");
        boolean ifContainsTwoCommas = parts.length == 3;
        boolean ifContainsTwoInteger = isInteger(parts[1]) && isInteger(parts[2]);
        boolean ifValidType = Arrays.asList(actorType).contains(parts[0]);
        return (ifContainsTwoCommas && ifContainsTwoInteger && ifValidType);
    }

    private boolean isInteger(String string) {
        try {
            Integer.valueOf(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean haltProgramme(){
        for(Actor actor : actors){
            if(actor instanceof Gatherer){
                if(((Gatherer) actor).isActive()){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void update(Input input) {
        // Draw all elements
        background.drawFromTopLeft(0, 0);
        for (Actor actor : actors) {
            if (actor != null) {
                actor.render();
            }
        }

        if (haltProgramme()) {
            System.out.println(numOfTicks + " ticks");
            for (Actor actor : actors) {
                if (actor instanceof Stockpile) {
                    System.out.println(((Stockpile) actor).getNumOfFruits());
                }
                if (actor instanceof Hoard) {
                    System.out.println(((Hoard) actor).getNumOfFruits());
                }
            }
            Window.close();
        }

        // If enough time has passed, run the next tick
        if (System.currentTimeMillis() - lastTick > tickRate) {
            lastTick = System.currentTimeMillis();
            for(Actor a: actors){
                if (a != null) {
                    a.tick();
                }
            }
            numOfTicks++;
        }
        for(Gatherer gatherer: doppelgangers){
            if(gatherer != null) {
                actors.add(gatherer);
            }
        }
        doppelgangers.clear();

        Iterator<Actor> itr = actors.iterator();
        while(itr.hasNext()){
            Actor a = itr.next();
            if( a instanceof Gatherer){
                if(((Gatherer) a).destroy){
                    itr.remove();
                }
            }
        }

        if (numOfTicks > maxNumTick) {
            System.out.println("Timed out");
            System.exit(-1);
        }
    }

    /** The main function
     * @param args argument input
     */
    public static void main (String[]args){
        try {
            new ShadowLife(argsFromFile()).run();
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
}
