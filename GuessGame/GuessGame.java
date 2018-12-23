public class GuessGame{
    Player p1;
    Player p2;
    Player p3;
    public void startgame(){
        p1 = new Player();
        p2 = new Player();
        p3 = new Player();

        int guessp1 = 0;
        int guessp2 = 0;
        int guessp3 = 0;

        boolean p1isRight = false;
        boolean p2isRight = false;
        boolean p3isRight = false;

        int targeNumber = (int) (Math.random() * 10);
        System.out.println("I`m thinking of a number between 0 and 9...");

        while(true){
            System.out.println("Number to guess is " + targeNumber);

            p1.guess();
            p2.guess();
            p3.guess();

            guessp1 = p1.number;
            System.out.println("Player ONE guessed " + guessp1);
            guessp2 = p2.number;
            System.out.println("Player TWO guessed " + guessp2);
            guessp3 = p3.number;
            System.out.println("Player THREE guessed " + guessp3);

            if(guessp1 == targeNumber){
                p1isRight = true;
            }
            if(guessp2 == targeNumber){
                p2isRight = true;
            }
            if(guessp3 == targeNumber){
                p3isRight = true;
            }

            if (p1isRight || p2isRight || p3isRight){
                System.out.println("We hava a winner!");
                System.out.println("Player ONE got it right? "+ p1isRight);
                System.out.println("Player TWO got it right? "+ p2isRight);
                System.out.println("Player THREE got it right? "+ p3isRight);
                System.out.println("Game is over.");
                break;
            }else{
                System.out.println("Players will have to try again.");
            }
    }
}}