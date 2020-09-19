package com.tw.robot;

import com.tw.Bag;
import com.tw.Locker;
import com.tw.Storable;
import com.tw.Ticket;
import com.tw.exception.IncompatibleTicketTypeException;
import com.tw.exception.InvalidTicketException;

import java.util.List;

public abstract class LockerRobot implements Storable {
    final List<Locker> lockers;

    LockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    @Override
    public abstract Ticket store(Bag bag);

    @Override
    public Bag pickup(Ticket ticket) {
        for (Locker locker : lockers) {
            if (ticket.getSize() != locker.getSize()) {
                throw new IncompatibleTicketTypeException();
            }

            if (locker.containTicket(ticket)) {
                return locker.pickup(ticket);
            }
        }
        throw new InvalidTicketException();
    }

    @Override
    public boolean isFull() {
        return lockers.stream().allMatch(Locker::isFull);
    }

    public List<Locker> getLockers() {
        return lockers;
    }

    @Override
    public boolean containTicket(Ticket ticket) {
        return lockers.stream().anyMatch(locker -> locker.containTicket(ticket));
    }
}
