import java.sql.Connection;

public class Demo {
    public static void main(String[] args) {
        Dbconnector dbc = new Dbconnector();
        Connection con = dbc.getConnection();

        
    }
}
