import Vehicle.*;



class Main
{
    public static void main(String argv[])
    {
        Automobile a;
        a = new Automobile("Manuf1");
        for (int i = 0; i < 10; i++) {
            a.AddModel("model "+i, (i+1)*10_000);
        }
        int[] mc = a.GetModelsCost();
        for (int i = 0; i < mc.length; i++) {
            System.out.println(mc[i]);
        }
    }
}
