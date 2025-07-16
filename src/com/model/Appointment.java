package model;

public class Appointment {
    private String student;
    private String counselor;
    private String date;
    private String time;
    private String status;

    public Appointment(String student, String counselor, String date, String time, String status) {
        this.student = student;
        this.counselor = counselor;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getSummary() {
        return String.format("Appointment: %s with %s on %s at %s [%s]", student, counselor, date, time, status);
    }

    // Getters and setters (encapsulation)
    // ...
}
