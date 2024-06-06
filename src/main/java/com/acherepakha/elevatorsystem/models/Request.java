package com.acherepakha.elevatorsystem.models;

import java.time.LocalDateTime;

// Should implement java.lang.Comparable to use with Queue ?
public class Request {
    private final Direction direction;
    private final int floor;
    private final String id;
    private final Request destination; // for testing purpose

    // initial request - a person pressed UP/DOWN button on some floor
    // destination request - the person entered the elevator and pressed a number

    // initial request
    public Request(Direction direction, int floor, int destination) {
        if (direction == Direction.UP && floor >= destination) {
            throw new IllegalArgumentException("FROM cannot be less than TO for UP direction.");
        }

        if (direction == Direction.DOWN && destination >= floor) {
            throw new IllegalArgumentException("FROM cannot be greater than TO for DOWN direction.");
        }

        id = direction + "-" + floor + "-" + destination;
        this.direction = direction;
        this.floor = floor;
        this.destination = new Request(direction, destination, id);
    }

    // destination request
    private Request(Direction direction, int floor, String id) {
        this.id = id;
        this.direction = direction;
        this.floor = floor;
        this.destination = null;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getFloor() {
        return floor;
    }

    public Request getDestination() {
        return destination;
    }

    public String getId() {
        return id;
    }
}
