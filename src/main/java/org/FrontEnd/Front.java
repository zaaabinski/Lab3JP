package org.FrontEnd;

import org.BackEnd.DatabaseConnection;
import org.BackEnd.QueryOperations;
import org.BackEnd.TrendLine;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class Front {

    public static void StartUI(Connection connection) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            ClearConsole();
            System.out.println("Chose one option : ");
            System.out.println("1. Display opinions ");
            System.out.println("2. Add new opinion ");
            System.out.println("3. Delete opinion ");
            System.out.println("4. Display trend");
            System.out.println("5. Close application");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Display(connection);
                    break;
                case 2:
                    Adding(connection);
                    break;
                case 3:
                    System.out.print("Which opinion would you like to delete? ");
                    int idToDelete = sc.nextInt();
                    Deleting(connection, idToDelete);
                    break;
                case 4:
                    System.out.print("Set staff number for trend line: ");
                    int staffNumber = sc.nextInt();
                    System.out.print("Set start date of the range:  ");
                    String startDate = sc.next();
                    System.out.print("Set end date of the range: ");
                    String endDate = sc.next();
                    DisplayTrendLine(connection, staffNumber,startDate, endDate);
                case 5:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    private static void Adding(Connection connection) {
        int staffNumber;
        String dateOfOpinion;
        String statusCheck;
        boolean status = false;
        int wage;
        String comment;
        Scanner sc = new Scanner(System.in);
        System.out.println("Adding opinion");
        System.out.println("To add an opinion fill given variables with proper data ");

        try {
            System.out.print("Enter staff number (number value): ");
            staffNumber = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter date of opinion (YYYY-MM-DD) : ");
            dateOfOpinion = sc.next();
            sc.nextLine();

            System.out.print("Enter wage of opnion (number value 1-10): ");
            wage = sc.nextInt();
            if(wage>10)
                wage=10;
            else if(wage<=0)
                wage=1;
            sc.nextLine();

            System.out.print("Enter comment : ");
            comment = sc.nextLine();


            System.out.print("Enter status (positive/negative): ");
            statusCheck = sc.nextLine();


            if (statusCheck.equalsIgnoreCase("positive"))
                status = true;
            else if (statusCheck.equalsIgnoreCase("negative"))
                status = false;

            System.out.println("Add opinion? Y/N  : " + staffNumber + " " + dateOfOpinion + " " + wage + " " + comment + " " + statusCheck);
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("y")) {
                try {
                    QueryOperations.InsertToBase(staffNumber, dateOfOpinion, status, wage, comment, connection);
                    System.out.println("Added opinion for staff " + staffNumber);
                } catch (SQLException e) {
                    System.out.println("Something went wrong when inserting to query");
                }
            } else {
                System.out.println("Adding canceled");
            }
        } catch (Exception e) {
            System.out.println("Use proper format for the data");
        }
    }

    private static void Display(Connection connection) {
        Scanner sc = new Scanner(System.in);
        try {
            ArrayList<String> Base = QueryOperations.ShowBase(connection);
            for(String line : Base) {
                System.out.println(line);
            }
            sc.nextLine();
        } catch (SQLException e) {
            System.out.println("Something went wrong when displaying the table");
            sc.nextLine();
        }
    }

    private static void DisplayTrendLine(Connection connection, int staffNumber,String dateOne, String dateTwo)
    {
        int trend = TrendLine.GetTrend(connection,staffNumber,dateOne, dateTwo);
        System.out.println("For staff number " + staffNumber + " his trend is equal to " + trend);
    }

    private static void Deleting(Connection connection, int idToDelete) {
        try {
            QueryOperations.DeleteFromBase(idToDelete, connection);
            System.out.println("Deleted opinion with id " + idToDelete);
        } catch (SQLException e) {
            System.out.println("Something went wrong when deleting from the table");
        }
    }

    private static void ClearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
