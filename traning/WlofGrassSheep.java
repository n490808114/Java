import java.util.ArrayList;

public class WlofGrassSheep{
    public static void main(String[] args) {
        Farmer farmer =new Farmer();
        Wolf wolf = new Wolf();
        Grass grass = new Grass();
        Sheep sheep = new Sheep();

        ArrayList<Thing> thisSide =new ArrayList<Thing>();
        thisSide.add(wolf);
        thisSide.add(grass);
        thisSide.add(sheep);
        thisSide.add(farmer);
        ArrayList<Thing> otherSide = new ArrayList<Thing>();

        ArrayList<String> result = new ArrayList<String>();


        while (true){
            ArrayList<Thing> a;
            ArrayList<Thing> b;
            if(thisSide.contains(farmer)){
                a = thisSide;
                b = otherSide;
            }else if(otherSide.contains(farmer)){
                a = otherSide;
                b = thisSide;
            }
            a.remove(farmer);
            b.add(farmer);
            Thing thing = a.remove(0);

        }
    }
    boolean isEnd(){
        if (otherSide.length().equals(4)){
            return true;
        }else{
            return false;
        }
    }
    boolean isGameOver(){
        if (thisSide.contains(wolf)&&
            thisSide.contains(sheep)&&
            !thisSide.contains(farmer))
        {return true;}
        if (thisSide.contains(this.sheep)&&
            thisSide.contains(this.grass)&&
            !thisSide.contains(this.farmer))
        {return true;}
        if (otherSide.contains(this.wolf)&&
            otherSide.contains(this.sheep)&&
            !otherSide.contains(this.farmer))
        {return true;}
        if (otherSide.contains(this.sheep)&&
            otherSide.contains(this.grass)&&
            !otherSide.contains(this.farmer))
        {return true;}
        else
        {return false;}
    }
}

class Thing{
    String name = "thing";
}
class Wolf extends Thing{
    String name = "wolf";
}
class Grass extends Thing{
    String name = "grass";
}
class Sheep extends Thing{
    String name = "sheep";
}
class Farmer extends Thing{
    String name = "farmer";
}