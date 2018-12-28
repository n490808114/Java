public class MyDogList{
    private Dog[] dogs = new Dog[5];
    private int nextIndex = 0;
    public void add(Dog d){
        if(nextIndex < dogs.length){
            dogs[nextIndex] = d;
            nextIndex ++;
            System.out.println(" " + dogs.length);
        }else if(nextIndex == dogs.length){
            Dog[] dogsAdd = new Dog[nextIndex +1];
            for (int i=0;i<dogs.length;i++){
                dogsAdd[i] =dogs[i];
            }
            dogsAdd[dogs.length] = d;
            dogs = dogsAdd;
            nextIndex ++;
            System.out.println("add " + dogs.length);
        }

    }

    public static void main(String[] args) {
        MyDogList a = new MyDogList();
        Dog d = new Dog();
        a.add(d);

    }
}
class Dog{
    String name = "123";
}