package com.tw;

import com.tw.exception.InvalidTicketException;
import com.tw.exception.LockerIsFullException;
import com.tw.robot.PrimaryLockerRobot;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

public class LockerRobotManagerTest {
    @Test
    public void should_return_ticket_when_manager_save_bag_given_locker_and_robots_have_available_capacity() {
        StorableFactory factory = new StorableFactory();
        LockerRobotManager manager = factory.createManager();

        Bag expectBag = new Bag(Size.MEDIUM);
        Ticket ticket = manager.store(expectBag);

        assertNotNull(ticket);
    }

    @Test(expected = LockerIsFullException.class)
    public void should_throw_full_when_manager_save_bag_given_locker_full_and_small_bag() {
        StorableFactory factory = new StorableFactory();
        LockerRobotManager manager = factory.createManager();
        for (int i = 0; i < Size.SMALL.capacity; i++) {
            manager.store(new Bag(Size.SMALL));
        }
        manager.store(new Bag(Size.SMALL));

    }
}
