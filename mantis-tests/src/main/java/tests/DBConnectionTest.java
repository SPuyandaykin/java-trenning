package tests;

import model.UserData;
import model.Users;
import org.testng.annotations.Test;

import java.sql.*;

public class DBConnectionTest {

    @Test
    public void testDBConnection (){
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker_1_2?user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select username, email, access_level from mantis_user_table");
            Users users = new Users();
            while (rs.next()) {
                users.add(new UserData().withName(rs.getString("username"))
                        .withEmail(rs.getString("email"))
                        .withAccess(rs.getInt("access_level")));
            }
            rs.close();
            st.close();
            conn.close();
            System.out.println(users);

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
