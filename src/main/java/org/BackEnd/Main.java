package org.BackEnd;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args){
        Connection connection;
        try {
            connection = DatabaseConnection.GetConnection();
                if (!QueryTest.CheckIfTableExists(connection))
                {
                    QueryTest.CreateTable(connection);
                }

             QueryTest.InsertToBase(1,"2024-10-10",true, 10, "Założył firme", connection);
            //QueryTest.DeleteFromBase(2,connection);
            QueryTest.ShowBase(connection);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }
    }
}