package Vehicle;

import java.io.Serializable;
import java.time.Instant;

public final class Motocycle implements IVehicle{

    private String Manufacture;
    private transient long lastEdit;
    private final Model head;
    private int size = 0;
    
    {
        lastEdit = Instant.now().getEpochSecond();
    }
    
    public Motocycle(String manuf, int size)
    {
        head = new Model("", 0);
        Manufacture = manuf;
        head.next = head;
        head.prev = head;
        try {
            for (int i = 0; i < size; i++)
            {
                this.addModel("Model "+i, i*25_000 + 10_000);
            }
        } catch (DuplicateModelNameException ex) // impossible exception
        {
            ex.printStackTrace(System.out);
        }
        lastEdit = Instant.now().getEpochSecond();
    }

    private class Model implements Serializable
    {
        public String Title;
        public int Cost;
        Model next;
        Model prev;

        public Model(String title, int cost)
        {
            Title = title;
            Cost = cost;
        }
    }
    
    @Override
    public String getManufacture()
    {
        return Manufacture;
    }
    
    @Override
    public void setManufacture(String manuf)
    {
        lastEdit = Instant.now().getEpochSecond();
        Manufacture = manuf;
    }

    @Override
    public void setModelTitle(String oldName, String newName)
            throws
                NoSuchModelNameException,
                DuplicateModelNameException
            
    {
        Model node = head.next;
        
        while(!node.Title.equals(newName) && node != head)
        {
            node = node.next;
        }
        if (node != head)
                throw new DuplicateModelNameException(newName);
        
        node = head.next;
        while(!node.Title.equals(oldName) && node != head)
        {
            node = node.next;
        }
        if (node == head)
                throw new NoSuchModelNameException(oldName);
        
        node.Title = newName;
        lastEdit = Instant.now().getEpochSecond();
    }

    @Override
    public int getModelCostByName(String model) throws NoSuchModelNameException
    {
        Model node = head.next;
        while(!node.Title.equals(model) && node != head)
        {
            node = node.next;
        }
        if (node == head)
            throw new NoSuchModelNameException(model);
        else
            return node.Cost;
    }

    @Override
    public void setModelCostByName(String model, int cost) throws NoSuchModelNameException
    {
        
        if (cost < 0) {
            throw new ModelPriceOutOfBoundsException();
        }
        Model node = head.next;
        while(!node.Title.equals(model) && node != head)
        {
            node = node.next;
        }
        if (node == head)
            throw new NoSuchModelNameException(model);
        node.Cost = cost;
        lastEdit = Instant.now().getEpochSecond();
    }

    @Override
    public int getModelCount()
    {
        return size;
    }

    @Override
    public void addModel(String title, int cost)
            throws
                DuplicateModelNameException
    {
        
        if (cost < 0)
            throw new ModelPriceOutOfBoundsException("cost < 0 : not in bounds");
        Model node = head.next;
        while ( !node.Title.equals(title) && node != head)
        {
            node = node.next;
        }
        if (node != head)
            throw new DuplicateModelNameException("Motocycle class has same model: " + title);
        
        Model m = new Model(title, cost);
        m.prev = head.prev;
        m.next = head;
        head.prev = m;
        m.prev.next = m;
        size++;
        lastEdit = Instant.now().getEpochSecond();
    }

    @Override
    public int[] getModelsCost()
    {
        int[] ret = new int[getModelCount()];
        Model node = head.next;
        int i = 0;
        while(node != head)
        {
            ret[i] = node.Cost;
            i ++;
            node = node.next;
        }
        return ret;
    }
    
    @Override
    public String[] getModelsTitle()
    {
        String[] ret = new String[getModelCount()];
        Model node = head.next;
        int i = 0;
        while(node != head)
        {
            ret[i] = node.Title;
            i ++;
            node = node.next;
        }
        return ret;
    }
    
    @Override
    public void delModel(String Name) throws NoSuchModelNameException
    {
        Model node = head.next;
        while(!node.Title.equals(Name) && node != head)
        {
            node = node.next;
        }
        if(node != head)
        {
            Model n = node;
            n.prev.next = n.next;
            n.next.prev = n.prev;
        }
        else
            throw new NoSuchModelNameException(Name);
        size--;
        lastEdit = Instant.now().getEpochSecond();
            
    }
    
    public long getLastEdit()
    {
        return lastEdit;
    }
}
