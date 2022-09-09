import MyFirstPackage.MySecondClass;

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
