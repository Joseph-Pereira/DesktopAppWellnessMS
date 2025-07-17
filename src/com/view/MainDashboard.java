package view;

import javax.swing.*;

public class MainDashboard extends JFrame {
    public MainDashboard() {
        setTitle("Wellness Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Appointments", new appointmentPanel());
        tabs.addTab("Counselors", new counselorPanel());
        tabs.addTab("Feedback", new feedbackPanel());

        add(tabs);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainDashboard().setVisible(true));
    }
}
