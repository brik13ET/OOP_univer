/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehicle;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author user0
 */
public class Moped implements IVehicle
{
    private LinkedList<Model> store;
    private String Mark;
    
    public Moped(String mark, int count)
    {
        Mark = mark;
        store = new LinkedList<>();
        for (int i = 0; i < count; i++)
        {
            store.add(new Model("Model " + i, 10000.d + 2300*i));
        }
    }
    
    private class Model implements Serializable
    {
        double Cost;
        String Title;
        
        public Model (String name, double cost)
        {
            Title = name;
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
        for (int i = 0; i < store.size(); i++)
        {
            if (store.get(i).Title == newName)
            {
                throw new DuplicateModelNameException();
            }
        }
        for (int i = 0; i < store.size(); i++)
        {
            if (store.get(i).Title == oldName)
            {
                Model m = store.get(i);
                m.Title = newName;
                store.add(new Model(m.Title, m.Cost));
                store.remove(i);
                return;
            }
        }
        throw new NoSuchModelNameException();
    }

    @Override
    public String[] getModelsTitle()
    {
        String[] ret = new String[store.size()];
        for (int i = 0; i < store.size(); i++)
        {
            ret[i] = store.get(i).Title;
        }
        return ret;
    }

    @Override
    public double[] getModelsCost() {
        var ret = new double[store.size()];
        for (int i = 0; i < store.size(); i++)
        {
            ret[i] = store.get(i).Cost;
        }
        return ret;
    }

    @Override
    public double getModelCostByName(String model)
            throws NoSuchModelNameException
    {
        for (int i = 0; i < store.size(); i++)
        {
            if (store.get(i).Title == model)
            {
                return store.get(i).Cost;
            }
        }
        throw new NoSuchModelNameException();
    }

    @Override
    public void setModelCostByName(String model, double cost)
            throws NoSuchModelNameException
    {
        for (int i = 0; i < store.size(); i++)
        {
            if (store.get(i).Title.equals(model))
            {
                store.get(i).Cost = cost;
                return;
            }
        }
        throw new NoSuchModelNameException();
    }

    @Override
    public int getModelCount() {
        return store.size();
    }

    @Override
    public void addModel(String title, double cost) throws DuplicateModelNameException {
        if (cost < 0)
            throw new ModelPriceOutOfBoundsException();
        for (Model model : store) {
            if (model.Title.equals(title))
                throw new DuplicateModelNameException();
        }
        store.add(new Model(title, cost));
    }

    @Override
    public void delModel(String title) throws NoSuchModelNameException {
        for (Model model : store) {
            if (model.Title.equals(title)) {
                store.remove(model);
                return;
            }
        }
        throw new NoSuchModelNameException();
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        var ret = (Moped)super.clone();
        ret.store = new LinkedList<Model>();
        for (Model model : store) {
            ret.store.add(new Model(model.Title, model.Cost));
        }
        return ret;
    }
    
    @Override
    public String toString()
    {
        var sb = new StringBuffer();
        sb.append(this.Mark).append('\n');
        for (Model m : store)
        {
            sb.append(
                String.format("Model: %s\tCost: %f\n",
                    m.Title, m.Cost
                )
            );
        }
        return sb.toString();
    }
}
