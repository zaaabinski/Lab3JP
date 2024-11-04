package org.BackEnd;

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
            Front.StartUI();
        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }
    }
}


