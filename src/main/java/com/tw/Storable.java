package com.tw;

public interface Storable {
    Ticket store(Bag bag);

    boolean isFull();
}
