/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Vehicle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author user0
 */
public interface IVehicle extends Serializable, Cloneable{
    public String   getManufacture();
    public void     setManufacture(String manuf);
    
    public void     setModelTitle(String oldName, String newName)
            throws DuplicateModelNameException, NoSuchModelNameException;
    public String[] getModelsTitle();
    
    public int[]    getModelsCost();
    public int      getModelCostByName(String model)
            throws NoSuchModelNameException;
    public void     setModelCostByName(String model, int cost)
            throws NoSuchModelNameException;
    
    public int      getModelCount();
    public void     addModel(String title, int cost)
            throws DuplicateModelNameException;
    public void     delModel(String title)
            throws NoSuchModelNameException;
    
    @Override
    public String toString();
    @Override
    public boolean equals(Object obj);
    @Override
    public int hashCode();
    public Object clone() throws CloneNotSupportedException;
}
