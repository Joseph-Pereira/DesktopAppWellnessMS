package dao;

import model.Feedback;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.dao.DBConnection;


public class feedbackDAO {

    public void addFeedback(Feedback f) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO Feedback (student, rating, comments) VALUES (?, ?, ?)")) {
            ps.setString(1, f.getStudent());
            ps.setInt(2, f.getRating());
            ps.setString(3, f.getComments());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding feedback: " + e.getMessage());
        }
    }

    public List<Feedback> getAllFeedback() {
        List<Feedback> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Feedback")) {
            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getString("student"),
                        rs.getInt("rating"),
                        rs.getString("comments"));
                list.add(f);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching feedback: " + e.getMessage());
        }
        return list;
    }

    public void deleteFeedbackByStudent(String student) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM Feedback WHERE student = ?")) {
            ps.setString(1, student);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting feedback: " + e.getMessage());
        }
    }

    public void updateFeedback(Feedback f) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE Feedback SET rating = ?, comments = ? WHERE student = ?")) {
            ps.setInt(1, f.getRating());
            ps.setString(2, f.getComments());
            ps.setString(3, f.getStudent());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating feedback: " + e.getMessage());
        }
    }
}
