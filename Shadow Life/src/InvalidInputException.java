import java.lang.Exception;

/** An exception for detecting whether the argument input is valid
 * @author xiaotongwang
 */
public class InvalidInputException extends Exception{
    /** Constructor of InvalidInputException
     */
    public InvalidInputException(){
        super("usage: ShadowLife <tick rate> <max ticks> <world file>");
    }
}
