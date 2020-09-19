package com.tw;

import com.tw.robot.PrimaryLockerRobot;
import com.tw.robot.SuperLockerRobot;

import java.util.ArrayList;
import java.util.List;

public class StorableFactory {
    public Storable createStorable(Size size, int numberOfLockers){
        List<Locker> lockers = new ArrayList<>();
        for (int i = 0; i < numberOfLockers; i++) {
            lockers.add(new Locker(size));
        }
        switch (size){
            case Large:
                return new SuperLockerRobot(lockers);
            case MEDIUM:
                return new PrimaryLockerRobot(lockers);
            case SMALL:
                return createStorable(size);
        }
        return null;
    }

    public Storable createStorable(Size size) {
        return new Locker(size);
    }

    public LockerRobotManager createManager(){
        Storable locker = createStorable(Size.SMALL);
        Storable primaryRobot = createStorable(Size.MEDIUM, 2);
        Storable superRobot = createStorable(Size.Large, 2);

        List<Storable> storables = new ArrayList<>();
        storables.add(locker);
        storables.add(primaryRobot);
        storables.add(superRobot);

        return new LockerRobotManager(storables);
    }
}
