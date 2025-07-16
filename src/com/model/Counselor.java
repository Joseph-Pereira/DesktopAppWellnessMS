package model;

public class Counselor {
    private String name;
    private String surname;
    private String specialization;
    private String availability;

    public Counselor(String name, String surname, String specialization, String availability) {
        this.name = name;
        this.specialization = specialization;
        this.availability = availability;
    }

    public String getSummary() {
        return String.format("Counselor: %s (%s) - %s", name, specialization, availability);
    }

    // Getters and setters
    // ...
}
