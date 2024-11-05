package org.BackEnd;

import java.sql.*;
import java.util.ArrayList;

public class TrendLine {

    public static int GetTrend(Connection connection, int staffNumber, String start, String end)
    {
        int trend=0;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM OPINIONS where opinionDate between ? and ? and staffNumber = ?");
            Date startDate = Date.valueOf(start);
            Date endDate = Date.valueOf(end);

            statement.setDate(1, startDate);
            statement.setDate(2, endDate);
            statement.setInt(3, staffNumber);
            ResultSet rs = statement.executeQuery();

            while(rs.next())
            {
               if(rs.getBoolean("opinionStatus"))
               {
                   trend+=rs.getInt("wage");
               }
               else {
                   trend-=rs.getInt("wage");
               }
            }
            return trend;

        }catch (SQLException e) {
            System.out.println("Error with base");
        }
        return 0;
    }
}
