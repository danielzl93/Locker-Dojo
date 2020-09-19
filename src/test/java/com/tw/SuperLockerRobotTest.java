package com.tw;

import com.tw.Bag;
import com.tw.Locker;
import com.tw.Ticket;
import com.tw.exception.InvalidTicketException;
import com.tw.exception.LockerIsFullException;
import com.tw.robot.SuperLockerRobot;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class SuperLockerRobotTest {
    @Test
    public void should_return_ticket_and_save_to_first_medium_locker_when_robot_save_bag_given_locker_has_available_capacity_and_medium_bag() {
        StorableFactory factory = new StorableFactory();
        SuperLockerRobot robot = (SuperLockerRobot) factory.createStorable(Size.Large, 2);
        for (int i = 0; i < 20; i++) {
            robot.getLockers().get(0).store(new Bag(Size.Large));
        }
        for (int i = 0; i < 10; i++) {
            robot.getLockers().get(1).store(new Bag(Size.Large));
        }

        Bag expectBag = new Bag(Size.Large);
        Ticket ticket = robot.store(expectBag);

        assertNotNull(ticket);
        assertTrue(robot.getLockers().get(0).containBag(expectBag));
    }
}
