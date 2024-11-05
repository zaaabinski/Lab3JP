package org.MiddlEnd;

import org.BackEnd.DatabaseConnection;
import org.BackEnd.QueryOperations;
import org.FrontEnd.Front;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args){
        Connection connection;

        try {
            connection = DatabaseConnection.GetConnection();
                if (!QueryOperations.CheckIfTableExists(connection))
                {
                    QueryOperations.CreateTable(connection);
                }
            Front.StartUI(connection);
        } catch (SQLException e) {
            System.out.println("Something went wrong with connection to database");
        }
    }
}


