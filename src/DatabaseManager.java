import java.sql.*;
public class DatabaseManager {
private static final String URL = "jdbc:postgresql://localhost:5432/DBMS_G39"; 
private static final String USER = "postgres"; 
private static final String PASSWORD = ""; 

public Connection connect() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
}

public void insertStartup(String name,String industry,String stage,String founded_date,String location ,int funding_amount ,int employee_count){
    String insertSQL = "INSERT INTO \"SE\".startup (name, industry,stage,founded_date, location,funding_amount,employee_count) VALUES (?, ?, ?, ? , ? , ? , ?)";
    try (Connection connection = connect();
    PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
    pstmt.setString(1, name);
    pstmt.setString(2, industry);
    pstmt.setString(3, stage);
    pstmt.setString(4, founded_date);
    pstmt.setString(5, location);
    pstmt.setInt(6, funding_amount);
    pstmt.setInt(7, employee_count);
    pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public ResultSet readStartup() {
    String selectSQL = "SELECT * FROM \"SE\".startup";
    try {
    Connection connection = connect();
    PreparedStatement pstmt = connection.prepareStatement(selectSQL);
    return pstmt.executeQuery();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

public void updateStartup(int startup_id, String name, String industry, String stage,String founded_date,String location ,int funding_amount ,int employee_count) {
    String updateSQL = "UPDATE \"SE\".startup SET name = ?, industry = ?, stage = ?, founded_date = ?, location = ?, funding_amount = ?, employee_count = ? WHERE startup_id = ?";
    try (Connection connection = connect();
    PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
        pstmt.setString(1, name);
        pstmt.setString(2, industry);
        pstmt.setString(3, stage);
        pstmt.setString(4, founded_date);
        pstmt.setString(5, location);
        pstmt.setInt(6, funding_amount);
        pstmt.setInt(7, employee_count);
        pstmt.setInt(8, startup_id);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void deleteStartup(int startup_id) {
    String deleteSQL = "DELETE FROM \"SE\".startup WHERE startup_id = ?";
    try (Connection connection = connect();
    PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
        pstmt.setInt(1, startup_id);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
