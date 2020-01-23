package com.assignment.robot.service;

import com.assignment.robot.model.Direction;
import com.assignment.robot.model.State;
import org.junit.Before;
import org.junit.Test;

import static com.assignment.robot.model.Direction.EAST;
import static com.assignment.robot.model.Direction.NORTH;
import static com.assignment.robot.model.Direction.SOUTH;
import static com.assignment.robot.model.Direction.WEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class GameServiceTest {

    private GameService sut;
    private State state;

    @Before
    public void setUp() {
        state = createState(0, 0, NORTH);
        sut = new GameService();
    }

    private State createState(int xCoordinate, int yCoordinate, Direction direction) {
        return State.builder()
                .xCoordinate(xCoordinate)
                .yCoordinate(yCoordinate)
                .currentDirection(direction)
                .build();
    }

    @Test
    public void placeSuccess() {
        State expectedState = State.builder().xCoordinate(0).yCoordinate(0).currentDirection(WEST).build();
        State state = sut.place(0, 0, WEST);
        assertThat(state).isNotNull().isEqualTo(expectedState);
    }

    @Test
    public void placeOutOfBoundaryMinXCoordinate() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> sut.place(-1, 0, WEST))
                .withMessageContaining("Invalid coordinates provided")
                .withNoCause();
    }

    @Test
    public void placeOutOfBoundaryMaxCoordinate() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> sut.place(5, 0, WEST))
                .withMessageContaining("Invalid coordinates provided")
                .withNoCause();
    }

    @Test
    public void placeOutOfBoundaryMinYCoordinate() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> sut.place(0, -1, WEST))
                .withMessageContaining("Invalid coordinates provided")
                .withNoCause();
    }

    @Test
    public void placeOutOfBoundaryMaxYCoordinate() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> sut.place(0, 5, WEST))
                .withMessageContaining("Invalid coordinates provided")
                .withNoCause();
    }

    @Test
    public void endToEnd() {
        State expectedState;
        State state = sut.place(0, 0, NORTH);
        sut.move(state);
        expectedState = State.builder().xCoordinate(0).yCoordinate(1).currentDirection(NORTH).build();
        assertThat(state).isEqualTo(expectedState);

        state = sut.place(0, 0, NORTH);
        sut.left(state);
        expectedState = State.builder().xCoordinate(0).yCoordinate(0).currentDirection(WEST).build();
        assertThat(state).isEqualTo(expectedState);

        state = sut.place(1, 2, EAST);
        sut.move(state);
        sut.move(state);
        sut.left(state);
        sut.move(state);
        expectedState = State.builder().xCoordinate(3).yCoordinate(3).currentDirection(NORTH).build();
        assertThat(state).isEqualTo(expectedState);
    }

    @Test
    public void moveSuccess() {
        State expectedState = State.builder()
                .xCoordinate(1)
                .yCoordinate(0)
                .currentDirection(NORTH)
                .build();
        sut.move(state);
        assertThat(state).isNotNull().isEqualTo(expectedState);
    }

    @Test
    public void moveBoundaryConditionAtSouthWestFacingWest() {
        moveAndAssert(0, 0, WEST);
    }

    @Test
    public void moveBoundaryConditionAtSouthWestFacingSouth() {
        moveAndAssert(0, 0, SOUTH);
    }

    @Test
    public void moveBoundaryConditionAtNorthWestFacingNorth() {
        moveAndAssert(4, 0, NORTH);
    }

    @Test
    public void moveBoundaryConditionAtNorthWestFacingWest() {
        moveAndAssert(4, 0, WEST);
    }

    @Test
    public void moveBoundaryConditionAtSouthEastFacingSouth() {
        moveAndAssert(0, 4, SOUTH);
    }

    @Test
    public void moveBoundaryConditionAtSouthEastFacingEast() {
        moveAndAssert(0, 4, EAST);
    }

    @Test
    public void moveBoundaryConditionAtNorthEastFacingNorth() {
        moveAndAssert(4, 4, NORTH);
    }

    @Test
    public void moveBoundaryConditionAtNorthEastFacingEast() {
        moveAndAssert(4, 4, EAST);
    }

    private void moveAndAssert(int xCoordinate, int yCoordinate, Direction direction) {
        State expectedState = createState(xCoordinate, yCoordinate, direction);
        state = createState(xCoordinate, yCoordinate, direction);
        sut.move(state);
        assertThat(state).isNotNull().isEqualTo(expectedState);
    }

    @Test
    public void leftAtNorth() {
        turnAndAssert(NORTH, WEST, true);
    }

    @Test
    public void leftAtWest() {
        turnAndAssert(WEST, SOUTH, true);
    }

    @Test
    public void leftAtSouth() {
        turnAndAssert(SOUTH, EAST, true);
    }

    @Test
    public void leftAtEast() {
        turnAndAssert(EAST, NORTH, true);
    }

    @Test
    public void rightAtNorth() {
        turnAndAssert(NORTH, EAST, false);
    }

    @Test
    public void rightAtWest() {
        turnAndAssert(WEST, NORTH, false);
    }

    @Test
    public void rightAtSouth() {
        turnAndAssert(SOUTH, WEST, false);
    }

    @Test
    public void rightAtEast() {
        turnAndAssert(EAST, SOUTH, false);
    }

    private void turnAndAssert(Direction direction, Direction expectedDirection, boolean isLeft) {
        State expectedState = createState(0, 0, expectedDirection);
        state = createState(0, 0, direction);
        if (isLeft) {
            sut.left(state);
        } else {
            sut.right(state);
        }
        assertThat(state).isNotNull().isEqualTo(expectedState);
    }
}