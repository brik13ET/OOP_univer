import java.util.Random;

class MyFirstClass
{
    public static void main(String[] args)
    {
        MySecondClass obj = new MySecondClass(5);
        obj.setArray(0, 2);
        System.out.println("avg:\t"+obj.avg());
        obj.println();
    }
}

class MySecondClass
{
    private int[] Arr;

    public int getArray(int index)
    {
		if (index >= Arr.length || index < 0)
			return 0;
        return Arr[index];
    }

    public void setArray(int index, int value)
    {
		if (index >= Arr.length || index < 0)
			return;
        Arr[index] = value;
    }

    public MySecondClass(int n)
    {
		if (n < 0)
			return ;
        Arr = new int[n]; 
        Random randGenerator = new Random();
        for (int j = 0; j < Arr.length; j++)
            Arr[j] = randGenerator.nextInt();
    }

    public float avg()
    {
        long sum = 0;
        for (int i : Arr)
            sum += i;
        return sum * (1f / Arr.length);
    }

    public void println()
    {
        for (int j = 0; j < Arr.length; j++)
            System.out.println(j+": "+Arr[j]);
    }
}