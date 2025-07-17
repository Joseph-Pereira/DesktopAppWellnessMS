package com.view;

import com.dao.DBConnection;
import java.sql.Connection;
//import com.util.DBInitialization;
import javax.swing.*;
import com.view.appointmentPanel;
import com.view.feedbackPanel;
import com.view.counselorPanel;
import java.sql.*;


public class MainDashboard extends JFrame {

    public MainDashboard() {
        setTitle("BC Wellness Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Appointments", new appointmentPanel()); 
        tabbedPane.addTab("Counselors", new counselorPanel());
        tabbedPane.addTab("Feedback", new feedbackPanel());        
        
        add(tabbedPane);
        setVisible(true);
    }

    public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(() -> new MainDashboard());

    
}

    }