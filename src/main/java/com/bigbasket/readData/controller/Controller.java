package com.bigbasket.readData.controller;

import java.util.List;

public interface Controller<T> {

    List<T> create();

    String drop();

    List<T> readAll();
}
