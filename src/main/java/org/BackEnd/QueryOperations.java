package org.BackEnd;

import java.sql.*;
import java.util.ArrayList;

public class QueryOperations {

    public static boolean CheckIfTableExists(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        String query = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'OPINIONS'";

        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static void CreateTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        String createTableSQL = "CREATE TABLE OPINIONS (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "staffNumber INT , " +
                "opinionDate DATE, " +
                "opinionStatus BOOLEAN, " +
                "wage INT, " +
                "comment text)";

        statement.executeUpdate(createTableSQL);
    }

    public static void InsertToBase(int staffNumber, String date, Boolean status, int wage, String comment, Connection currentConnection) throws SQLException {
        String insertSQL = "INSERT INTO OPINIONS (staffNumber,opinionDate, opinionStatus, wage,comment) VALUES (?,?,?,?,?)";
        PreparedStatement statement = currentConnection.prepareStatement(insertSQL);

        //change value to fit format
        Date opDate = Date.valueOf(date);

        //set parameters given above
        statement.setInt(1, staffNumber);
        statement.setDate(2, opDate);
        statement.setBoolean(3, status);
        statement.setInt(4, wage);
        statement.setString(5, comment);

        statement.executeUpdate();
    }

    public static ArrayList<String> ShowBase(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM OPINIONS");
        ResultSet rs = statement.executeQuery();
        ArrayList<String> Baselines = new ArrayList<>();
        while (rs.next()) {
            String statusOfOpinion;
            if (rs.getBoolean("opinionStatus")) {
                statusOfOpinion = "positive";
            } else {
                statusOfOpinion = "negative";
            }

            String temp = rs.getInt("id") + " " + rs.getString("staffNumber") + " "
                    + rs.getString("opinionDate") + " " + statusOfOpinion + " " + rs.getString("wage")
                    + " " + rs.getString("comment");
            Baselines.add(temp);
        }
        return Baselines;
    }

    public static void DeleteFromBase(int opinionID, Connection connection) throws SQLException {
        String deleteSQL = "DELETE FROM OPINIONS WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(deleteSQL);
        statement.setInt(1, opinionID);
        statement.executeUpdate();
    }
}
