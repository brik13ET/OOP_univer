
import Vehicle.*;

class Main {

    public static void main(String argv[])
            throws
            DuplicateModelNameException,
            NoSuchModelNameException
    {
        IVehicle v = new Motocycle("Hurrington Motors", 3);
        //IVehicle v = new Automobile("Hyndai",5);

        v.addModel("Model 22", 1337_420); // DuplicateModelNameException, ModelCost
        v.delModel("Model 2"); // NoSuchModelNameException

        System.out.println("MAnufacter:\t" + v.getManufacture());
        v.setManufacture("AAAAAA-AAAAAAAaa-AAAAaa");
        System.out.println("v.setManufacture()");
        System.out.println("MAnufacter:\t" + v.getManufacture());
        System.out.println();

        VehicleAnalyzer.printPriceList(v);
        System.out.println("v.setModelTitle()");
        v.setModelTitle("Model 22", "Model 111"); // DuplicateModelNameException, NoSuchModelNameException
        VehicleAnalyzer.printPriceList(v);
        System.out.println();
        System.out.println("avg: " + VehicleAnalyzer.doAvg(v));

        System.out.println("v.setModelCostByName()");
        v.setModelCostByName("Model 33", 420_1337_00); // NoSuchModelNameException. ModelCost...
        System.out.println("avg: " + VehicleAnalyzer.doAvg(v));
    }
}
