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
        Optional<Storable> optionalStorable = Optional.empty();
        switch (bag.getSize()){
            case Large:
                optionalStorable = storables.stream()
                        .filter(storable -> storable instanceof SuperLockerRobot && !storable.isFull())
                        .findFirst();
                break;
            case MEDIUM:
                optionalStorable = storables.stream()
                        .filter(storable -> storable instanceof PrimaryLockerRobot && !storable.isFull())
                        .findFirst();
                break;
            case SMALL:
                optionalStorable = storables.stream()
                        .filter(storable -> storable instanceof Locker && !storable.isFull())
                        .findFirst();
                break;
        }
        if (optionalStorable.isPresent()) {
            return optionalStorable.get().store(bag);
        }

        throw new LockerIsFullException();
    }

    public Bag pickup(Ticket ticket) {
        for (Storable storable : storables) {
            if (storable.containTicket(ticket)) {
                return storable.pickup(ticket);
            }
        }
        throw new InvalidTicketException();
    }
}
