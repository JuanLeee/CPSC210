package ca.ubc.cs.cpsc210.snake.tests;

import ca.ubc.cs.cpsc210.snake.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// jUnit tests for Snake class
public class SnakeTest {
    private Snake testSnake;

    @BeforeEach
    public void runBefore() {
        testSnake = new Snake(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2));
    }

    // TODO: complete the unit tests for the Snake class

    @Test
    public void testConstructor() {
        assertEquals(1, testSnake.length());
        assertFalse(testSnake.canGrow());
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2),testSnake.getPosition());
        assertEquals(Direction.RIGHT,testSnake.getDirection());
    }

    @Test
    public void testFullTurnLeftTurns(){
        setSnakeDirection(testSnake,Direction.DOWN);
        assertEquals(Direction.DOWN,testSnake.getDirection());
        setSnakeDirection(testSnake,Direction.RIGHT);
        assertEquals(Direction.RIGHT,testSnake.getDirection());
    }

    @Test
    public void testFullTurnRightTurns(){
        setSnakeDirectionTurnsRight(testSnake,Direction.DOWN);
        assertEquals(Direction.DOWN,testSnake.getDirection());
        setSnakeDirectionTurnsRight(testSnake,Direction.LEFT);
        assertEquals(Direction.LEFT,testSnake.getDirection());
        setSnakeDirectionTurnsRight(testSnake,Direction.UP);
        assertEquals(Direction.UP,testSnake.getDirection());
        setSnakeDirectionTurnsRight(testSnake,Direction.RIGHT);
        assertEquals(Direction.RIGHT,testSnake.getDirection());
    }

    @Test
    public void testMoveRight() {
        setSnakeDirection(testSnake, Direction.RIGHT);
        testSnake.move();
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2 + 1), testSnake.getPosition());
    }

    @Test
    public void testMoveLeft() {
        setSnakeDirection(testSnake, Direction.LEFT);
        testSnake.move();
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2 - 1), testSnake.getPosition());
    }

    @Test
    public void testMoveUp() {
        setSnakeDirection(testSnake, Direction.UP);
        testSnake.move();
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2 - 1, SnakeGame.BOARD_COLS / 2), testSnake.getPosition());
    }

    @Test
    public void testMoveDown() {
        setSnakeDirection(testSnake, Direction.DOWN);
        testSnake.move();
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2 + 1, SnakeGame.BOARD_COLS / 2), testSnake.getPosition());
    }

    @Test
    public void testMoveBody() {
        setSnakeDirection(testSnake, Direction.RIGHT);
        growBodyByTwo();
        testSnake.move();
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2 + 3), testSnake.getPosition());

        List<Cell> body = testSnake.getBodyPositions();
        assertEquals(2, body.size());
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2 + 2), body.get(0));
        assertEquals(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2 + 1), body.get(1));
    }

    @Test
    public void testGrowOnFeed() {
        Food food = new Food(testSnake.getPosition(), Snake.NUTRITION_TO_GROW);
        testSnake.eat(food);
        assertEquals(1, testSnake.length());
        assertTrue(testSnake.canGrow());

        testSnake.move();
        assertEquals(2, testSnake.length());
        assertFalse(testSnake.canGrow());
    }

    //Task M5: this test fails when bug introduced to Snake.eat
    @Test
    public void testEatMultiple(){
        Food food = new Food(testSnake.getPosition(), Snake.NUTRITION_TO_GROW/5);
        testSnake.eat(food);
        assertFalse(testSnake.canGrow());
        assertEquals(1, testSnake.length());
        testSnake.move();
        for(int i = 0; i < 5; i++) {
            testSnake.eat(food);
        }
        assertTrue(testSnake.canGrow());
        assertEquals(1, testSnake.length());
        testSnake.move();
        assertEquals(2, testSnake.length());
        assertFalse(testSnake.canGrow());

    }

    // EFFECTS: rotate snake until it is facing in direction d
    private void setSnakeDirection(Snake snake, Direction d) {
        while (snake.getDirection() != d)
            snake.rotateLeft();
    }

    // EFFECTS: rotate snake until it is facing in direction d
    private void setSnakeDirectionTurnsRight(Snake snake, Direction d) {
        while (snake.getDirection() != d)
            snake.rotateRight();
    }

    // MODIFIES: this
    // EFFECTS:  get snake to eat enough food so that its body has length 2
    private void growBodyByTwo() {
        Food food = new Food(testSnake.getPosition(), 2 * Snake.NUTRITION_TO_GROW);
        testSnake.eat(food);
        testSnake.move();
        testSnake.move();
    }
}