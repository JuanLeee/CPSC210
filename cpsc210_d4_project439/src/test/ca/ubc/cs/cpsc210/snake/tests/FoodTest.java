package ca.ubc.cs.cpsc210.snake.tests;

import ca.ubc.cs.cpsc210.snake.model.Cell;
import ca.ubc.cs.cpsc210.snake.model.Food;
import ca.ubc.cs.cpsc210.snake.model.SnakeGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.ubc.cs.cpsc210.snake.model.Food.DECAY_AMOUNT;
import static ca.ubc.cs.cpsc210.snake.model.Food.INITIAL_NUTRITIONAL_VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;


// jUnit tests for Food class
public class FoodTest {
    private Food testFood;
    private Food testFoodNutritionalValue;

    @BeforeEach
    public void runBefore() {
        testFood = new Food(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2));
    }

    @Test
    public void testTemplate() {
        // use this as a template for designing your tests
    }

    @Test
    public void testConstructor(){
        assertEquals(INITIAL_NUTRITIONAL_VALUE,testFood.getNutritionalValue());
        assertEquals((new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2)),testFood.getPosition());
    }

    @Test
    public void testDecayNonZero(){
        testFood.decay();
        assertEquals(INITIAL_NUTRITIONAL_VALUE-DECAY_AMOUNT,testFood.getNutritionalValue());
    }

    @Test
    public void testDecayZero(){
        for (int i = 0; i < INITIAL_NUTRITIONAL_VALUE+DECAY_AMOUNT; i = i + DECAY_AMOUNT){
            testFood.decay();
            if (i >= INITIAL_NUTRITIONAL_VALUE) {
                assertEquals(0,testFood.getNutritionalValue());
            }
            else {
                assertEquals(INITIAL_NUTRITIONAL_VALUE-i-DECAY_AMOUNT,testFood.getNutritionalValue());
            }
        }
    }

    @Test
    public void testConstructorInputNutritionalValue(){
        testFoodNutritionalValue = new Food(new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2),INITIAL_NUTRITIONAL_VALUE);
        assertEquals(INITIAL_NUTRITIONAL_VALUE,testFood.getNutritionalValue());
        assertEquals((new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2)),testFood.getPosition());
    }
}