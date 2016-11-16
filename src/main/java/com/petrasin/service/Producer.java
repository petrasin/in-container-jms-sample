package com.petrasin.service;

import java.io.Serializable;

public interface Producer<T extends Serializable> {

    T produce();

}
