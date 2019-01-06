class StaticSuper{

    static{
        System.out.println("1,Super static block");
    }
    StaticSuper(){
        System.out.println("2,Super Constructor");
    }
}

public class StaticTests extends StaticSuper{
    static int rand;
    static{
        rand = (int)(Math.random() * 6);
        System.out.println("3.Static block " + rand);
    }
    StaticTests(){
        System.out.println("4.Constructor");
    }
    public static void main(String[] args) {
        System.out.println("5.In Main");
        StaticTests st = new StaticTests();
    }
}