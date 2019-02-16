package com.account.model;

import java.util.ArrayList;

public class AccountDB {
    ArrayList<Account> accounts = new ArrayList<>();

    public void add(String userName,String password)
            throws ArrayIndexOutOfBoundsException{
        if (!check(userName,password)){
            throw new ArrayIndexOutOfBoundsException();
        }
        accounts.add(new Account(userName, password));
    }
    public Boolean check(String userName,String password){

        return accounts.contains(new Account(userName, password));
    }
}

class Account{
    String userName;
    String password;
    Account(String userName,String password){
        this.userName = userName;
        this.password = password;
    }
}
