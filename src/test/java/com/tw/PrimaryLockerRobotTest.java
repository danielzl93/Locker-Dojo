package com.tw;

import com.tw.exception.IncompatibleTicketTypeException;
import com.tw.exception.InvalidTicketException;
import com.tw.exception.LockerIsFullException;
import com.tw.robot.PrimaryLockerRobot;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrimaryLockerRobotTest {
    @Test
    public void should_return_ticket_and_save_to_first_medium_locker_when_robot_save_bag_given_locker_has_available_capacity_and_medium_bag() {
        StorableFactory factory = new StorableFactory();
        PrimaryLockerRobot robot = (PrimaryLockerRobot) factory.createStorable(Size.MEDIUM, 2);

        Bag expectBag = new Bag(Size.MEDIUM);
        Ticket ticket = robot.store(expectBag);

        assertNotNull(ticket);
        assertTrue(robot.getLockers().get(0).containBag(expectBag));
    }

    @Test
    public void should_return_ticket_and_save_to_second_medium_locker_when_robot_save_bag_given_first_locker_full_and_medium_bag() {
        StorableFactory factory = new StorableFactory();
        PrimaryLockerRobot robot = (PrimaryLockerRobot) factory.createStorable(Size.MEDIUM, 2);

        for (int i = 0; i < Size.MEDIUM.capacity; i++) {
            robot.store(new Bag(Size.MEDIUM));
        }

        Bag expectBag = new Bag(Size.MEDIUM);
        Ticket ticket = robot.store(expectBag);

        assertNotNull(ticket);
        assertFalse(robot.getLockers().get(0).containBag(expectBag));
        assertTrue(robot.getLockers().get(1).containBag(expectBag));
    }

    @Test(expected = LockerIsFullException.class)
    public void should_throw_full_when_robot_save_bag_given_lockers_are_full() {
        StorableFactory factory = new StorableFactory();
        PrimaryLockerRobot robot = (PrimaryLockerRobot) factory.createStorable(Size.MEDIUM, 2);

        for (int i = 0; i < Size.MEDIUM.capacity * 2; i++) {
            robot.store(new Bag(Size.MEDIUM));
        }

        Bag expectBag = new Bag(Size.MEDIUM);
        robot.store(expectBag);
    }

    @Test
    public void should_return_bag_when_robot_pickup_bag_given_valid_ticket() {
        StorableFactory factory = new StorableFactory();
        PrimaryLockerRobot robot = (PrimaryLockerRobot) factory.createStorable(Size.MEDIUM, 2);

        Bag expectBag = new Bag(Size.MEDIUM);
        Ticket ticket = robot.store(expectBag);

        assertEquals(expectBag, robot.pickup(ticket));
    }

    @Test(expected = IncompatibleTicketTypeException.class)
    public void should_throw_incompatible_when_robot_pickup_bag_given_ticket_size_incompatible_not_medium() {
        StorableFactory factory = new StorableFactory();
        PrimaryLockerRobot robot = (PrimaryLockerRobot) factory.createStorable(Size.MEDIUM, 2);

        Ticket ticket = new Ticket(Size.SMALL);

        robot.pickup(ticket);
    }

    @Test(expected = InvalidTicketException.class)
    public void should_throw_invalid_ticket_when_robot_pickup_bag_given_invalid_ticket() {
        StorableFactory factory = new StorableFactory();
        PrimaryLockerRobot robot = (PrimaryLockerRobot) factory.createStorable(Size.MEDIUM, 2);

        Ticket ticket = new Ticket(Size.MEDIUM);

        robot.pickup(ticket);
    }
}
