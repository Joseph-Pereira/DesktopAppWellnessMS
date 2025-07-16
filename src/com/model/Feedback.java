package model;

public class Feedback {
    private String student;
    private int rating;
    private String comments;

    public Feedback(String student, int rating, String comments) {
        this.student = student;
        this.rating = rating;
        this.comments = comments;
    }

    public String getSummary() {
        return String.format("Feedback by %s: %d/5 - %s", student, rating, comments);
    }

    // Getters and setters
    // ...
}
