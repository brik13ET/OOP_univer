/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Vehicle;

/**
 *
 * @author user0
 */
public interface IVehicle {
    public String   getManufacture();
    public String[] getModelsTitle();
    public int[]    getModelsCost();
    public int      getModelCostByName(String model) throws NoSuchModelNameException;
    public int      getModelCount();
    public void     setManufacture(String manuf);
    public void     setModelTitleByName(String oldName, String newName) throws NoSuchModelNameException;
    public void     setModelCostByName(String model, int cost) throws NoSuchModelNameException,ModelPriceOutOfBoundsException;
    public void     addModel(String title, int cost) throws DuplicateModelNameException,ModelPriceOutOfBoundsException;
    public void     delModel(String title) throws NoSuchModelNameException;
}
