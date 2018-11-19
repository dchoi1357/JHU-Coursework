//Vehicle extending All
public class Vehicle extends All
{
    //Instance Variables for Name and Age
    private String name;
    private int age;
    //Constructor
    public Vehicle(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
    //Setters and Getters
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
    //Implemented methods from All
    public void resizeObject()
    {
        System.out.println("Resizing a Vehicle");
    }
    public void playSound()
    {
        System.out.println("Vehicle Sound");
    }
    public void rotateObject()
    {
        System.out.println("Rotating a Vehicle");
    }
    public void drawObjects()
    {
        System.out.println("Drawing a Vehicle");
    }
}