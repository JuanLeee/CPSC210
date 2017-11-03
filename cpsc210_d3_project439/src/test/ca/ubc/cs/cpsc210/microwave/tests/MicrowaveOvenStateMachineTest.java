package ca.ubc.cs.cpsc210.microwave.tests;

import ca.ubc.cs.cpsc210.microwave.model.MicrowaveOvenState;
import ca.ubc.cs.cpsc210.microwave.model.MicrowaveOvenStateMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// unit tests for MicrowaveOvenStateMachine
public class MicrowaveOvenStateMachineTest {

    private MicrowaveOvenStateMachine testOven;

    @BeforeEach
    public void setUp() throws Exception{
        testOven = new MicrowaveOvenStateMachine();
    }

    @Test
    void testInitialState(){
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
    }

    @Test
    void testSetTime(){
        assertEquals(MicrowaveOvenState.PROGRAMMED,testOven.setTime(10,10));
    }

    @Test
    void testSetTimeCancel(){
        assertEquals(MicrowaveOvenState.PROGRAMMED,testOven.setTime(10,10));
        assertEquals(MicrowaveOvenState.IDLE,testOven.cancel());
    }

    @Test
    void testSetTimePowerLevel() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setTime(10, 0));
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setPowerLevel(10));
    }

    @Test
    void testSetTimePowerLevelStart() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setTime(10, 0));
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setPowerLevel(10));
        assertEquals(MicrowaveOvenState.COOKING, testOven.start());
    }

    @Test
    void testSetTimePowerLevelStartStart() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setTime(10, 0));
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setPowerLevel(10));
        assertEquals(MicrowaveOvenState.COOKING, testOven.start());
        testOven.start();
        assertEquals(MicrowaveOvenState.COOKING, testOven.getCurrentState());
    }

    @Test
    void testSetTimePowerLevelStartPause() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setTime(10, 0));
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setPowerLevel(10));
        assertEquals(MicrowaveOvenState.COOKING, testOven.start());
        assertEquals(MicrowaveOvenState.PAUSED,testOven.pause());
    }

    @Test
    void testSetTimePowerLevelStartPausePause() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setTime(10, 0));
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setPowerLevel(10));
        assertEquals(MicrowaveOvenState.COOKING, testOven.start());
        assertEquals(MicrowaveOvenState.PAUSED,testOven.pause());
        testOven.pause();
        assertEquals(MicrowaveOvenState.PAUSED,testOven.getCurrentState());
    }

    @Test
    void testSetTimePowerLevelStartPauseResume() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setTime(10, 0));
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setPowerLevel(10));
        assertEquals(MicrowaveOvenState.COOKING, testOven.start());
        assertEquals(MicrowaveOvenState.PAUSED,testOven.pause());
        assertEquals(MicrowaveOvenState.COOKING,testOven.resume());
    }

    @Test
    void testSetTimePowerLevelStartPauseResumeResume() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setTime(10, 0));
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setPowerLevel(10));
        assertEquals(MicrowaveOvenState.COOKING, testOven.start());
        assertEquals(MicrowaveOvenState.PAUSED,testOven.pause());
        assertEquals(MicrowaveOvenState.COOKING,testOven.resume());
        testOven.resume();
        assertEquals(MicrowaveOvenState.COOKING,testOven.getCurrentState());
    }

    @Test
    void testSetTimePowerLevelStartPauseCancel() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setTime(10, 0));
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setPowerLevel(10));
        assertEquals(MicrowaveOvenState.COOKING, testOven.start());
        assertEquals(MicrowaveOvenState.PAUSED,testOven.pause());
        assertEquals(MicrowaveOvenState.IDLE,testOven.cancel());
    }

    @Test
    void testCancel() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        testOven.cancel();
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
    }

    @Test
    void testStart() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        testOven.start();
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
    }

    @Test
    void testSetPowerLevel() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        testOven.setPowerLevel(10);
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
    }

    @Test
    void testPause() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        testOven.pause();
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
    }

    @Test
    void testResume() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        testOven.resume();
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
    }

    @Test
    void testSetTimePause() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setTime(10, 0));
        testOven.pause();
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.getCurrentState());
    }

    @Test
    void testSetTimeResume() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setTime(10, 0));
        testOven.resume();
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.getCurrentState());
    }

    @Test
    void testSetTimePowerLevelStartSetTime() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setTime(10, 0));
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setPowerLevel(10));
        assertEquals(MicrowaveOvenState.COOKING, testOven.start());
        testOven.setTime(10,10);
        assertEquals(MicrowaveOvenState.COOKING, testOven.getCurrentState());
    }

    @Test
    void testSetTimePowerLevelStartSetPower() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setTime(10, 0));
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setPowerLevel(10));
        assertEquals(MicrowaveOvenState.COOKING, testOven.start());
        testOven.setPowerLevel(10);
        assertEquals(MicrowaveOvenState.COOKING, testOven.getCurrentState());
    }

    @Test
    void testSetTimePowerLevelStartCancel() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setTime(10, 0));
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setPowerLevel(10));
        assertEquals(MicrowaveOvenState.COOKING, testOven.start());
        testOven.cancel();
        assertEquals(MicrowaveOvenState.COOKING, testOven.getCurrentState());
    }

    @Test
    void testSetTimePowerLevelStartPauseStart() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setTime(10, 0));
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setPowerLevel(10));
        assertEquals(MicrowaveOvenState.COOKING, testOven.start());
        assertEquals(MicrowaveOvenState.PAUSED,testOven.pause());
        testOven.start();
        assertEquals(MicrowaveOvenState.PAUSED, testOven.getCurrentState());
    }

    @Test
    void testSetTimePowerLevelStartPauseSetTime() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setTime(10, 0));
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setPowerLevel(10));
        assertEquals(MicrowaveOvenState.COOKING, testOven.start());
        assertEquals(MicrowaveOvenState.PAUSED,testOven.pause());
        testOven.setTime(10,10);
        assertEquals(MicrowaveOvenState.PAUSED, testOven.getCurrentState());
    }

    @Test
    void testSetTimePowerLevelStartPauseSetPower() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setTime(10, 0));
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setPowerLevel(10));
        assertEquals(MicrowaveOvenState.COOKING, testOven.start());
        assertEquals(MicrowaveOvenState.PAUSED,testOven.pause());
        testOven.setPowerLevel(10);
        assertEquals(MicrowaveOvenState.PAUSED, testOven.getCurrentState());
    }

    @Test
    void testSetTimeSetTime(){
        assertEquals(MicrowaveOvenState.PROGRAMMED,testOven.setTime(10,10));
        testOven.setTime(10,10);
        assertEquals(MicrowaveOvenState.PROGRAMMED,testOven.getCurrentState());
    }



}