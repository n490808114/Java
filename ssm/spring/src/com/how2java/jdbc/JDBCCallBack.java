package com.how2java.jdbc;

import java.util.LinkedList;

public interface JDBCCallBack {
    int EXECUTE = 0;
    int EXECUTE_QUERY = 1;
    int EXECUTE_UPDATE = 2;
    default void onResult(Boolean b){}
    default void onResult(int i){}
    default void onResult(LinkedList<String[]> list){}

}
