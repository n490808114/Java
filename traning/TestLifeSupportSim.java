import java.util.*;

class V2Radiator{
    V2Radiator(ArrayList list){
        for(int x=0;x<5;x++){
            list.add(new SimUnit("V2Radiator"));
        }
    }
}
class V3Radiator extends V2Radiator{
    V3Radiator(ArrayList lglist){
        super(lglist);
        for(int g=0;g<10;g++){
            lglist.add(new SimUnit("V3Radiator"));
        }
    }
}
class RetentionBot{
    RetentionBot(ArrayList rlist){
        rlist.add(new SimUnit("Retention"));
    }
}
public class TestLifeSupportSim{
    public static void main(String[] args) {
        ArrayList aList = new ArrayList();
        V2Radiator v2 = new V2Radiator(aList);
        V3Radiator v3 = new V3Radiator(aList);
        for (int z=0;z<20;z++){
            RetentionBot ret = new RetentionBot(aList);
        }
    }
}
class SimUnit{
    String botType;
    SimUnit(String type){ //引用要带入bottype
        botType = type;
    }
    int power(){   //power()方法返回结果，等于返回2，不等于返回2
        if("Retention".equals(botType)){
            return 2;
        }else{
            return 1;
        }
    }
}