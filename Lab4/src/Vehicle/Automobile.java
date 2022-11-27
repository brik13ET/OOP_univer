package Vehicle;

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
            public double Cost;

            public Model(String title, double cost )
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
    public double  getModelCostByName(String model)
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
    public void setModelCostByName(String name, double  cost)
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
    public void addModel(String title, double cost)
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
    public double [] getModelsCost()
    {
        double[] ret = new double[models.length];
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
    
    // Lab 4
    
    @Override
    public String toString()
    {
        var sb = new StringBuilder();
        sb.append(this.Manufacature).append('\n');
        for (int i = 0; i < models.length; i++)
        {
            sb.append(
                    String.format("Model: %s\tCost: %f\n",
                            models[i].Title, models[i].Cost
                    )
            );
        }
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Automobile))
            return false;
        Automobile a = (Automobile)obj;
        if (a.hashCode() != this.hashCode())
            return false;
            
        if (a.Manufacature == null ?
                this.Manufacature != null :
                !a.Manufacature.equals(this.Manufacature)
            )
            return false;
        if (a.models.length != models.length)
            return false;
        for (int i = 0; i < models.length; i++) {
            if (!models[i].Title.equals(a.models[i].Title) ||
                    models[i].Cost != a.models[i].Cost)
                return false;
        }
        return true;
    }
    
    @Override
    public int hashCode()
    {
        return this.getClass().getCanonicalName().hashCode() ^
               this.Manufacature.hashCode() ^
               this.getModelCount();
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Automobile sc = (Automobile)super.clone();
        sc.models = new Model[sc.models.length];
        for (int i = 0; i < sc.models.length; i++) {
            sc.models[i] = new Model(models[i].Title, models[i].Cost);
        }
        return sc;
    }
}