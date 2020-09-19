package com.tw;

import com.tw.exception.InvalidTicketException;
import com.tw.exception.LockerIsFullException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class LockerTest {
    @Test
    public void should_return_ticket_and_save_to_small_locker_when_locker_save_bag_given_locker_has_available_capacity_and_small_bag() {

        Locker locker = new Locker(10, Size.SMALL);

        Ticket ticket = locker.store(new Bag(Size.SMALL));

        assertNotNull(ticket);
    }

    @Test(expected = LockerIsFullException.class)
    public void should_throw_full_when_locker_save_bag_given_locker_has_no_available_capacity_and_small_bag() {

        Locker locker = new Locker(1, Size.SMALL);
        locker.store(new Bag(Size.SMALL));
        locker.store(new Bag(Size.SMALL));

    }
}
