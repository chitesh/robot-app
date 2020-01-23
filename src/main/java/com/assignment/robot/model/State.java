package com.assignment.robot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Class to hold the current state of Robot
 */
@Data
@Builder
@AllArgsConstructor
public class State {
    private int xCoordinate;
    private int yCoordinate;
    private Direction currentDirection;
}
