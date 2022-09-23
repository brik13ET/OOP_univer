package Vehicle;

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

    public String getManufacture()
    {
            return Manufacature;
    }
    public void setManufacture(String manuf)
    {
            Manufacature = manuf;
    }

    @Override
    public String[] getModelsTitle() {
            String[] ret = new String[models.length];
            for (int i = 0; i < models.length; i++)
            {
                    ret[i]= models[i].Title;
            }
            return ret;
    }

    @Override
    public int getModelCostByName(String model) throws NoSuchModelNameException
    {
        boolean found = false;
        int i = 0;
        while (found == false && i < models.length)
        {
            found = models[i].Title == null ? 
                    model == null : 
                    models[i].Title.equals(model);
            if (!found)
                i++;

        }
        if (!found || i > models.length)
            throw new RuntimeException("Model Name not found: " + model);
        else
            return models[i].Cost;
    }

    @Override
    public void setModelCostByName(String name, int cost) throws NoSuchModelNameException,ModelPriceOutOfBoundsException
    {
        boolean found = false;
        int i = 0;
        if (cost < 0)
            throw new ModelPriceOutOfBoundsException("cost < 0.");
        while(found != true && i < models.length)
        {
                if(models[i].Title == null ? name == null : models[i].Title.equals(name))
                {
                        found = true;
                        models[i].Cost = (int)cost;
                }

        }
    }

    @Override
    public void addModel(String title, int cost) throws DuplicateModelNameException, ModelPriceOutOfBoundsException
    {
        if (cost < 0)
            throw new ModelPriceOutOfBoundsException("Cost < 0");
        boolean hasSame = false;
        int i = 0;
        while (!hasSame && i < models.length)
        {
            hasSame = (models[i].Title == null ? title == null : models[i].Title.equals(title));
            i ++;
        }
        if (hasSame)
            throw new DuplicateModelNameException("Automobile class has same model: " + title);
        models = Arrays.copyOf(models, models.length+1);
        models[models.length - 1] = new Model(title, cost);
    }

    @Override
    public int getModelCount() {
        return models.length;
    }

    public class Model
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
    public void setModelTitleByName(String oldName,String newName)
    {
            boolean found = false;
            int i = 0;
            while(found != true && i < models.length)
            {
                    if(models[i].Title == null ? oldName == null : models[i].Title.equals(oldName))
                    {
                            found = true;
                            models[i].Title = newName;
                    }

            }
    }
    public String[] getModelsTitles()
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
    public void delModel(String Name) throws NoSuchModelNameException
    {
        boolean found = false;
        int i = 0;
        while(!found && i < models.length)
        {
            found = (Name == null ? models[i].Title == null : Name.equals(models[i].Title));
            if (!found)
                i ++;
        }
        if (!found)
            throw new NoSuchModelNameException("Model Name not found: " + Name);
        if (i != models.length - 1)
            models[i] = models[models.length - 1];
        models = Arrays.copyOf(models, models.length - 1);
    }
    
    public int getModelsCount()
    {
            return models.length;
    }
}