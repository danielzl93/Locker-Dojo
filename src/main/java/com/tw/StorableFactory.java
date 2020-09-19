package com.tw;

public class StorableFactory {
    public Storable createStorable(Size size){

        switch (size){
            case Large:
                return new Locker(Size.SMALL);
            case MEDIUM:
                return new Locker(Size.SMALL);
            case SMALL:
                return new Locker(Size.SMALL);
        }
        return null;
    }
}
