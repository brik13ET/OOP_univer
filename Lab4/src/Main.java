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
        
        
        //var a = new Motocycle("(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧", 2);
        var a = new Automobile("(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧", 2);
        Object b ,c;
        try {
            b = a.clone();
            c = a.clone();
        } catch (CloneNotSupportedException ex) {
            System.err.println(ex.getMessage());
            return;
        }
        
        a.setModelTitle("Model 1", "ヾ(•ω•`)o");
        
        System.out.println(a.toString());
        System.out.println(b.toString());
        
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
        
        System.out.println(((IVehicle)b).equals(a));
        System.out.println(((IVehicle)b).equals(c));
    }
    
}
