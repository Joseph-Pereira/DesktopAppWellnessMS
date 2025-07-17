package controller;

import model.Feedback;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class feedbackController {
    private final List<Feedback> feedbackList = new ArrayList<>();

    public boolean validate(String student, int rating, String comments) {
        if (student.isEmpty() || comments.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (rating < 1 || rating > 5) {
            JOptionPane.showMessageDialog(null, "Rating must be between 1 and 5.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public void add(String student, int rating, String comments) {
        feedbackList.add(new Feedback(student, rating, comments));
    }

    public void update(int index, String student, int rating, String comments) {
        feedbackList.set(index, new Feedback(student, rating, comments));
    }

    public void delete(int index) {
        feedbackList.remove(index);
    }

    public List<Feedback> getAll() {
        return feedbackList;
    }
}
