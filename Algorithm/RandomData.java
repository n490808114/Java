public class RandomData{
    public static int[] getList(int count,int min,int max){
        int[] list = new int[count];
        for(int i=0;i<count;i++){
            list[i]=(int)(Math.random()*(max-min)+min);
        }
        return list;
    }
}