package com.tw;

import com.tw.exception.InvalidTicketException;
import com.tw.exception.LockerIsFullException;

import java.lang.annotation.IncompleteAnnotationException;
import java.util.HashMap;
import java.util.Map;

enum Size {
    SMALL,
    MEDIUM,
    Large
}

public class Locker implements Storable {
    private final int capacity;
    private final Size size;
    private final Map<Ticket, Bag> ticketPackageMap = new HashMap<>();

    public Locker(int capacity, Size size) {
        this.capacity = capacity;
        this.size = size;
    }

    @Override
    public Ticket store(Bag bag) {
        if (isFull()) {
            throw new LockerIsFullException();
        }
        Ticket ticket = new Ticket(this.size);
        ticketPackageMap.put(ticket, bag);
        return ticket;
    }

    @Override
    public Bag pickup(Ticket ticket) {

        if (ticketPackageMap.containsKey(ticket)) {
            return ticketPackageMap.remove(ticket);
        }
        throw new InvalidTicketException();

    }

    @Override
    public boolean isFull() {
        return ticketPackageMap.size() == this.capacity;
    }
}
