package com.tw;

import com.tw.exception.InvalidTicketException;
import com.tw.exception.LockerIsFullException;
import org.junit.Test;
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

    @Test(expected = LockerIsFullException.class)
    public void should_throw_full_when_manager_save_bag_given_primaryRobot_locker_full_and_medium_bag() {
        StorableFactory factory = new StorableFactory();
        LockerRobotManager manager = factory.createManager();
        for (int i = 0; i < Size.MEDIUM.capacity * 2; i++) {
            manager.store(new Bag(Size.MEDIUM));
        }

        manager.store(new Bag(Size.MEDIUM));
    }

    @Test(expected = LockerIsFullException.class)
    public void should_throw_full_when_manager_save_bag_given_superRobot_locker_full_and_large_bag() {
        StorableFactory factory = new StorableFactory();
        LockerRobotManager manager = factory.createManager();
        for (int i = 0; i < Size.Large.capacity * 2; i++) {
            manager.store(new Bag(Size.Large));
        }

        manager.store(new Bag(Size.Large));
    }

    @Test
    public void should_return_bag_when_manager_pickup_bag_given_valid_ticket() {
        StorableFactory factory = new StorableFactory();
        LockerRobotManager manager = factory.createManager();

        Bag expectBag = new Bag(Size.Large);
        Ticket ticket = manager.store(expectBag);

        assertEquals(expectBag, manager.pickup(ticket));
    }

    @Test(expected = InvalidTicketException.class)
    public void should_throw_invalid_ticket_when_robot_pickup_bag_given_invalid_ticket() {
        StorableFactory factory = new StorableFactory();
        LockerRobotManager manager = factory.createManager();

        Ticket ticket = new Ticket(Size.Large);

        manager.pickup(ticket);
    }
}
