/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehicle;

/**
 *
 * @author user0
 */
public class VehicleAnalyzer {
    public static double doAvg(IVehicle v)
    {
        double sum = 0;
        var b = v.getModelsCost();
        int count = b.length;
        for (int i = 0; i < count; i++) {
            sum += b[i];
        }
        return sum / count;
    }
    
    public static void printPriceList(IVehicle v) throws NoSuchModelNameException
    {
        String[] s = v.getModelsTitle();
        for (String string : s) {
            System.out.println(string+"\t"+v.getModelCostByName(string));
        }
    }
}
