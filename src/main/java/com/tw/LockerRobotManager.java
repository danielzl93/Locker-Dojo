package com.tw;

import com.tw.exception.InvalidTicketException;
import com.tw.exception.LockerIsFullException;

import java.util.List;

public class LockerRobotManager {

//    private final List<Storable> storables;
//
//    public LockerRobotManager(List<Storable> storables) {
//        this.storables = storables;
//    }
//
//    public Ticket save(Bag bag) {
//        for (Storable storable : storables) {
//            if (!storable.isFull()) {
//                return storable.save(bag);
//            }
//        }
//
//        throw new LockerIsFullException();
//    }
//
//    public Bag pickUp(Ticket ticket) {
//        for (Storable storable : storables) {
//            if (storable.contains(ticket)) {
//                return storable.pickUp(ticket);
//            }
//        }
//        throw new InvalidTicketException();
//    }
}
