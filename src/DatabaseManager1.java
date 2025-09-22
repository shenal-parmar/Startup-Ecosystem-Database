import java.sql.*;
public class DatabaseManager1 {
private static final String URL = "jdbc:postgresql://localhost:5432/DBMS_G39"; 
private static final String USER = "postgres"; 
private static final String PASSWORD = ""; 
public Connection connect() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
}

public void insertAccelerator(String name, String location, String industry_focus, int batch_size, int program_duration){
    String insertSQL = "INSERT INTO \"SE\".accelerator (name, location, industry_focus, batch_size, program_duration) VALUES (?, ?, ?, ? , ?)";
    try (Connection connection = connect();
    PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
        pstmt.setString(1, name);
        pstmt.setString(2, location);
        pstmt.setString(3, industry_focus);
        pstmt.setInt(4, batch_size);
        pstmt.setInt(5, program_duration);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public ResultSet readAccelerator() {
    String selectSQL = "SELECT * FROM \"SE\".accelerator";
    try {
        Connection connection = connect();
        PreparedStatement pstmt = connection.prepareStatement(selectSQL);
        return pstmt.executeQuery();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

public void updateAccelerator(int accelerator_id, String name, String location, String industry_focus, int batch_size, int program_duration ) {
    String updateSQL = "UPDATE \"SE\".accelerator SET name = ?, location = ?, industry_focus = ?, batch_size = ?, program_duration = ? WHERE accelerator_id = ?";

    try (Connection connection = connect();
    PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
        pstmt.setString(1, name);
        pstmt.setString(2, location);
        pstmt.setString(3, industry_focus);
        pstmt.setInt(4, batch_size);
        pstmt.setInt(5, program_duration);
        pstmt.setInt(6, accelerator_id);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void deleteAccelerator(int accelerator_id) {
    String deleteSQL = "DELETE FROM \"SE\".accelerator WHERE accelerator_id = ?";
    try (Connection connection = connect();
    PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
        pstmt.setInt(1, accelerator_id);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
