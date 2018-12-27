import java.util.ArrayList;
import java.util.Scanner;

public class GameLauncher{
    public static void main(String[] args) {
        String[] nameLists = {"Test1","Test2","Test3"};
        ArrayList<Boat> BoatLists = getBoatLists(nameLists);
        Scanner getUserInput = new Scanner(System.in);
        /* System.in实例化 */
        int numOfGuess = 0;
        while(!BoatLists.isEmpty()){
            /* 循环开始 */
            numOfGuess ++;
            System.out.println("Please Input Your Guess!");
            String userGuess = getUserInput.next();
            /* 获取用户输入的值 */
            String checkResult = "Miss";
            for(Boat eachBoat:BoatLists){
                checkResult = eachBoat.checkYourself(userGuess);
                /* 把判断结果存在一个变量里，下面要使用两次 */
                if (checkResult == "Hit"){
                    break;
                }else if(checkResult == "Kill"){
                    BoatLists.remove(eachBoat);
                    break;
                }
            }
            System.out.println(checkResult);
        }
        System.out.println("You guessed " + numOfGuess + " times!");
        System.out.println("You Win!");
        getUserInput.close();
    }
    static ArrayList<Boat> getBoatLists(String[] nameLists){
        int num = nameLists.length;
        ArrayList<Boat> result = new ArrayList<Boat>();
        ArrayList<String> boatLocationCellCheckList = new ArrayList<String>();
        for (int i=0;i<num;i++){
            boolean coninueWhile = true;
            Boat boatCell = new Boat();
            while (coninueWhile){
                boatCell.locationCells = boatCell.getBoatLocation();
                int isRightCount = 0;
                for (String eachLocationCell:boatCell.locationCells){
                    if (boatLocationCellCheckList.contains(eachLocationCell)){
                        break;
                    }
                    isRightCount ++;
                }
                if(isRightCount == 3){
                    for(int x=0;x<3;x++){
                        boatLocationCellCheckList.add(boatCell.locationCells.get(x));
                    }
                    coninueWhile = false;
                }
            }
            boatCell.name = nameLists[i];
            result.add(boatCell);
        }
        return result;
    }
}