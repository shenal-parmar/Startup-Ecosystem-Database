import java.sql.*;
public class DatabaseManager2 {
private static final String URL = "jdbc:postgresql://localhost:5432/DBMS_G39"; 
private static final String USER = "postgres"; 
private static final String PASSWORD = ""; 
public Connection connect() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
}

public void insertMentor(String name, String expertise, String affiliation, String linkedin_profile){
    String insertSQL = "INSERT INTO \"SE\".mentor (name, expertise, affiliation, linkedin_profile) VALUES (?, ?, ?, ?)";
    try (Connection connection = connect();
    PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
        pstmt.setString(1, name);
        pstmt.setString(2, expertise);
        pstmt.setString(3, affiliation);
        pstmt.setString(4, linkedin_profile);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public ResultSet readMentor() {
    String selectSQL = "SELECT * FROM \"SE\".mentor";
    try {
        Connection connection = connect();
        PreparedStatement pstmt = connection.prepareStatement(selectSQL);
        return pstmt.executeQuery();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

public void updateMentor(int mentor_id, String name, String expertise, String affiliation, String linkedin_profile) {
    String updateSQL = "UPDATE \"SE\".mentor SET name = ?, expertise = ?, affiliation = ?, linkedin_profile = ? WHERE mentor_id = ?";
    try (Connection connection = connect();
    PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
        pstmt.setString(1, name);
        pstmt.setString(2, name);
        pstmt.setString(3, expertise);
        pstmt.setString(4, affiliation);
        pstmt.setInt(5, mentor_id);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void deleteMentor(int mentor_id) {
    String deleteSQL = "DELETE FROM \"SE\".mentor WHERE mentor_id = ?";
    try (Connection connection = connect();
    PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
        pstmt.setInt(1, mentor_id);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
