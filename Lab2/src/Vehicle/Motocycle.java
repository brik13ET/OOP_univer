package Vehicle;

import java.time.Instant;

public final class Motocycle {

    private String Manufacture;
    private long lastEdit;
    private Model models;
    
    public String getManufacture()
    {
        return Manufacture;
    }
    
    public long getLastEdit()
    {
        return lastEdit;
    }
    
    public Model getModels()
    {
        return models;
    }
    
    public Motocycle(String Manuf, Model[] m)
    {
        Manufacture = Manuf;
        for (Model me : m)
        {
            this.addModel(me.Title, me.Cost);
        }
        lastEdit = Instant.now().getEpochSecond();
    }

    public void setManufacture(String manuf)
    {
        lastEdit = Instant.now().getEpochSecond();
        Manufacture = manuf;
    }

    public class Model
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

    private int modelsCount()
    {
        return 0;
    }
    
    private Model at(int index)
    {
        Model node = models;
        for (int i = 0; i < index && node != null; i++)
        {
            node = node.next;
        }
        return node;
    }
    
    public void setModelTitle(int index, String Name)
    {
        lastEdit = Instant.now().getEpochSecond();
        at(index).Title = Name;
    }

    public void setModelCost(int index, int cost)
    {
        lastEdit = Instant.now().getEpochSecond();
        at(index).Cost = cost;
    }

    public String getModelTitle(int index)
    {
        return at(index).Title;
    }

    public int getModelCost(int index)
    {
        Model c = models;
        int i = 0;
        while (c != null && i != index) {
            c = c.next;
            i++;
        }
        if (c == null)
            return 0;
        return c.Cost;
    }

    public int[] getModelsCost()
    {
        int[] ret = new int[modelsCount()];
        for (int i = 0; i < modelsCount(); i++)
        {
            ret[i]= at(i).Cost;
        }
        return ret;
    }

    public void changeCostOfModel(String model, int cost)
    {
        lastEdit = Instant.now().getEpochSecond();
        boolean found = false;
        int i = 0;
        while(found != true && i < modelsCount())
        {
            if(at(i).Title == null ? model == null : at(i).Title.equals(model))
            {
                found = true;
                at(i).prev.next.Cost = cost;
            }

        }
    }

    public void addModel(String Name, int cost)
    {
        lastEdit = Instant.now().getEpochSecond();
        Model m = new Model(Name, cost);
        m.prev = models.next;
        m.next = models.next.next;
        models.next.next.prev = m;
        models.next = m;
    }

    public void delModel(String Name)
    {
        lastEdit = Instant.now().getEpochSecond();
        boolean found = false;
        int i = 0;
        while(found != true && i < modelsCount())
        {
                if(at(i).Title == null ? Name == null : at(i).Title.equals(Name))
                {
                        found = true;
                }
        }
        if(found)
        {
            Model n = at(i);
            n.prev.next = n.next;
            n.next.prev = n.prev;
            
        }
    }
}
