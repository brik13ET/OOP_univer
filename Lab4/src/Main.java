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
        
        System.out.println("Оригинал:\n" + a.toString());
        System.out.println("Копия:\n" + b.toString());
        
        System.out.println("Хэш оригинала: " + a.hashCode());
        System.out.println("Хэш копии: " + b.hashCode());
        System.out.println("Хэш 2й копии: " + c.hashCode());
        
        System.out.println("Оригинал равен копии ? " + ((IVehicle)b).equals(a));
        System.out.println("2я копия равна копии ? " + ((IVehicle)b).equals(c));
    }
    
}
