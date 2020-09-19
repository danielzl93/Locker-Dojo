package com.tw;

public interface Storable {
    Ticket store(Bag bag);

    boolean isFull();

    Bag pickup(Ticket ticket);

    boolean containTicket(Ticket ticket);
}
