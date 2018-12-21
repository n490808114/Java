class MyClass{
    public static void main(String[] args) {
        int x = 0;
        while(x<4){
            /* x = x - 1 ; */
            System.out.print("a");
            if(x<1){
                System.out.print(" ");
            }
            System.out.print("n");
            if(x<1){
                System.out.println("noise");
            }
            if(x==1){
                x = x + 2;
                System.out.println("noys");
            }
            if(x>1){
                System.out.println(" oyster");
            }
            x = x + 1;
        }
    }}