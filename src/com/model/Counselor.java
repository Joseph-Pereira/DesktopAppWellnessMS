package model;

public class Counselor {
    private String name;
    private String surname;
    private String specialization;
    private String availability;

    public Counselor(String name, String surname, String specialization, String availability) {
        this.name = name;
        this.surname = surname;
        this.specialization = specialization;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getAvailability() {
        return availability;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getSummary() {
        return String.format("Counselor: %s (%s) - %s", name, surname, specialization, availability);
    }


}
