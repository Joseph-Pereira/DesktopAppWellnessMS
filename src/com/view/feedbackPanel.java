package view;

import javax.swing.*;
import java.awt.*;

public class feedbackPanel extends JPanel {
    public feedbackPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Submit Feedback", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        JComboBox<Integer> ratingCombo = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        JTextArea commentArea = new JTextArea(4, 20);
        JButton submitBtn = new JButton("Submit Feedback");

        form.add(new JLabel("Rating (1-5):"));
        form.add(ratingCombo);
        form.add(new JLabel("Comments:"));
        form.add(new JScrollPane(commentArea));
        form.add(new JLabel());
        form.add(submitBtn);

        add(title, BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);
    }
}
