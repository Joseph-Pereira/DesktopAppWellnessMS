package controller;

import model.Counselor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class counselorController {
    private final List<Counselor> counselorList = new ArrayList<>();

    public boolean validate(String name, String surname, String specialization, String availability) {
        if (name.isEmpty() || surname.isEmpty() || specialization.isEmpty() || availability.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void add(String name, String surname, String specialization, String availability) {
        counselorList.add(new Counselor(name, surname, specialization, availability));
    }

    public void update(int index, String name, String surname, String specialization, String availability) {
        counselorList.set(index, new Counselor(name, surname, specialization, availability));
    }

    public void delete(int index) {
        counselorList.remove(index);
    }

    public List<Counselor> getAll() {
        return counselorList;
    }
}
