public class Game01{
    public static void main(String[] args) {
        System.out.println("Game Begin!");
        System.out.println("we have two player");
        Player [] zw= new Player[2];
        zw[0] = new Player();
        zw[1] = new Player();
        zw[0].setName("ZHAO");
        zw[1].setName("WANG");
        System.out.println("we set three boat in de river!");
        Boat [] boat = new Boat[3];
        boat[0] = new Boat();
        boat[1] = new Boat();
        boat[2] = new Boat();
        boat[0].num = 1;
        boat[1].num = 2;
        boat[2].num = 3;
        int howLong = 100;
        System.out.println("This river has " + howLong + " Long!");
        boat[0].local(howLong);
        boat[1].local(howLong);
        boat[2].local(howLong);

        String turn = "ZHAO";
        int result;

        while (true){
            System.out.println("a new round");
            int z = 0;
            if (turn == "ZHAO"){
                z = 0;
            }else if (turn == "WANG"){
                z = 1;
            }
            System.out.println("Now is " + zw[z].getName()+ " guessing!");
            result = zw[z].guess(howLong);
            System.out.println(zw[z].getName() + " guess "+result);
            for(int x=0;x<3;x++){
                for(int y=0;y<boat[x].possion.length;y++){
                    if (result == boat[x].possion[y]){
                        System.out.println("No we got a boat BOOM!!!"+boat[x].num);
                        zw[z].addGoal();
                        System.out.println("The player "+ zw[z]+" got a goal!");
                        boat[x] =null;
                    }
                }

            }
            if (boat[0]==null||boat[1]==null||boat[2]==null){
                break;
            }
        }
        System.out.println(zw[0].getName() + " get "+ zw[0].getGoal());
        System.out.println(zw[1].getName() + " get "+ zw[1].getGoal());
        System.out.println("The boats all have been sinked");
    }
}


class Boat{
    int num = 0;
    int length = 3;
    int [] possion = new int[3];
    void local(int howLong){
        possion[0] = (int) (Math.random() * (howLong - length));
        int x = 0;
        while(x<length){
            possion[x] = possion[0] + x;
        }
    }
}

class Player{
    private String name = "player";
    private int goal = 0;
    int guess(int howLong){
        int guess;
        guess = (int) (Math.random() * howLong);
        System.out.println(name + " guess the boat`s possion is " + guess);
        return guess;
    }
    public void setName(String aName){
        name = aName;
    }
    public String getName(){
        return name;
    }
    public void addGoal(){
        goal = goal + 1;
    }
    public int getGoal(){
        return goal;
    }
}