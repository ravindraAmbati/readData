package com.bigbasket.readData.service;

import java.util.List;

public interface Service<T> {

    List<T> create();

    String drop();

    List<T> readAll();
}
