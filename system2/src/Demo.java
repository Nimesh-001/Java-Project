import java.sql.Connection;

public class Demo {
    public static void main(String[] args) {
        Dbconnector dbc = new Dbconnector();
        Connection con = dbc.getConnection();

        if (con != null) {
            System.out.println("Connection object is ready to use.");
        } else {
            System.out.println("Connection object is null.");
        }
    }
}
