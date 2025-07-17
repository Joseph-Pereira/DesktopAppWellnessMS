package dao;

import model.Appointment;
import java.sql.*;
import java.util.*;

public class appointmentDAO {
    public void addAppointment(Appointment a) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Appointments VALUES (?, ?, ?, ?, ?)")) {
            ps.setString(1, a.getStudent());
            ps.setString(2, a.getCounselor());
            ps.setString(3, a.getDate());
            ps.setString(4, a.getTime());
            ps.setString(5, a.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding appointment: " + e.getMessage());
        }
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Appointments")) {
            while (rs.next()) {
                Appointment a = new Appointment(
                        rs.getString("student"),
                        rs.getString("counselor"),
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getString("status"));
                list.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching appointments: " + e.getMessage());
        }
        return list;
    }

    public static void updateAppointment(int id, String status) {
        try (Connection conn = DBUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE Appointments SET status=? WHERE id=?")) {
            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println("Appointment updated.");
        } catch (SQLException e) {
            System.out.println("Error updating appointment: " + e.getMessage());
        }
    }
    public static void deleteAppointment(int id) {
        try (Connection conn = DBUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "DELETE FROM Appointments WHERE id=?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Appointment deleted.");
        } catch (SQLException e) {
            System.out.println("Error deleting appointment: " + e.getMessage());
        }
    }
}
}

