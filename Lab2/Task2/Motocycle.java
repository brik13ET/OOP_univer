import java.time.Instant;
import java.util.Arrays;

public class Motocycle {

    String Manufacture;
    long lastEdit;
    Model models;

    public void SetManufacture(String manuf)
	{
        lastEdit = Instant.toEpochMilli();
		String Manufacture = manuf;
	}
	
	public String GetManufacture()
	{
		return Manufacture;
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
	
	public void SetModelTitle(int index, String Name)
	{
        lastEdit = Date.from(Instant.now());
		models[index].Title = Name;
	}

	public void SetModelCost(int index, int cost)
	{
        lastEdit = Date.from(Instant.now());
		models[index].Cost = cost;
	}

	public String GetModelTitle(int index)
	{
		return models[index].Title;
	}

	public int GetModelCost(int index)
	{
        Model c = models;
        int i = 0;
        while (c != null && i != index) {
            c = c.next;
            i++;
        }
        if 
	}

	public int[] GetModelsCost()
	{
		int[] ret = new int[models.length];
		for (int i = 0; i < models.length; i++)
		{
			ret[i]= models[i].Cost;
		}
		return ret;
	}

	public void ChangeCostOfModel(String model, int cost)
	{
        lastEdit = Date.from(Instant.now());
		boolean found = false;
		int i = 0;
		while(found != true && i < models.length)
		{
			if(models[i].Title == model)
			{
				found = true;
				models[i].Cost = cost;
			}

		}
	}

	public void AddModel(String Name, int cost)
	{
        lastEdit = Date.from(Instant.now());
		Model[] newM = Arrays.copyOf(models, models.length+1);
		newM[models.length] = new Model(Name, cost);
		models = newM;
	}

	public void DelModel(String Name)
	{
		Model[] newM = new Model[models.length-1];
		int outI = 0;

		boolean found = false;
		int i = 0;
		while(found != true && i < models.length)
		{
			if(models[i].Title == Name)
			{
				found = true;
			}
		}
		if(found)
		{
			for (i = 0; i < newM.length; i++)
			{
				if (models[i].Title != Name)
				{
					System.arraycopy(models, i, newM, outI, 1);
					outI++;
				}
			}
			models = newM;
		}
	}
}
