package com.controller;

import com.model.Feedback;
import com.dao.feedbackDAO;
import java.util.List;

public class feedbackController {
    private feedbackDAO dao = new feedbackDAO();

    public void create(Feedback f) {
        dao.addFeedback(f);
    }

    public void listAll() {
        List<Feedback> all = dao.getAllFeedback();
        for (Feedback f : all) {
            System.out.println(f.getSummary());
        }
    }

    public void delete(String student) {
        dao.deleteFeedbackByStudent(student);
    }

    public void update(Feedback f) {
        dao.updateFeedback(f);
    }
}
