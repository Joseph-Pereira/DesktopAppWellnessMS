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

    // updateAppointment(), deleteAppointment() ...
}

