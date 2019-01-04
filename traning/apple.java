import java.util.Collection;
import java.util.Comparator;
import java.util.Conparable;

class Apple{
    String name;
    String weight;
    public static void main(String[] args) {
        Collection.sort(inventory,new Comparator<Apple>() {
            public int compare(Apple a1,Apple a2){
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });
    }
    String getWeight(){
        return weight;
    }
}