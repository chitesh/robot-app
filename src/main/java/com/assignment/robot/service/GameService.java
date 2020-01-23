package com.assignment.robot.service;

import com.assignment.robot.model.Direction;
import com.assignment.robot.model.State;

import static com.assignment.robot.model.Direction.EAST;
import static com.assignment.robot.model.Direction.NORTH;
import static com.assignment.robot.model.Direction.SOUTH;
import static com.assignment.robot.model.Direction.WEST;
import static com.assignment.robot.validator.GameValidator.validateBoundary;

/**
 * Service class that performs all the operations
 */
public class GameService {

    public State place(int xCoordinate, int yCoordinate, Direction currentDirection) {
        if(!(validateBoundary(xCoordinate) && validateBoundary(yCoordinate))) {
            throw new IllegalArgumentException("Invalid coordinates provided");
        }
        return State.builder()
                .xCoordinate(xCoordinate)
                .yCoordinate(yCoordinate)
                .currentDirection(currentDirection)
                .build();
    }

    public void move(State state) {
        switch (state.getCurrentDirection()) {
            case EAST:
                incrementXCoordinate(state);
                break;
            case WEST:
                decrementXCoordinate(state);
                break;
            case NORTH:
                incrementYCoordinate(state);
                break;
            case SOUTH:
                decrementYCoordinate(state);
                break;
        }
    }

    public void left(State state) {
        switch (state.getCurrentDirection()) {
            case EAST:
                state.setCurrentDirection(NORTH);
                break;
            case WEST:
                state.setCurrentDirection(SOUTH);
                break;
            case NORTH:
                state.setCurrentDirection(WEST);
                break;
            case SOUTH:
                state.setCurrentDirection(EAST);
                break;
        }
    }

    public void right(State state) {
        switch (state.getCurrentDirection()) {
            case EAST:
                state.setCurrentDirection(SOUTH);
                break;
            case WEST:
                state.setCurrentDirection(NORTH);
                break;
            case NORTH:
                state.setCurrentDirection(EAST);
                break;
            case SOUTH:
                state.setCurrentDirection(WEST);
                break;
        }
    }

    public void report(State state) {
        System.out.println(state);
    }

    private void incrementXCoordinate(State state) {
        int coordinateIndex = state.getXCoordinate() + 1;
        setXCoordinate(state, coordinateIndex);
    }

    private void decrementXCoordinate(State state) {
        int coordinateIndex = state.getXCoordinate() - 1;
        setXCoordinate(state, coordinateIndex);
    }

    private void setXCoordinate(State state, int coordinateIndex) {
        if (validateBoundary(coordinateIndex)) {
            state.setXCoordinate(coordinateIndex);
        }
    }

    private void incrementYCoordinate(State state) {
        int coordinateIndex = state.getYCoordinate() + 1;
        setYCoordinate(state, coordinateIndex);
    }

    private void decrementYCoordinate(State state) {
        int coordinateIndex = state.getYCoordinate() - 1;
        setYCoordinate(state, coordinateIndex);
    }

    private void setYCoordinate(State state, int coordinateIndex) {
        if (validateBoundary(coordinateIndex)) {
            state.setYCoordinate(coordinateIndex);
        }
    }
}
