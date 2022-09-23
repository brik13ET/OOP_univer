/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Vehicle;

/**
 *
 * @author user0
 */
public class DuplicateModelNameException extends Exception {

    /**
     * Creates a new instance of <code>DuplicateModelNameException</code>
     * without detail message.
     */
    public DuplicateModelNameException() {
    }

    /**
     * Constructs an instance of <code>DuplicateModelNameException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public DuplicateModelNameException(String msg) {
        super(msg);
    }
}
