import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnector {

    public Connection getConnection(){

        Connection con = null;

        try {
            String url = "jdbc:mysql://localhost:3306/TecMIS_DB";
            String user = "root";
            String password = "1234";

            con = DriverManager.getConnection(url,user,password);
            System.out.println("Connected to database successfully");
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            System.out.println(e.getMessage());

        }


        return con;
    }
}
