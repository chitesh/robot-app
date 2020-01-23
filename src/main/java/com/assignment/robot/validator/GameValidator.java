package com.assignment.robot.validator;

/**
 * Validator class for Game
 */
public class GameValidator {
    public static boolean validateBoundary(int position) {
        if (!(position >= 0 && position <= 4)) {
            System.out.println("Move discarded.. was about to fall");
            return false;
        }
        return true;
    }
}
