package Vehicle;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.time.Instant;

public final class Motocycle implements IVehicle{

    private String Manufacture;
    private long lastEdit;
    private Model head;
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

    private class Model implements Serializable,Cloneable
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
        
        @Override
        public boolean equals(Object obj)
        {
            if (!(obj instanceof Model))
                return false;
            return Title.equals(((Model)obj).Title) && Cost == ((Model)obj).Cost;
        }

        @Override
        public Object clone() throws CloneNotSupportedException 
        {
            Model ret = (Model)super.clone();
            ret.Title = new String(Title);
            ret.next = null;
            ret.prev = null;
            return ret;
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
        int[] ret = new int[size ];
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
        String[] ret = new String[size];
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
    
    // Lab 4
    
    @Override
    public String toString()
    {
        StringWriter sw = new StringWriter();
        try {
            VehicleAnalyzer.writeVehicle(this, sw);
        } catch (IOException ex) {
            System.err.println("Impossible exception occured:\n" + ex.getMessage());
        }
        return sw.toString();
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj.hashCode() != this.hashCode())
            return false;
        if (!(obj instanceof Motocycle))
            return false;
            
        Motocycle a = (Motocycle)obj;
        if (!this.Manufacture.equals(a.Manufacture))
            return false;        
        // CDLL compare
        
        if (this.size != a.size)
            return false;
        
        Model m = this.head.next;
        Model n = a.head.next;
        boolean same = true;
        
        for (int i = 0; i < size && same; i++) {
            same = (n.Cost == m.Cost ) && (m.Title.equals(n.Title));
            m = m.next;
            n = n.next;
        }
        if (!same)
            return false;
        return true;
    }
    
    @Override
    public int hashCode()
    {
        return (this.getClass().getCanonicalName().hashCode() << 16) |
               (this.Manufacture.hashCode() << 8) |
               this.getModelCount();
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Motocycle sc = (Motocycle)super.clone();
        Model n = head;
        Model p = (Model)n.clone();
        Model __head = p;
        do {
            p.next = (Model)n.next.clone();
            p.next.prev = p;
            
            p = p.next;
            n = n.next;
        } while (n.next != head);
        __head.prev = p;
        p.next = __head;
        sc.head = __head;
        return sc;
    }
}
