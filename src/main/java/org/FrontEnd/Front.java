package org.FrontEnd;

import org.BackEnd.QueryOperations;
import org.BackEnd.Trend;

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
                    Display(connection, sc);
                    break;
                case 2:
                    Adding(connection,sc);
                    break;
                case 3:
                    Deleting(connection,sc);
                    break;
                case 4:
                    DisplayTrend(connection,sc);
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    private static void Adding(Connection connection,Scanner sc) {
        int staffNumber;
        String dateOfOpinion;
        String statusCheck;
        boolean status = false;
        int wage;
        String comment;

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

            if (wage > 10)
                wage = 10;
            else if (wage <= 0)
                wage = 1;

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
                    sc.nextLine();
                } catch (SQLException e) {
                    System.out.println("Something went wrong when inserting to query");
                }
            } else {
                System.out.println("Adding canceled");
                sc.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Use proper format for the data");
        }
    }

    private static void Display(Connection connection, Scanner sc) {
        try {
            ArrayList<String> Base = QueryOperations.ShowBase(connection);
            for (String line : Base) {
                System.out.println(line);
            }
            sc.nextLine();
            sc.nextLine();
        } catch (SQLException e) {
            System.out.println("Something went wrong when displaying the table");
            sc.nextLine();
            sc.nextLine();
        }
    }

    private static void DisplayTrend(Connection connection,Scanner sc) {
         System.out.print("Set staff number for trend line: ");
        int staffNumber = sc.nextInt();
        System.out.print("Set start date of the range:  ");
        String startDate = sc.next();
        System.out.print("Set end date of the range: ");
        String endDate = sc.next();
        String status;

        int trend = Trend.GetTrend(connection, staffNumber, startDate, endDate);
        if (trend > 0)
            status = "positive";
        else
            status = "negative";
        System.out.println("For staff number " + staffNumber + " his trend is equal to " + trend + " which is " + status);
        sc.nextLine();
        sc.nextLine();
    }

    private static void Deleting(Connection connection,Scanner sc) {
        System.out.print("Which opinion would you like to delete? ");
        int idToDelete = sc.nextInt();
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
