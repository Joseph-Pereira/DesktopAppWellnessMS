package view;

import javax.swing.*;
import java.awt.*;

public class appointmentPanel extends JPanel {
    public appointmentPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Manage Appointments", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        JComboBox<String> counselorCombo = new JComboBox<>(new String[]{"Dr. Smith", "Dr. Jane"});
        JTextField dateField = new JTextField();
        JTextField timeField = new JTextField();
        JButton bookBtn = new JButton("Book Appointment");

        form.add(new JLabel("Counselor:"));
        form.add(counselorCombo);
        form.add(new JLabel("Date (YYYY-MM-DD):"));
        form.add(dateField);
        form.add(new JLabel("Time (HH:MM):"));
        form.add(timeField);
        form.add(new JLabel());
        form.add(bookBtn);

        add(title, BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);
    }
}

