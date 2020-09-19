package com.tw;

public class Ticket {
    private final Size size;
    public Ticket(Size size) {
        this.size = size;
    }

    public Size getSize() {
        return size;
    }
}
