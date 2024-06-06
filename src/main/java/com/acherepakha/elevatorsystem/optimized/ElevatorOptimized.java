package com.acherepakha.elevatorsystem.optimized;

import com.acherepakha.elevatorsystem.models.Direction;
import com.acherepakha.elevatorsystem.models.Request;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class ElevatorOptimized {
    private int currentFloor;
    private final List<Request> queue; // global queue with external requests
    private final Queue<Request> movingQueue = new PriorityQueue<>(REQUEST_FLOOR_COMPARATOR);

    private static final Comparator<Request> REQUEST_FLOOR_COMPARATOR = Comparator.comparing(Request::getFloor);

    public ElevatorOptimized(int floor, List<Request> queue) {
        currentFloor = floor;
        this.queue = queue;
        System.out.println("Elevator is on " + floor + " floor.");
    }

    public void start() {
        while (!queue.isEmpty()) {
            var request = queue.getFirst();
            queue.removeFirst();
            movingQueue.add(request);
            // use == to make a movement even if on the same floor
            if (request.getDirection() == Direction.UP) {
                // where is the elevator? higher or lower the requested floor?
                // if it's higher, it should first move down
                while (currentFloor > request.getFloor()) {
                    System.out.println("Moving on " + currentFloor + " floor.");
                    currentFloor--;
                }
                moveUp();
            } else {
                // where is the elevator? higher or lower the requested floor?
                // if it's lower, it should first move up
                while (currentFloor < request.getFloor()) {
                    System.out.println("Moving on " + currentFloor + " floor.");
                    currentFloor++;
                }
                moveDown();
            }
        }
    }

    private void moveUp() {
        while (!movingQueue.isEmpty()) {
            System.out.println("Moving on " + currentFloor + " floor.");
            movingQueue.addAll(getSimilarRequests(Direction.UP)); // update movingQueue with items from the global queue
            while (!movingQueue.isEmpty() && movingQueue.peek().getFloor() == currentFloor) {
                var request = movingQueue.poll();
                System.out.println("Stopped on " + currentFloor + " for " + request.getId());
                if (request.getDestination() != null) {
                    movingQueue.add(request.getDestination());
                }
            }
            if (!movingQueue.isEmpty()) {
                currentFloor++;
            }
        }
    }

    private void moveDown() {
        while (!movingQueue.isEmpty()) {
            System.out.println("Moving on " + currentFloor + " floor.");
            movingQueue.addAll(getSimilarRequests(Direction.DOWN)); // update movingQueue with items from global queue
            while (!movingQueue.isEmpty() && movingQueue.peek().getFloor() == currentFloor) {
                var request = movingQueue.poll();
                System.out.println("Stopped on " + currentFloor + " for " + request.getId());
                if (request.getDestination() != null) {
                    movingQueue.add(request.getDestination());
                }
            }
            if (!movingQueue.isEmpty()) {
                currentFloor--;
            }
        }
    }

    private List<Request> getSimilarRequests(Direction direction) {
        // return empty list if threshold exceeded?
        // what if the elevator stopped to drop off?
        var rs = queue.stream()
                .filter(r -> r.getDirection() == direction && r.getFloor() == currentFloor)
                .toList();
        queue.removeAll(rs);
        return rs;
    }
}
