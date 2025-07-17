package controller;

import dao.appointmentDAO;
import model.Appointment;

import java.util.*;

public class appointmentController {
    private appointmentDAO dao = new appointmentDAO();
    private List<Appointment> localList = new ArrayList<>();

    public void create(Appointment a) {
        dao.addAppointment(a);
        localList.add(a); // Collection usage
    }

    public void listAll() {
        List<Appointment> all = dao.getAllAppointments();
        for (Appointment a : all) {
            System.out.println(a.getSummary());
        }
    }

    // update, delete, filter by date etc.
}
