import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
public class StartupGUI extends JFrame {
    private JTextField nameField, industryField, stageField, dateField, locationField, funding_amountField, employee_countField, idField;
    private JButton addButton, updateButton, deleteButton, loadButton;
    private JTable startupTable;
    private DefaultTableModel tableModel;
    private DatabaseManager dbManager;

public StartupGUI() {
    dbManager = new DatabaseManager();
    setTitle("Startup Management");
    setLayout(new BorderLayout());

    JPanel inputPanel = new JPanel(new GridLayout(4, 1));
    inputPanel.add(new JLabel("ID:"));
    idField = new JTextField();
    inputPanel.add(idField);

    inputPanel.add(new JLabel("Name:"));
    nameField = new JTextField();
    inputPanel.add(nameField);

    inputPanel.add(new JLabel("Industry:"));
    industryField = new JTextField();
    inputPanel.add(industryField);

    inputPanel.add(new JLabel("Stage:"));
    stageField = new JTextField();
    inputPanel.add(stageField);

    inputPanel.add(new JLabel("Date:"));
    dateField = new JTextField();
    inputPanel.add(dateField);

    inputPanel.add(new JLabel("Location:"));
    locationField = new JTextField();
    inputPanel.add(locationField); 

    inputPanel.add(new JLabel("Funding Amount:"));
    funding_amountField = new JTextField();
    inputPanel.add(funding_amountField); 

    inputPanel.add(new JLabel("Employee Count:"));
    employee_countField = new JTextField();
    inputPanel.add(employee_countField);

    JPanel buttonPanel = new JPanel();
    addButton = new JButton("Add");
    updateButton = new JButton("Update");
    deleteButton = new JButton("Delete");
    loadButton = new JButton("Load");
    buttonPanel.add(addButton);
    buttonPanel.add(updateButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(loadButton);

    tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Industry", "Stage", "Date", "Location","FundAmount","EmployeeCount"}, 0);
    startupTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(startupTable);

    add(inputPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

addButton.addActionListener(new ActionListener() {
    @Override

    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String industry= industryField.getText();
        String stage= stageField.getText();
        String date= dateField.getText();
        String location= locationField.getText();
        int funding_amount = Integer.parseInt(funding_amountField.getText());
        int employee_count = Integer.parseInt(employee_countField.getText());
        dbManager.insertStartup(name, industry,stage,date, location ,funding_amount ,employee_count);
        loadStartup();
    }
});

updateButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int startup_id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String industry= industryField.getText();
        String stage= stageField.getText();
        String founded_date = dateField.getText();
        String location= locationField.getText();
        int funding_amount = Integer.parseInt(funding_amountField.getText());
        int employee_count = Integer.parseInt(employee_countField.getText());
        dbManager.updateStartup(startup_id,name, industry,stage,founded_date, location ,funding_amount, employee_count);
        loadStartup();
    }
});

deleteButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int startup_id = Integer.parseInt(idField.getText());
        dbManager.deleteStartup(startup_id);
        loadStartup();
    }
});

loadButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        loadStartup();
    }
});

    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
}

private void loadStartup() {
    try {
        ResultSet resultSet = dbManager.readStartup();
        tableModel.setRowCount(0); // Clear existing data
        while (resultSet != null && resultSet.next()) {
            int id = resultSet.getInt("startup_id");
            String name = resultSet.getString("name");
            String industry= resultSet.getString("industry");
            String stage = resultSet.getString("stage");
            String date= resultSet.getString("founded_date");
            String location= resultSet.getString("location");
            int fundAmt =resultSet.getInt("funding_amount");
            int employee_count= resultSet.getInt("employee_count");

            tableModel.addRow(new Object[]{id, name, industry,stage,date, location ,fundAmt , employee_count});
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public static void main(String[] args) {
    new StartupGUI();
}
}