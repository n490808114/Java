package xyz.n490808114.jdbc;

import java.util.concurrent.ConcurrentHashMap;

class PassagerManager {
    private static ConcurrentHashMap<String,Passager> passagers = new ConcurrentHashMap<>();
    private PassagerManager(){}
    static Passager getPassager(String passagerName){
        Passager passager =passagers.get(passagerName);
        if(passager!= null){
            return passager;
        }else{
            return getPassagerFromDB(passagerName);
        }
    }
    private static Passager getPassagerFromDB(String passagerName){
        Passager passager = CreateFactory.getPassager(passagerName);
        if(passager == null){
            return null;
        }else{
            passagers.put(passagerName,passager);
            return passager;
        }
    }
}
