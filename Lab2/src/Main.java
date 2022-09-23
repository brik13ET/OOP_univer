import Vehicle.*;


class Main
{
    public static void Test(IVehicle v) throws NoSuchModelNameException, DuplicateModelNameException, ModelPriceOutOfBoundsException
    {
        String n = v.getModelsTitle()[0];
        //v.addModel(n, 0);
        //v.addModel("EEEEEEE", -273);
        //v.delModel(n+"xddd");
        
    }
    public static void main(String argv[]) throws DuplicateModelNameException, NoSuchModelNameException
    {
        System.out.println("Motocycle");
        Motocycle a;
        
        a = new Motocycle("Hurrington Motors",3);
        a.addModel("Vega", 1337_42);
        a.delModel("Model 1");
        System.out.println("Task5");
        
        double avg = VehicleAnalyzer.doAvg(a);
        System.out.println("avg: "+  avg);
        VehicleAnalyzer.printPriceList(a);
        
        System.out.println("Automobile");
        Automobile b;
        b = new Automobile("Hyndai",5);
        b.addModel("Solaris", 1_000_000);
        b.delModel("Model 0");
        System.out.println("Task5");
        
        double avg2 = VehicleAnalyzer.doAvg(b);
        System.out.println("avg: "+  avg2);
        VehicleAnalyzer.printPriceList(b);
        
        try
        {
            Test(a);
        }catch(NoSuchModelNameException ex){
            System.out.println(ex);
        }catch(DuplicateModelNameException ex){
            System.out.println(ex);
        }catch(ModelPriceOutOfBoundsException ex){
            System.out.println(ex);
        }
        try
        {
            Test(b);
        }catch(NoSuchModelNameException ex){
            System.out.println(ex);
        }catch(DuplicateModelNameException ex){
            System.out.println(ex);
        }catch(ModelPriceOutOfBoundsException ex){
            System.out.println(ex);
        }
                
    }
}
