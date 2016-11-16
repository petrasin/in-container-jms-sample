package com.petrasin.service;

public interface Consumer<T> {

    void consume(T msg);

}
