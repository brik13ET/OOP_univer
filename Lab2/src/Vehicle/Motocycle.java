package Vehicle;

import java.time.Instant;
// NetBeans moment
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Motocycle implements IVehicle{

    private String Manufacture;
    private long lastEdit;
    private final Model head;
    
    public Motocycle(String manuf, int size)
    {
        head = new Model("", 0);
        Manufacture = manuf;
        head.next = head;
        head.prev = head;
        for (int i = 0; i < size; i++) {
            try {
                this.addModel("Model "+i, i*25_000 + 10_000);
            } catch (DuplicateModelNameException | ModelPriceOutOfBoundsException ex) {
                Logger.getLogger(Motocycle.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    public void setModelTitleByName(String oldName, String newName) throws NoSuchModelNameException {
        
        lastEdit = Instant.now().getEpochSecond();
        boolean found = false;
        Model node = head.next;
        while(found != true && node != head)
        {
            found = node.Title == null ? 
                    oldName == null : 
                    node.Title.equals(oldName);
            if (!found)
                node = node.next;
        }
        if (!found)
                throw new NoSuchModelNameException("Model not found: "+ oldName);

        node.prev.next.Title = newName;
    }

    @Override
    public int getModelCostByName(String model) throws NoSuchModelNameException {
        boolean found = false;
        Model node = head.next;
        while(found != true && node != head)
        {
            found = node.Title == null ? model == null : node.Title.equals(model);
            if ( !found )
                node = node.next;
            
        }
        if (!found)
            throw new NoSuchModelNameException("Model \""+model+"\" not found.");
        else
            return node.Cost;
    }

    @Override
    public void setModelCostByName(String model, int cost) throws NoSuchModelNameException, ModelPriceOutOfBoundsException {
        
        lastEdit = Instant.now().getEpochSecond();
        boolean found = false;
        Model node = head.next;
        while(found != true && node != head)
        {
            found = node.Title == null ? model == null : node.Title.equals(model);
            if (!found)
                    node = node.next;
        }
        if (!found)
            throw new NoSuchModelNameException("Model \""+model+"\" not found.");
        node.Cost = cost;
    }

    @Override
    public int getModelCount() {
        
        int ret = 0;
        Model node;
        node = head.next;
        while (node != head)
        {
            ret ++;
            node = node.next;
        }
        return ret;
    }

    @Override
    public void addModel(String title, int cost) throws DuplicateModelNameException, ModelPriceOutOfBoundsException {
        
        if (cost < 0)
            throw new ModelPriceOutOfBoundsException("Cost < 0");
        boolean hasTitle = false;
        Model node = head.next;
        while ( !hasTitle && node != head)
        {
            hasTitle = (node.Title == null ? title == null : node.Title.equals(title));
            node = node.next;
        }
        if (hasTitle)
            throw new DuplicateModelNameException("Motocycle class has same model: " + title);
        lastEdit = Instant.now().getEpochSecond();
        Model m = new Model(title, cost);
        m.prev = head;
        m.next = head.next;
        head.next = m;
        m.next.prev = m;
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

    public int getCostOfModelByName(String model) throws NoSuchModelNameException
    {
        lastEdit = Instant.now().getEpochSecond();
        boolean found = false;
        Model node = head.next;
        while(found != true && node != head)
        {
            found = node.Title == null ? 
                    model == null : 
                    node.Title.equals(model);
            if (!found)
                node = node.next;
        }
        if (!found)
            throw new NoSuchModelNameException(
                    "Название модели \""+model+"\" не найдено.");
        return node.Cost;
    }
    
    @Override
    public String[] getModelsTitle()
    {
        String[] ret = new String[getModelCount()];
        Model node = head.prev;
        for (int i = 0; i < ret.length && node != head; i++) {
            ret[i] = node.Title;
            node = node.prev;
        }
        return ret;
    }
    
    @Override
    public void delModel(String Name) throws NoSuchModelNameException
    {
        lastEdit = Instant.now().getEpochSecond();
        boolean found = false;
        Model node = head.next;
        while(found == false && node != head)
        {
            found = node.Title == null ? Name == null : node.Title.equals(Name);
            if (!found)
                node = node.next;
        }
        if(found)
        {
            Model n = node;
            n.prev.next = n.next;
            n.next.prev = n.prev;
        }
        else
            throw new NoSuchModelNameException(
                    "Название модели \""+Name+"\" не найдено.");
            
    }

    
    public long getLastEdit()
    {
        return lastEdit;
    }
}
