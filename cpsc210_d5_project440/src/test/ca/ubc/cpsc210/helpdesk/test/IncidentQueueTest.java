package ca.ubc.cpsc210.helpdesk.test;


import ca.ubc.cpsc210.helpdesk.model.Incident;
import ca.ubc.cpsc210.helpdesk.model.IncidentQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncidentQueueTest {
    // Design your unit tests for the IncidentQueue class here

    private IncidentQueue queue;

    private Incident Inc0 = new Incident(0,"");
    private Incident Inc1 = new Incident(1,"");
    private Incident Inc2 = new Incident(2,"");
    private Incident Inc3 = new Incident(3,"");
    private Incident Inc4 = new Incident(4,"");
    private Incident Inc5 = new Incident(5,"");
    private Incident Inc6 = new Incident(6,"");
    private Incident Inc7 = new Incident(7,"");
    private Incident Inc8 = new Incident(8,"");
    private Incident Inc9 = new Incident(9,"");
    private Incident Inc10 = new Incident(10,"");

    @BeforeEach
    public void runBefore(){
        queue = new IncidentQueue();
    }

    @Test
    public void testConstructor(){
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testAddIncidentNotFullOne(){
        queue.addIncident(Inc0);
        assertEquals(Inc0,queue.getNextIncident());
    }

    @Test
    public void testAddIncidentNotFull5() {
        assertTrue(queue.addIncident(Inc0));
        assertTrue(queue.addIncident(Inc2));
        assertTrue(queue.addIncident(Inc3));
        assertTrue(queue.addIncident(Inc5));
        assertTrue(queue.addIncident(Inc7));
        assertEquals(5,queue.length());

        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());
        assertEquals(Inc0, queue.getNextIncident());

        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());
        assertEquals(Inc2, queue.getNextIncident());

        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());
        assertEquals(Inc3, queue.getNextIncident());

        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());
        assertEquals(Inc5, queue.getNextIncident());

        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());
        assertEquals(Inc7, queue.getNextIncident());

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testAddIncidentFull(){
        for(int i = 0; i < IncidentQueue.MAX_SIZE+4; i++) {
            queue.addIncident(Inc0);
        }
        assertFalse(queue.addIncident(Inc9));
        assertEquals(IncidentQueue.MAX_SIZE,queue.length());
        assertFalse(queue.isEmpty());
        assertTrue(queue.isFull());
        assertEquals(IncidentQueue.MAX_SIZE,queue.length());
        for(int i = 0; i < IncidentQueue.MAX_SIZE; i++) {
            assertEquals(Inc0,queue.getNextIncident());
        }
        assertTrue(queue.isEmpty());
        assertFalse(queue.isFull());
    }

    @Test
    public void testGetPositionInQueueOfCaseNumber(){
        queue.addIncident(Inc0);
        queue.addIncident(Inc2);
        queue.addIncident(Inc3);
        queue.addIncident(Inc5);
        queue.addIncident(Inc7);
        queue.addIncident(Inc7);
        queue.addIncident(Inc7);

        assertEquals(4,queue.getPositionInQueueOfCaseNumber(5));
        assertEquals(1,queue.getPositionInQueueOfCaseNumber(0));
        assertEquals(5,queue.getPositionInQueueOfCaseNumber(7));
        assertEquals(-1,queue.getPositionInQueueOfCaseNumber(7000));
    }


}