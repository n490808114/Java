import java.util.Scanner;

/* class SimpleBoatTestDrive{
    public static void main(String[] args) {
        SimpleBoat boat1 = new SimpleBoat();
        int[] locationCells = {2,3,4};
        boat1.setlocationCells(locationCells);
        String userGuess = "4";
        String result = boat1.checkresult(userGuess);
        String testResult = "false";
        if(result=="HIT"){
            testResult = "pass";
        }
        System.out.println(testResult);
    }
} */
class SimpleBoat{
    private int[] locationCells;
    private int numOfHits = 0;

    public int gotHit(int num){
        /** 获取用户输入的位置，并返回打击的结果 */
        numOfHits = numOfHits + num;
        return numOfHits;
    }
    public String checkresult(String userGuess){
        int guess = Integer.parseInt(userGuess);
        String result = "MISS";
        for (int cell :locationCells){
            if (cell == guess){
                result = "HIT";
                numOfHits ++;
                break;
            }
        }
        if(numOfHits == locationCells.length){
            result = "KILL";
        }
        return result;
    }
    public void setLocationCells(int[] alocationCells){
        /** 设置位置 */
        locationCells = alocationCells;
    }
    public int[] getLocationCells(){
        return locationCells;
    }
    public int getNumOfHits(){
        return numOfHits;
    }
    public void changeNumOfHits(int x){
        numOfHits = numOfHits + x;
    }
}

class SimpleBoatGame{
    public static void main(String[] args) {
        int numOfGuesses =0;//guess number from user input
        int riverLength = 7;
        int boatLength = 3;
        int aLocationCellstartNum = (int) (Math.random()*(riverLength-boatLength));
        int[] aLocationCell = {aLocationCellstartNum,(aLocationCellstartNum+1),(aLocationCellstartNum+2)};
        System.out.println(Integer.toString(aLocationCellstartNum));
        SimpleBoat boat01 = new SimpleBoat();
        boat01.setLocationCells(aLocationCell);
        
        boolean isAlive = true;

        while(isAlive){
            System.out.println("Please input a number for guess:");
            
            Scanner getUserInput = new Scanner(System.in);
            String userGuess = getUserInput.next();
            getUserInput.close();

            int [] userGuessRightList = new int[3];
            for (int each : userGuessRightList){
                if ((each == 0){
                    each = Integer.parseInt(userGuess);
                    break;
                }
            }
            String result = boat01.checkresult(userGuess);
            
            System.out.println(result);
            if (result == "KILL"){
                isAlive = false;
            }
            numOfGuesses ++;
            if(numOfGuesses == 10){
                break;
            }
        }
        System.out.println("You guessed " + Integer.toString(numOfGuesses)+ " times!");
    }
}