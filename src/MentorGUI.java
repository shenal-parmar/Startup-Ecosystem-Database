import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
public class MentorGUI extends JFrame {
    private JTextField nameField, expertiseField, affiliationField, linkedin_profileField, idField;
    private JButton addButton, updateButton, deleteButton, loadButton;
    private JTable mentorTable;
    private DefaultTableModel tableModel1;
    private DatabaseManager2 dbManager;
public MentorGUI() {
    dbManager = new DatabaseManager2();
    setTitle("Mentor Management");
    setLayout(new BorderLayout());

    JPanel inputPanel = new JPanel(new GridLayout(5, 2));
    inputPanel.add(new JLabel("ID:"));
    idField = new JTextField();
    inputPanel.add(idField);

    inputPanel.add(new JLabel("Name:"));
    nameField = new JTextField();
    inputPanel.add(nameField);

    inputPanel.add(new JLabel("Expertise:"));
    expertiseField = new JTextField();
    inputPanel.add(expertiseField);

    inputPanel.add(new JLabel("Affiliation:"));
    affiliationField = new JTextField();
    inputPanel.add(affiliationField);

    inputPanel.add(new JLabel("Linkedin Profile :"));
    linkedin_profileField = new JTextField();
    inputPanel.add(linkedin_profileField);

    JPanel buttonPanel = new JPanel();
    addButton = new JButton("Add");
    updateButton = new JButton("Update");
    deleteButton = new JButton("Delete");
    loadButton = new JButton("Load");
    buttonPanel.add(addButton);
    buttonPanel.add(updateButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(loadButton);

    tableModel1 = new DefaultTableModel(new String[]{"ID", "Name", "Expertise", "Affiliation", "Linkedin Profile"}, 0);
    mentorTable = new JTable(tableModel1);
    JScrollPane scrollPane = new JScrollPane(mentorTable);

    add(inputPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

addButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String expertise = expertiseField.getText();
        String affiliation = affiliationField.getText();
        String linkedin_profile = linkedin_profileField.getText();;
        dbManager.insertMentor(name, expertise, affiliation, linkedin_profile);
        loadMentor();
    }
});

updateButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int mentor_id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String expertise = expertiseField.getText();
        String affiliation = affiliationField.getText();
        String linkedin_profile = linkedin_profileField.getText();
        dbManager.updateMentor(mentor_id, name, expertise, affiliation, linkedin_profile);
        loadMentor();
    }
});

deleteButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int mentor_id = Integer.parseInt(idField.getText());
        dbManager.deleteMentor(mentor_id);
        loadMentor();
    }
});

loadButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        loadMentor();
    }
});

    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
}

private void loadMentor() {
    try {
        ResultSet resultSet = dbManager.readMentor();
        tableModel1.setRowCount(0); // Clear existing data
        while (resultSet != null && resultSet.next()) {
            int id = resultSet.getInt("mentor_id");
            String name = resultSet.getString("name");
            String expertise = resultSet.getString("expertise");
            String affiliation = resultSet.getString("affiliation");
            String linkedin_profile = resultSet.getString("linkedin_profile");

            tableModel1.addRow(new Object[]{id, name, expertise, affiliation, linkedin_profile});
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public static void main(String[] args) {
    new MentorGUI();
}
}