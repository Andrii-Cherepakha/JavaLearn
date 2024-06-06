package com.acherepakha.elevatorsystem.simple;

import com.acherepakha.elevatorsystem.models.Request;

import java.util.List;

public class ElevatorSimple {
    private int currentFloor;
    private final List<Request> queue;

    public ElevatorSimple(int floor, List<Request> queue) {
        currentFloor = floor;
        this.queue = queue;
        System.out.println("Elevator is on " + floor + " floor.");
    }

    public void start() {
        while (!queue.isEmpty()) {
            var request = queue.getFirst();
            queue.removeFirst();
            processRequest(request);
        }
    }

    private void processRequest(Request request) {
        if (request.getFloor() > currentFloor) {
            moveUp(request.getFloor());
        } else if (request.getFloor() < currentFloor) {
            moveDown(request.getFloor());
        }
        System.out.println("Stopped on " + currentFloor + " for " + request.getId());
        if (request.getDestination() != null) {
            processRequest(request.getDestination());
        }
    }

    private void moveUp(int floor) {
        while(floor > currentFloor) {
            currentFloor++;
            System.out.println("Moving on " + currentFloor + " floor.");
        }
    }

    private void moveDown(int floor) {
        while(floor < currentFloor) {
            currentFloor--;
            System.out.println("Moving on " + currentFloor + " floor.");
        }
    }
}
