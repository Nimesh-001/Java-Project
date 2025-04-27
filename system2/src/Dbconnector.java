import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnector {

    public Connection getConnection(){

        Connection con = null;

        try {
            //String url = "jdbc:mysql://localhost:3306/TecMIS_DB";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            String url = "jdbc:mysql://localhost:3306/TecMIS_DB";
            String user = "root";
            String password = "12345";

            con = DriverManager.getConnection(url,user,password);
            System.out.println("Connected to database successfully");
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            System.out.println(e.getMessage());

        }


        return con;
    }
}
