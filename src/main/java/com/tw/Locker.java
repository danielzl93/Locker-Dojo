package com.tw;

import com.tw.exception.IncompatibleTicketTypeException;
import com.tw.exception.InvalidTicketException;
import com.tw.exception.LockerIsFullException;

import java.util.HashMap;
import java.util.Map;

public class Locker implements Storable {
    private final Size size;
    private final Map<Ticket, Bag> ticketPackageMap = new HashMap<>();

    public Locker(Size size) {
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
        if (ticket.getSize() != this.size) {
            throw new IncompatibleTicketTypeException();
        }

        if (containTicket(ticket)) {
            return ticketPackageMap.remove(ticket);
        }
        throw new InvalidTicketException();

    }

    public Size getSize() {
        return size;
    }

    @Override
    public boolean isFull() {
        return ticketPackageMap.size() == this.size.capacity;
    }

    @Override
    public boolean containTicket(Ticket ticket) {
        return ticketPackageMap.containsKey(ticket);
    }

    public boolean containBag(Bag bag) {
        return ticketPackageMap.containsValue(bag);
    }

    public double getIdealRate() {
        double freeCapacity = this.size.capacity - ticketPackageMap.size();
        return  freeCapacity / this.size.capacity;
    }
}
