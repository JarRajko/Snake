package map;

/**
 *
 * @author 
 */
public class OutOfMapException extends Exception {
    public OutOfMapException() {
        super("Object is out of map range!");
    }
}
