package com.tw;

import com.tw.exception.InvalidTicketException;
import com.tw.exception.LockerIsFullException;
import com.tw.robot.PrimaryLockerRobot;
import com.tw.robot.SuperLockerRobot;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class LockerRobotManager {
    private final List<Storable> storables;

    public LockerRobotManager(List<Storable> storables) {
        this.storables = storables;
    }

    public Ticket store(Bag bag) {
        switch (bag.getSize()){
            case Large:
                Optional<Storable> optionalStorable = storables.stream()
                        .filter(storable -> storable instanceof SuperLockerRobot && !storable.isFull())
                        .findFirst();
                if (optionalStorable.isPresent()) {
                    return optionalStorable.get().store(bag);
                }
                break;
            case MEDIUM:
                optionalStorable = storables.stream()
                        .filter(storable -> storable instanceof PrimaryLockerRobot && !storable.isFull())
                        .findFirst();
                if (optionalStorable.isPresent()) {
                    return optionalStorable.get().store(bag);
                }
                break;
            case SMALL:
                optionalStorable = storables.stream()
                        .filter(storable -> storable instanceof Locker && !storable.isFull())
                        .findFirst();
                if (optionalStorable.isPresent()) {
                    return optionalStorable.get().store(bag);
                }
                break;
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

    public List<Storable> getStorables() {
        return storables;
    }
}
