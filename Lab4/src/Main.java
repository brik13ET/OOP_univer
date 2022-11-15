/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author user0
 */
import Vehicle.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DuplicateModelNameException {
        var a = new Automobile("0xDEADBEEF", 2);
        IVehicle b;
        try {
            b = (Automobile)a.clone();
        } catch (CloneNotSupportedException ex) {
            System.err.println(ex.getMessage());
            return;
        }
        a.addModel("XDXDXDXD", 420_1337);
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(b == a);
        
    }
    
}
