package com.assignment.robot.controller;

import com.assignment.robot.model.Direction;
import com.assignment.robot.model.State;
import com.assignment.robot.service.GameService;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static com.assignment.robot.model.Direction.EAST;
import static com.assignment.robot.model.Direction.NORTH;
import static com.assignment.robot.model.Direction.SOUTH;
import static com.assignment.robot.model.Direction.WEST;

/**
 * Main class to run the app and play
 */
public class GameController {

    private GameService gameService = new GameService();
    private State state;

    private Map<String, Direction> directionMap = new HashMap<String, Direction>() {{
        put("east", EAST);
        put("e", EAST);
        put("west", WEST);
        put("w", WEST);
        put("north", NORTH);
        put("n", NORTH);
        put("south", SOUTH);
        put("s", SOUTH);
    }};

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.startGame();
    }

    private void startGame() {
        readPlaceData();
        boolean continuePlay = true;
        while (continuePlay) {
            continuePlay = playGame();
        }
    }

    private boolean playGame() {
        Scanner command = getInputFromUser("Please enter command - Place(P), Move(M), Left(L), Right(R), Report(S), Quit(Q)");
        switch (command.next().toLowerCase()) {
            case "p":
            case "place":
                readPlaceData();
                break;
            case "m":
            case "move":
                gameService.move(state);
                break;
            case "l":
            case "left":
                gameService.left(state);
                break;
            case "r":
            case "right":
                gameService.right(state);
                break;
            case "s":
            case "report":
                gameService.report(state);
                break;
            case "q":
            case "quit":
                return false;
            default:
                break;
        }
        return true;
    }

    private void readPlaceData() {
        int xCoordinate = getNumericRangeInputFromUser("Please provide Position X between 0 and 4", 0, 4);
        int yCoordinate = getNumericRangeInputFromUser("Please provide Position Y between 0 and 4", 0, 4);
        Direction direction = getDirectionFromUser();
        state = gameService.place(xCoordinate, yCoordinate, direction);
    }

    private Direction getDirectionFromUser() {
        Scanner input;
        Direction direction;
        do {
            input = getInputFromUser("Please provide valid Direction");
            direction = directionMap.get(input.next().toLowerCase());
        } while (null == direction);
        return direction;
    }

    private Scanner getInputFromUser(String message) {
        System.out.println(message);
        return new Scanner(System.in);
    }

    private int getNumericRangeInputFromUser(String message, int min, int max) {
        int value = -1;
        do {
            try {
                System.out.println(message);
                value = new Scanner(System.in).nextInt();
                if (value < min || value > max) {
                    value = -1;
                }
            } catch (InputMismatchException e) {
                //Do nothing
            }
        } while (value == -1);
        return value;
    }
}
