package com.how2java.jdbc;

import java.util.LinkedList;

public interface JDBCgetResult {
    default void onResult(Boolean b){}
    default void onResult(int i){}
    default void onResult(LinkedList<String[]> list){}
}
