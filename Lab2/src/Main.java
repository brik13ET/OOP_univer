import Vehicle.*;

class Main
{
    public static void Test(IVehicle v) 
            throws 
                DuplicateModelNameException, 
                NoSuchModelNameException,
                ModelPriceOutOfBoundsException
    {
        String n = v.getModelsTitle()[0];
        //v.addModel(n, 0);
        //v.addModel("EEEEEEE", -273);
        //v.delModel(n+"xddd");
        
    }
    public static void main(String argv[])
            throws 
                DuplicateModelNameException, 
                NoSuchModelNameException,
                ModelPriceOutOfBoundsException
    {
        Motocycle a = new Motocycle("Hurrington Motors",3);
        Automobile b = new Automobile("Hyndai",5);
        
        IVehicle v = b;
        
        v.addModel("Vega", 1337_420);
        v.delModel("Model 1");
        
        System.out.println(v.getManufacture());
        v.setManufacture("EEEEEEEEEEEEEEEEEEEE");
        System.out.println(v.getManufacture());
        
        v.setModelTitle("Model 0", "Begginer");
        VehicleAnalyzer.printPriceList(v);
        
        System.out.println("avg: "+  VehicleAnalyzer.doAvg(v));
        v.setModelCostByName("Begginer", 420_1337_00);
        
        System.out.println("cnt: " + v.getModelCount() );
        
        System.out.println("avg: "+  VehicleAnalyzer.doAvg(v));
        v.setModelCostByName("Begginer", 420_1337_00);
        
        try
        {
            Test(v);
        }catch(
                NoSuchModelNameException | 
                DuplicateModelNameException | 
                ModelPriceOutOfBoundsException ex){
            //System.out.println(ex);
            ex.printStackTrace(System.err);
        }
    }
}
