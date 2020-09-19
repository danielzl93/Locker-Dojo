package com.tw.robot;

import com.tw.Bag;
import com.tw.Locker;
import com.tw.Ticket;

import java.util.Comparator;
import java.util.List;

public class SuperLockerRobot extends LockerRobot {

    public SuperLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Ticket store(Bag bag) {
        return lockers.stream().max(Comparator.comparingDouble(Locker::getIdealRate)).get().store(bag);
    }
}
