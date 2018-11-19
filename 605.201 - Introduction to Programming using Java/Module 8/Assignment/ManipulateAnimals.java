public class ManipulateAnimals
{
    public static void main(String[]args)
    {
        All[]objects = {new Vehicle("Tesla",33), new Animal("Cat"), new Vehicle("Honda",25), new Animal("Chicken")};
        for(All object: objects)
        {
            object.drawObjects();
            object.playSound();
            object.resizeObject();
            object.rotateObject();
        }
    }
}