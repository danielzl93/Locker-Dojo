package com.tw;

import com.tw.exception.InvalidTicketException;
import com.tw.exception.LockerIsFullException;

import java.util.List;

public class LockerRobotManager {
    private final List<Storable> storables;

    public LockerRobotManager(List<Storable> storables) {
        this.storables = storables;
    }

    public Ticket store(Bag bag) {
        for (Storable storable : storables) {
            if (!storable.isFull()) {
                return storable.store(bag);
            }
        }

        throw new LockerIsFullException();
    }

    public Bag pickUp(Ticket ticket) {
        for (Storable storable : storables) {
            if (storable.containTicket(ticket)) {
                return storable.pickup(ticket);
            }
        }
        throw new InvalidTicketException();
    }
}
