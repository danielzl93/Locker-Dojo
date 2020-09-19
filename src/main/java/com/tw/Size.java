package com.tw;

enum Size {
    SMALL(5), MEDIUM(15), Large(30);

    int capacity;
    Size(int capacity) {
        this.capacity = capacity;
    }
}
