import java.util.ArrayList;


abstract class Animal{
    String name = "animal";
    abstract void bark();
}
class Dog extends Animal{
    String name = "dog";
    void bark(){
        System.out.println("wolf!");
    }
    void eat(){
        System.out.println("eat nothing");
    }
}
public class ObjectTestDrive{
    public static void main(String[] args) {
        ArrayList<Animal> test = new ArrayList<Animal>();
        Dog a = new Dog();
        Animal b = new Animal();
        test.add(a);
        test.add(b);
        test.get(0).bark();
        test.get(1).bark();
        //test.get(0).eat();
    }

}