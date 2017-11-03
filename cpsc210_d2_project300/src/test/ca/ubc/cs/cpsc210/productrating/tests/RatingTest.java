package ca.ubc.cs.cpsc210.productrating.tests;

import ca.ubc.cs.cpsc210.productrating.model.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


// unit tests for the Rating classs
public class RatingTest {
    //TODO: design unit tests for Rating class
    Rating testRating;

    @BeforeEach
    void runBefore(){
        testRating = new Rating();
    }

    @Test
    void testConstructor(){
        assertEquals(0, testRating.getNumVotes());
        assertEquals(0.0, testRating.computeScore());
    }

    @Test
    void testresetVotes(){
            assertEquals(0, testRating.getNumVotes());
            assertEquals(0.0, testRating.computeScore());
            testRating.addVote(0);
            assertEquals(1, testRating.getNumVotes());
            assertEquals(0, testRating.computeScore());
            testRating.resetVotes();
            assertEquals(0, testRating.getNumVotes());
            assertEquals(0.0, testRating.computeScore());

    }

    @Test
    void testaddVote (){
        assertEquals(0, testRating.getNumVotes());
        assertEquals(0.0, testRating.computeScore());
        testRating.addVote(1);
        assertEquals(1, testRating.getNumVotes());
        assertEquals(1.0, testRating.computeScore());
        testRating.addVote(1);
        assertEquals(2, testRating.getNumVotes());
        assertEquals(1.0, testRating.computeScore());
        testRating.addVote(3);
        assertEquals(3, testRating.getNumVotes());
        assertEquals((5.0 / 3.0), testRating.computeScore());
    }

    @Test
    void testcomputeScore(){
        assertEquals(0, testRating.getNumVotes());
        assertEquals(0.0, testRating.computeScore());
        testRating.addVote(1);
        assertEquals(1, testRating.getNumVotes());
        assertEquals(1.0, testRating.computeScore());
        testRating.addVote(0);
        assertEquals(2, testRating.getNumVotes());
        assertEquals(0.5, testRating.computeScore());
        testRating.addVote(2);
        assertEquals(3, testRating.getNumVotes());
        assertEquals(1, testRating.computeScore());
        testRating.addVote(3);
        assertEquals(4, testRating.getNumVotes());
        assertEquals(6.0/4.0, testRating.computeScore());
    }

    @Test
    void testtoString(){
        testRating.addVote(0);
        assertEquals("",testRating.toString());
        testRating.addVote(1);
        assertEquals("",testRating.toString());
        testRating.resetVotes();
        testRating.addVote(1);
        assertEquals("*",testRating.toString());
        testRating.addVote(2);
        assertEquals("*",testRating.toString());
        testRating.resetVotes();
        testRating.addVote(2);
        assertEquals("**",testRating.toString());
        testRating.addVote(3);
        assertEquals("**",testRating.toString());
        testRating.resetVotes();
        testRating.addVote(3);
        assertEquals("***",testRating.toString());
    }


}