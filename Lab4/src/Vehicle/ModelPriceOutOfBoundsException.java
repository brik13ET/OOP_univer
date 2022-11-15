/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Vehicle;

/**
 *
 * @author user0
 */
public class ModelPriceOutOfBoundsException extends RuntimeException {

    /**
     * Creates a new instance of <code>ModelPriceOutOfBoundsException</code>
     * without detail message.
     */
    public ModelPriceOutOfBoundsException() {
    }

    /**
     * Constructs an instance of <code>ModelPriceOutOfBoundsException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ModelPriceOutOfBoundsException(String msg) {
        super(msg);
    }
}
