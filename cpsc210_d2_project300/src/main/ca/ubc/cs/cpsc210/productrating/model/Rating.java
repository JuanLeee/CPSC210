package ca.ubc.cs.cpsc210.productrating.model;

// Represents a rating (for a product or movie or ...)
// that is based on an arbitrary number of votes on a scale
// of 0 to MAX_VOTE stars
public class Rating {
    public static final int MAX_VOTE = 3;

    private int NumberOfVotes;
    private double averageScore;
    private String Stars;
    private int totalVote;

    // TODO: Add specification (do not implement methods - write stubs only)

    // EFFECTS: Rating has zero number of votes and zero average rating
    public Rating() {
        NumberOfVotes = 0;
        averageScore = 0.0;
        Stars = "";
        totalVote = 0;

    }

    // Modifies: this
    // EFFECTS: Zeroes number of votes and zero average rating
    public void resetVotes(){
        NumberOfVotes = 0;
        averageScore = 0.0;
        Stars = "";
        totalVote = 0;
    }

    // EFFECTS: returns number of votes
    public int getNumVotes(){
        return NumberOfVotes;
    }

    // REQUIRES: voteValue >= 0 and voteValue <= 3
    // MODIFIES: this
    // EFFECTS: adds voteValue to vote list
    public void addVote(int voteValue){
        NumberOfVotes = NumberOfVotes + 1;
        totalVote = totalVote + voteValue;

    }

    // EFFECTS: returns average vote score
    public double computeScore(){
        if (NumberOfVotes != 0){
            averageScore = totalVote / (double)NumberOfVotes;
        }
        return averageScore;
    }

    // EFFECTS: returns string of computeScore() stars rounded down to the nearest star
    public String toString(){
        Stars = "";
        for (int i = 0; i  < (int)(totalVote/(double)NumberOfVotes) ; i++){
            Stars = Stars + "*";
        }
        return Stars;
    }
    // TODO: After tests have been designed: add fields
    // TODO: After adding fields: replace method stubs with full implementation
}
