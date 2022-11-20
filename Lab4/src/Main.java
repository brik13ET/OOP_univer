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
    public static void main(String[] args)
            throws  DuplicateModelNameException,
                    NoSuchModelNameException
    {
        var a = new Motocycle("(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧", 2);
        IVehicle b ,c;
        try {
            b = (Motocycle)a.clone();
            c = (Motocycle)a.clone();
        } catch (CloneNotSupportedException ex) {
            System.err.println(ex.getMessage());
            return;
        }
        
        a.addModel("(╯°□°）╯︵ ┻━┻", 420_1337);
        a.setModelTitle("Model 1", "ヾ(•ω•`)o");
        
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
        System.out.println(((Motocycle)b).equals(a));
        System.out.println(((Motocycle)b).equals(c));
    }
    
}
