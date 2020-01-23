package com.assignment.robot.validator;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameValidatorTest {

    private GameValidator sut;

    @Before
    public void setUp() {
        sut = new GameValidator();
    }

    @Test
    public void validateBoundarySuccess() {
        boolean result = sut.validateBoundary(3);
        assertThat(result).isTrue();
    }

    @Test
    public void validateBoundaryFailForLowerRange() {
        boolean result = sut.validateBoundary(-1);
        assertThat(result).isFalse();
    }

    @Test
    public void validateBoundaryFailForHigherRange() {
        boolean result = sut.validateBoundary(5);
        assertThat(result).isFalse();
    }
}