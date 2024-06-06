package com.acherepakha.elevatorsystem;

import com.acherepakha.elevatorsystem.models.Direction;
import com.acherepakha.elevatorsystem.models.Request;
import com.acherepakha.elevatorsystem.optimized.ElevatorOptimized;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ElevatorOptimizedTest {
    @Test
    public void simple() {
        List<Request> queue = new ArrayList<>();
        queue.add(new Request(Direction.UP, 2, 4));
        queue.add(new Request(Direction.UP, 3, 5));
        queue.add(new Request(Direction.DOWN, 6, 4));
        queue.add(new Request(Direction.UP, 4, 6));
        queue.add(new Request(Direction.UP, 7, 9));
        queue.add(new Request(Direction.UP, 8, 10));
        queue.add(new Request(Direction.UP, 9, 11));

        var elevator = new ElevatorOptimized(1, queue);
        elevator.start();
    }

    @Test
    public void overlapped() {
        List<Request> queue = new ArrayList<>();
        queue.add(new Request(Direction.UP, 2, 4));
        queue.add(new Request(Direction.UP, 3, 5));
        queue.add(new Request(Direction.UP, 4, 6));
        queue.add(new Request(Direction.UP, 5, 7));
        queue.add(new Request(Direction.DOWN, 6, 4));
        queue.add(new Request(Direction.UP, 6, 8));
        queue.add(new Request(Direction.UP, 7, 9));
        queue.add(new Request(Direction.UP, 8, 10));
        queue.add(new Request(Direction.UP, 9, 11));

        var elevator = new ElevatorOptimized(1, queue);
        elevator.start();
    }

    @Test
    public void severalFromTheSameFloor() {
        List<Request> queue = new ArrayList<>();
        queue.add(new Request(Direction.UP, 2, 4));
        queue.add(new Request(Direction.UP, 2, 6));
        queue.add(new Request(Direction.DOWN, 8, 4));

        var elevator = new ElevatorOptimized(1, queue);
        elevator.start();
    }
}
