package com.bigbasket.readData.repo;

import java.util.List;

public interface Repo<T> {

    String drop = "DB Table deleted successfully";
    String error = "ERROR";

    List<T> create();

    String drop();

    List<T> readAll();

    boolean save(T t);

    String saveAndReturnId(T t);
}
