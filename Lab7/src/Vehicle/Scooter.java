/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehicle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author user0
 */
public class Scooter implements IVehicle
{
    String Mark;
    
    HashMap<String,Double> store;
    
    public Scooter(String mark, int count)
    {
        Mark = mark;
        store = new HashMap<String,Double>();
        for (int i = 0; i < count; i++) {
            store.put("Model " + i,10000.d + 5000*i);
        }
    }
    
    private class Model implements Serializable
    {
            public String Title;
            public double Cost;

            public Model(String title, double cost )
            {
                    Title = title;
                    Cost = cost;
            }
    }

    @Override
    public String getManufacture() {
        return Mark;
    }

    @Override
    public void setManufacture(String manuf) {
        Mark = manuf;
    }

    @Override
    public void setModelTitle(String oldName, String newName)
            throws  DuplicateModelNameException,
                    NoSuchModelNameException
    {
        var cost = store.get(oldName);
        var newmodel = store.get(newName);
        if (cost == null)
            throw new NoSuchModelNameException();
        if (newmodel != null)
            throw new DuplicateModelNameException();
        store.remove(oldName);
        store.put(newName, cost);
    }

    @Override
    public String[] getModelsTitle() {
        var models = store.keySet();
        var ret = new String[models.size()];
        int i = 0;
        for (var model : models) {
            ret[i] = model;
            i++;
        }
        return ret;
    }

    @Override
    public double[] getModelsCost()
    {
        var models = store.values();
        double[] ret = new double[models.size()];
        int i = 0;
        for (Double model : models) {
            ret[i] = model;
            i++;
        }
        return ret;
    }

    @Override
    public double getModelCostByName(String model)
            throws NoSuchModelNameException {
        return store.get(model);
    }

    @Override
    public void setModelCostByName(String model, double cost) 
            throws NoSuchModelNameException
    {
        var gcost = store.get(model);
        if (gcost == null)
            throw new NoSuchModelNameException();
        store.remove(model);
        store.put(model,cost);
    }

    @Override
    public int getModelCount() {
        return store.size();
    }

    @Override
    public void addModel(String title, double cost)
            throws DuplicateModelNameException
    {
        if (cost < 0)
            throw new ModelPriceOutOfBoundsException();
        if (store.get(title) != null)
            throw new DuplicateModelNameException();
        store.put(title, cost);
    }

    @Override
    public void delModel(String title)
            throws NoSuchModelNameException
    {
        if (store.get(title) == null)
            throw new NoSuchModelNameException();
        store.remove(title);
    }
    
    @Override
    public int hashCode()
    {
        return Mark.hashCode() ^ store.hashCode();
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (!(o instanceof Scooter))
            return false;
        var s = (Scooter)o;
        return Mark.equals(s.Mark) && store.equals(s.store);
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Scooter ret = (Scooter)super.clone();
        ret.store = new HashMap<String,Double>();
        for (var entr : store.entrySet())
        {
            ret.store.put(entr.getKey(), entr.getValue());
        }
        return ret;
    }
    
    @Override
    public String toString()
    {
        var sb = new StringBuffer();
        sb.append(this.Mark).append('\n');
        for (Map.Entry<String, Double> entry : store.entrySet())
        {
            sb.append(
                String.format("Model: %s\tCost: %f\n",
                    entry.getKey(), entry.getValue()
                )
            );
        }
        return sb.toString();
    }
}
