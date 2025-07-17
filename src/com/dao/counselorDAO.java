package dao;

import model.Counselor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.dao.DBConnection;


public class counselorDAO {

    public void addCounselor(Counselor c) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO Counselors (name, surname, specialization, availability) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, c.getName());
            ps.setString(1, c.getSurname());
            ps.setString(2, c.getSpecialization());
            ps.setString(3, c.getAvailability());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding counselor: " + e.getMessage());
        }
    }

    public List<Counselor> getAllCounselors() {
        List<Counselor> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Counselors")) {
            while (rs.next()) {
                Counselor c = new Counselor(
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("specialization"),
                        rs.getString("availability"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching counselors: " + e.getMessage());
        }
        return list;
    }

    public void deleteCounselorByName(String name) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM Counselors WHERE name = ?")) {
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting counselor: " + e.getMessage());
        }
    }

    public void updateCounselor(Counselor c) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE Counselors SET specialization = ?, availability = ? WHERE name = ?")) {
            ps.setString(1, c.getSpecialization());
            ps.setString(2, c.getAvailability());
            ps.setString(3, c.getSurname());
            ps.setString(3, c.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating counselor: " + e.getMessage());
        }
    }
}
