import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sam","postgres","Cam25264244");
        Statement st = con.createStatement();

        String sql1="UPDATE companies\n" +
                "SET number_of_employees=16000\n" +
                "WHERE number_of_employees<(SELECT AVG (number_of_employees) FROM companies);";
        int updateEdilenSatirSayisi=st.executeUpdate(sql1);
        System.out.println(updateEdilenSatirSayisi);
        ResultSet resultSet1= st.executeQuery("select * from companies");
        while (resultSet1.next()){
            System.out.println(resultSet1.getInt(1)+" "+resultSet1.getString(2)+" "+resultSet1.getInt(3));
        }
        con.close();
        st.close();
        resultSet1.close();
    }
}
