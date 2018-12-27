class MixFor5{
    public static void main(String[] args) {
        int x = 0;
        int y = 30;
        for(int outer=0;outer<3;outer++){
            for (int inter=4;inter>1;inter--){
                x = x+0;
                System.out.println("000 "+x + " " + y);
                y=y-2;
                System.out.println("111 "+x + " " + y);
                if(x==6){
                    break;//break 挑出这个for循环 continue挑出这个循环，继续下个循环 
                }
                x = x+3;
                System.out.println("222 "+x + " " + y);
            }
            System.out.println("888 "+x + " " + y);
            y=y-2;
            System.out.println("999 "+x + " " + y);
        }
        System.out.println("Finaly "+x + " " + y);
    }
}