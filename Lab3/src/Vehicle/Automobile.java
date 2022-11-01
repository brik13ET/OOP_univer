package Vehicle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Arrays;

public class Automobile implements IVehicle{
    private String Manufacature;
    private Model models[];

    public Automobile(String manuf, int size)
    {
        Manufacature = manuf;
        models = new Model[size];
        for (int i = 0; i < size; i++)
        {
            models[i] = new Model("Model " + i, i*15000+10000);
        }
    }

    private class Model implements Serializable
    {
            public String Title;
            public int Cost;

            public Model(String title, int cost )
            {
                    Title = title;
                    Cost = cost;
            }
    }
    
    @Override
    public String getManufacture()
    {
            return Manufacature;
    }
    @Override
    public void setManufacture(String manuf)
    {
            Manufacature = manuf;
    }

    @Override
    public int getModelCostByName(String model)
            throws NoSuchModelNameException
    {
        int i = 0;
        while (i < models.length && !models[i].Title.equals(model))
            if (!models[i].Title.equals(model)) {
                i++;
            }
        if (i >= models.length)
            throw new NoSuchModelNameException(model);
        else
            return models[i].Cost;
    }

    @Override
    public void setModelCostByName(String name, int cost)
            throws
                NoSuchModelNameException
    {
        int i = 0;
        if (cost < 0)
            throw new ModelPriceOutOfBoundsException("cost < 0 : not in bounds");
        while(i < models.length)
        {
            if(models[i].Title.equals(name))
            {
                models[i].Cost = cost;
                break;
            }
            i++;
        }
    }

    @Override
    public void addModel(String title, int cost)
            throws
                DuplicateModelNameException
    {
        if (cost < 0)
            throw new ModelPriceOutOfBoundsException("cost < 0 : not in bounds");
        int i = 0;
        while (i < models.length && !models[i].Title.equals(title))
            i ++;
        if (i < models.length)
            throw new DuplicateModelNameException(title);
        models = Arrays.copyOf(models, models.length+1);
        models[models.length - 1] = new Model(title, cost);
    }

    @Override
    public int getModelCount() {
        return models.length;
    }

    @Override
    public void setModelTitle(String oldName,String newName)
            throws
                DuplicateModelNameException,
                NoSuchModelNameException
    {
        int i = 0;
        if (newName == null)
            throw new NullPointerException("Bad newName");
        while(i < models.length && !models[i].Title.equals(newName))
            i++;
        if (i < models.length)
            throw new DuplicateModelNameException(newName);
        
        i = 0;
        while(i < models.length && !models[i].Title.equals(oldName))
            i++;
        if (i == models.length)
            throw new NoSuchModelNameException(oldName);
        models[i].Title = newName;
        
    }
    @Override
    public String[] getModelsTitle()
    {
            String[] ret = new String[models.length];
            for (int i = 0; i < models.length; i++)
            {
                    ret[i]= models[i].Title;
            }
            return ret;
    }
    
    @Override
    public int[] getModelsCost()
    {
        int[] ret = new int[models.length];
        for (int i = 0; i < models.length; i++)
        {
            ret[i]= models[i].Cost;
        }
        return ret;
    }
    @Override
    public void delModel(String Name)
            throws NoSuchModelNameException
    {
        int i = 0;
        while(i < models.length && !models[i].Title.equals(Name))
        {
                i ++;
        }
        if (i >= models.length)
            throw new NoSuchModelNameException(Name);
        System.arraycopy(models, i+1, this.models, i, models.length - i - 1);
        models = Arrays.copyOf(models, models.length-1);
    }
    
    public int getModelsCount()
    {
            return models.length;
    }
    
}