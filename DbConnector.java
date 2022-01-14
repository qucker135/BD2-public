import java.sql.*;

public class DbConnector {

    private static String URL = "jdbc:mysql://localhost/BazaSklepu";

    private static String USER = "pracownik";

    private static String PASS = "password";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            //System.out.println("Połączono");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    public static ResultSet executeSelectQuery(String query){
        try{
            Connection connection = DbConnector.connect();
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        }catch(SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public static void executeQuery(String query){
        try{
            Connection connection = DbConnector.connect();
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        }catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public static ResultSet executeSelectQueryToConnection(Connection connection, String query){
        try{
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        }catch(SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public static void executeQueryToConnection(Connection connection, String query){
        try{
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        }catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
