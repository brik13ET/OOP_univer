/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Vehicle;

/**
 *
 * @author user0
 */
public class NoSuchModelNameException extends Exception {

    /**
     * Creates a new instance of <code>NoSuchModelNameException</code> without
     * detail message.
     */
    public NoSuchModelNameException() {
    }

    /**
     * Constructs an instance of <code>NoSuchModelNameException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoSuchModelNameException(String modelName) {
        super("Model Name not found: " + modelName);
    }
    
}
