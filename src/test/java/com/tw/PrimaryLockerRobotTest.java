package com.tw;

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
}
