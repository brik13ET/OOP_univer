/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehicle;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author user0
 */
public class Quadricycle implements IVehicle {

    private String mark;
    ArrayList<Model> store;
    
    public Quadricycle(String mark, int count)
    {
        this.mark = mark;
        store = new ArrayList<Model>();
        for (int i = 0; i < count; i++) {
            store.add(new Model("Model " + i, 10000 + i * 5000));
        }
    }
    
    private class Model implements Serializable
    {
        String Title;
        double Cost;
        
        public Model(String name, double cost)
        {
            Title = name;
            Cost = cost;
        }
    }
    
    @Override
    public String getManufacture() {
        return mark;
    }

    @Override
    public void setManufacture(String manuf) {
        mark = manuf;
    }

    @Override
    public void setModelTitle(String oldName, String newName)
            throws  DuplicateModelNameException,
                    NoSuchModelNameException
    {
        int i = 0;
        if (newName == null)
            throw new NullPointerException("Bad newName");
        while(i < store.size() && !store.get(i).Title.equals(newName))
            i++;
        if (i < store.size())
            throw new DuplicateModelNameException(newName);
        
        i = 0;
        while(i < store.size() && !store.get(i).Title.equals(oldName))
            i++;
        if (i == store.size())
            throw new NoSuchModelNameException(oldName);
        store.get(i).Title = newName;
    }

    @Override
    public String[] getModelsTitle()
    {
        String[] ret = new String[store.size()];
        for (int i = 0; i < store.size(); i++)
        {
                ret[i]= store.get(i).Title;
        }
        return ret;
    }

    @Override
    public double[] getModelsCost() {
        double[] ret = new double[store.size()];
        for (int i = 0; i < store.size(); i++)
        {
                ret[i]= store.get(i).Cost;
        }
        return ret;
    }

    @Override
    public double getModelCostByName(String model) throws NoSuchModelNameException {
        int i = 0;
        while (i < store.size() && !store.get(i).Title.equals(model))
            if (!store.get(i).Title.equals(model)) {
                i++;
            }
        if (i >= store.size())
            throw new NoSuchModelNameException(model);
        else
            return store.get(i).Cost;
    }

    @Override
    public void setModelCostByName(String model, double cost)
            throws NoSuchModelNameException
    {
        int i = 0;
        if (cost < 0)
            throw new ModelPriceOutOfBoundsException("cost < 0 : not in bounds");
        while(i < store.size())
        {
            if(store.get(i).Title.equals(model))
            {
                store.get(i).Cost = cost;
                break;
            }
            i++;
        }
    }

    @Override
    public int getModelCount() {
        return store.size();
    }

    // TODO
    
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
            if(model.Title.equals(title))
            {
                store.remove(model);
                return;
            }
        }
        throw new NoSuchModelNameException();
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        var ret = (Quadricycle)super.clone();
        ret.store = new ArrayList<Model>();
        for (Model model : store) {
            ret.store.add(new Model(model.Title, model.Cost));
        }
        return ret;
    }
    
    @Override
    public String toString()
    {
        var sb = new StringBuffer();
        sb.append(this.mark).append('\n');
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
