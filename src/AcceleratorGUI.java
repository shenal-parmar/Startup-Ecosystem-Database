import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AcceleratorGUI extends JFrame {
    private JTextField nameField, locationField, industry_focusField, batch_sizeField, program_durationField, idField;
    private JButton addButton, updateButton, deleteButton, loadButton;
    private JTable acceleratorTable;
    private DefaultTableModel tableModel1;
    private DatabaseManager1 dbManager;

    public AcceleratorGUI() {
    dbManager = new DatabaseManager1();
    setTitle("Accelerator Management");
    setLayout(new BorderLayout());

    JPanel inputPanel = new JPanel(new GridLayout(5, 2));
    inputPanel.add(new JLabel("ID:"));
    idField = new JTextField();
    inputPanel.add(idField);

    inputPanel.add(new JLabel("Name:"));
    nameField = new JTextField();
    inputPanel.add(nameField);

    inputPanel.add(new JLabel("Location:"));
    locationField = new JTextField();
    inputPanel.add(locationField);

    inputPanel.add(new JLabel("Industry Focus :"));
    industry_focusField = new JTextField();
    inputPanel.add(industry_focusField);

    inputPanel.add(new JLabel("Batch Size:"));
    batch_sizeField = new JTextField();
    inputPanel.add(batch_sizeField);

    inputPanel.add(new JLabel("Program Duration:"));
    program_durationField = new JTextField();
    inputPanel.add(program_durationField); 

    JPanel buttonPanel = new JPanel();
    addButton = new JButton("Add");
    updateButton = new JButton("Update");
    deleteButton = new JButton("Delete");
    loadButton = new JButton("Load");
    buttonPanel.add(addButton);
    buttonPanel.add(updateButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(loadButton);

    tableModel1 = new DefaultTableModel(new String[]{"ID", "Name", "Location", "Industry Focus", "Batch Size", "Program Duration"}, 0);
    acceleratorTable = new JTable(tableModel1);
    JScrollPane scrollPane = new JScrollPane(acceleratorTable);

    add(inputPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

addButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String location = locationField.getText();
        String industry_focus = industry_focusField.getText();
        int batch_size = Integer.parseInt(batch_sizeField.getText());
        int program_duration = Integer.parseInt(program_durationField.getText());
        dbManager.insertAccelerator(name, location, industry_focus, batch_size , program_duration);
        loadAccelerator();
    }
});

updateButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int accelerator_id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String location = locationField.getText();
        String industry_focus = industry_focusField.getText();
        int batch_size = Integer.parseInt(batch_sizeField.getText());
        int program_duration = Integer.parseInt(program_durationField.getText());
        dbManager.updateAccelerator(accelerator_id, name, location, industry_focus, batch_size, program_duration);
        loadAccelerator();
    }
});

deleteButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int accelerator_id = Integer.parseInt(idField.getText());
        dbManager.deleteAccelerator(accelerator_id);
        loadAccelerator();
    }
});

loadButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        loadAccelerator();
    }
});

    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
}

private void loadAccelerator() {
    try {
    ResultSet resultSet = dbManager.readAccelerator();
    tableModel1.setRowCount(0); // Clear existing data
    while (resultSet != null && resultSet.next()) {
        int id = resultSet.getInt("accelerator_id");
        String name = resultSet.getString("name");
        String location = resultSet.getString("location");
        String industry_focus = resultSet.getString("industry_focus");
        int batch_size = resultSet.getInt("batch_size");
        int program_duration = resultSet.getInt("program_duration");

        tableModel1.addRow(new Object[]{id, name, location, industry_focus, batch_size, program_duration});
    }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public static void main(String[] args) {
    new AcceleratorGUI();
}
}