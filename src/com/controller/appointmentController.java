package controller;

import model.Appointment;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class appointmentController {
    private final List<Appointment> appointmentList = new ArrayList<>();

    public boolean validateAppointment(String student, String counselor, String date, String time) {
        if (student.isEmpty() || counselor.isEmpty() || date.isEmpty() || time.isEmpty()) {
            showError("All fields must be filled.");
            return false;
        }

        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            showError("Invalid date format. Use YYYY-MM-DD.");
            return false;
        }

        if (parsedDate.isBefore(LocalDate.now())) {
            showError("Date cannot be in the past.");
            return false;
        }

        try {
            LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            showError("Invalid time format. Use HH:MM.");
            return false;
        }

        return true;
    }

    public void addAppointment(String student, String counselor, String date, String time, String status) {
        appointmentList.add(new Appointment(student, counselor, date, time, status));
    }

    public void updateAppointment(int index, String student, String counselor, String date, String time, String status) {
        appointmentList.set(index, new Appointment(student, counselor, date, time, status));
    }

    public void deleteAppointment(int index) {
        appointmentList.remove(index);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentList;
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }
}
