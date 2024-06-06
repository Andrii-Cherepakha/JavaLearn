package com.acherepakha.elevatorsystem;

import com.acherepakha.elevatorsystem.models.Direction;
import com.acherepakha.elevatorsystem.models.Request;
import com.acherepakha.elevatorsystem.simple.ElevatorSimple;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

public class ElevatorSimpleTest {
    @Test
    public void simple() {
        List<Request> queue = new ArrayList<>();
        queue.add(new Request(Direction.UP, 2, 10));
        queue.add(new Request(Direction.UP, 3, 5));
        queue.add(new Request(Direction.DOWN, 6, 4));

        var elevator = new ElevatorSimple(1, queue);
        elevator.start();
    }

    @Test
    public void elevatorIsHigher() {
        List<Request> queue = new ArrayList<>();
        queue.add(new Request(Direction.UP, 2, 5));

        var elevator = new ElevatorSimple(10, queue);
        elevator.start();
    }
}
