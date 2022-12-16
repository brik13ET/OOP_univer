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
    String   getManufacture();
    void     setManufacture(String manuf);
    
    void     setModelTitle(String oldName, String newName)
            throws DuplicateModelNameException, NoSuchModelNameException;
    String[] getModelsTitle();
    
    double[]    getModelsCost();
    double      getModelCostByName(String model)
            throws NoSuchModelNameException;
    void     setModelCostByName(String model, double cost)
            throws NoSuchModelNameException;
    
    int      getModelCount();
    void     addModel(String title, double cost)
            throws DuplicateModelNameException;
    void     delModel(String title)
            throws NoSuchModelNameException;
        
    @Override
    String toString();
    @Override
    boolean equals(Object obj);
    @Override
    int hashCode();
    
    Object clone() throws CloneNotSupportedException;
}
