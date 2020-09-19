package com.tw;

import com.tw.exception.IncompatibleTicketTypeException;
import com.tw.exception.InvalidTicketException;
import com.tw.exception.LockerIsFullException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LockerTest {
    @Test
    public void should_return_ticket_and_save_to_small_locker_when_locker_save_bag_given_locker_has_available_capacity_and_small_bag() {
        StorableFactory factory = new StorableFactory();
        Storable locker = factory.createStorable(Size.SMALL);

        Ticket ticket = locker.store(new Bag(Size.SMALL));

        assertNotNull(ticket);
    }

    @Test(expected = LockerIsFullException.class)
    public void should_throw_full_when_locker_save_bag_given_locker_has_no_available_capacity_and_small_bag() {
        StorableFactory factory = new StorableFactory();
        Locker locker = (Locker) factory.createStorable(Size.SMALL);

        for (int i = 0; i < locker.getSize().capacity; i++) {
            locker.store(new Bag(Size.SMALL));
        }

        locker.store(new Bag(Size.SMALL));
    }

    @Test
    public void should_return_bag_when_locker_pickup_bag_given_valid_ticket() {
        StorableFactory factory = new StorableFactory();
        Storable locker = factory.createStorable(Size.SMALL);
        Bag bag = new Bag(Size.SMALL);

        Ticket ticket = locker.store(bag);

        assertEquals(bag, locker.pickup(ticket));
    }

    @Test(expected = IncompatibleTicketTypeException.class)
    public void should_throw_incompatible_when_locker_pickup_bag_given_ticket_size_incompatible_with_locker_size() {
        StorableFactory factory = new StorableFactory();
        Storable locker = factory.createStorable(Size.SMALL);

        Ticket ticket = new Ticket(Size.Large);

        locker.pickup(ticket);
    }

    @Test(expected = InvalidTicketException.class)
    public void should_throw_invalid_ticket_when_locker_pickup_bag_given_invalid_ticket() {
        StorableFactory factory = new StorableFactory();
        Storable locker = factory.createStorable(Size.SMALL);

        Ticket ticket = new Ticket(Size.SMALL);

        locker.pickup(ticket);
    }
}
