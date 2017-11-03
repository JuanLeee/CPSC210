package ca.ubc.cpsc210.helpdesk.model;

import java.util.LinkedList;

// Represents a queue of incidents to be handled by helpdesk
// with maximum size MAX_SIZE
public class  IncidentQueue {
    public static final int MAX_SIZE = 10;
    // TODO: complete the design of the IncidentQueue class

    private LinkedList<Incident> queue;

    //Effects: this LinkedList is empty
    public IncidentQueue (){
        queue = new LinkedList<>();
    }

    //Modifies: this
    //Effects: Incident incident is added to the LinkedList if not full and returns true
    //          if full, returns false
    public boolean addIncident(Incident incident){
        if (queue.size() == MAX_SIZE){
            return false;
        }
        else{
            queue.add(incident);
            return true;
        }

    }

    //Requires: queue is not empty
    //Modifies: this
    //Effects: Removes first incident from the queue and returns it
    public Incident getNextIncident(){
        return queue.remove();
    }

    //Effects: returns the position (int pos) that has the case number (int i)
    //         if no case number that matches the case number, return -1
    public int getPositionInQueueOfCaseNumber(int i){
        int pos = 1;
        for(Incident next: queue){
            if(i == next.getCaseNum()){
                return pos;
            }
            else{
                pos++;
            }
        }
        return -1;
    }

    //Effects: return number of Incidents in queue
    public int length(){
        return queue.size();
    }

    //Effects: returns true is queue is empty; else false
    public boolean isEmpty(){
        return queue.isEmpty();
    }

    //Effects: returns true if queue is full; else false
    public boolean isFull(){
        return queue.size() == MAX_SIZE;

    }

}
