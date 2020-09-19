package com.tw;

import com.tw.robot.PrimaryLockerRobot;

import java.util.ArrayList;
import java.util.List;

public class StorableFactory {
    public Storable createStorable(Size size, int numberOfLockers){
        List<Locker> lockers = new ArrayList<>();
        switch (size){
            case Large:
                return new Locker(Size.SMALL);
            case MEDIUM:
                for (int i = 0; i < numberOfLockers; i++) {
                    lockers.add(new Locker(size));
                }
                return new PrimaryLockerRobot(lockers);
            case SMALL:
                return createStorable(size);
        }
        return null;
    }

    public Storable createStorable(Size size) {
        return new Locker(size);
    }
}
