import java.util.ArrayList;

public class Boat{
    String name = new String();
    ArrayList<String> locationCells = new ArrayList<String>();

    public String checkYourself(String userGuess){
        if(locationCells.contains(userGuess)){
            locationCells.remove(userGuess);
            if (!locationCells.isEmpty()){
                return "Hit";
            }else{
                System.out.println("You shoot down the " + name);
                return "Kill";
            }
        }else{
            return "Miss";
        }
    }
    public ArrayList<String> getBoatLocation(){
        char[] line = new char[3];
        int[] column = new int[3];
        char[] lineMap = {'A','B','C','D','E','F','G'};
        //int[] columnMap = {1,2,3,4,5,6,7};//for show the column map ,never use it;
        if (((int)(Math.random()*2)) == 0){
            char lineCell = lineMap[(int)(Math.random()*7)];
            for(int i=0;i<3;i++){
                line[i] = lineCell;
            }
            int columnCell = (int)(Math.random()*4) + 1;
            for(int i=0;i<3;i++){
                column[i] = columnCell + i;
            }
        }else{
            int lineIndex = (int)(Math.random()*5);
            for (int i=0;i<3;i++){
                line[i] = lineMap[lineIndex + i];
            }
            int columnCell = (int)(Math.random()*7) + 1;
            for(int i=0;i<3;i++){
                column[i] = columnCell;
            }
        }
        ArrayList<String> result =new ArrayList<String>();
        for (int i=0;i<3;i++){
            result.add(line[i] +"" + column[i]);
        }
        return result;
    }
    public static void main(String[] args) {
        /** For Test Boat Class */
        ArrayList<Boat> BoatList = new ArrayList<Boat>();
        Boat a = new Boat();
        BoatList.add(a);
        a.locationCells = a.getBoatLocation();
        System.out.println(a.locationCells);
    }
}
