package FileLogger;

import java.sql.*;
public class DBConnect {

	static Connection con=null;
    static String ip = "localhost:3306",db = "filetracker",un = "root",pass = "pass";
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    public static  Connection ConnectDB(){
        String ConnectionURL = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
           // ConnectionURL = "jdbc:jtds:sqlserver://" + ip +";databaseName="+ db + ";user=" + un+ ";password=" + pass + ";";
            con = DriverManager
                    .getConnection("jdbc:mysql://localhost/filetracker?"
                            + "user=root&password=pass");
            statement = con.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("show tables");
            //con = DriverManager.getConnection(ConnectionURL);
            System.out.println(resultSet); 
        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
}

	

