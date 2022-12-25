import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sam","postgres","Cam25264244");
        Statement st = con.createStatement();
        /*
        Bir SQL Query olusturup, parametize edecegiz, PreparedStatement interface, birden cok kez calistirilabilen onceden
        derlenmis bir SQL kodunu temsil eder.Parametrelendirilmis SQL Query leri ile calisir. Bu sorguyu sifir yada daha fazzla
        parametre ile kullanabiliriz
         */
        ////1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        //1.Adim PreparedStatement query sini olustur
        String sql1="update companies set number_of_employees=? where company=?";
        //2.Adim PreparedStatement objesi olustur
        PreparedStatement pst1=con.prepareStatement(sql1);
        //3.Adim  setInt(), setString(),... methodlari kullanarak soru isratleri yerine deger tanimlayacagiz
        pst1.setInt(1,9999);
        pst1.setString(2,"IBM");
        //4.Adim Execute Query sorguyu calistir
        int guncellenenSatirSayisi=pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi ="+ guncellenenSatirSayisi);

        String sql2="select * from companies";
        ResultSet rs1=st.executeQuery(sql2);
        while (rs1.next()){
            System.out.println(rs1.getInt(1)+"--"+rs1.getString(2)+"--"+rs1.getInt(3));
        }
        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.
        pst1.setInt(1,5555);
        pst1.setString(2,"GOOGLE");
        int guncellenenSatirSayisi2=pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi2 ="+ guncellenenSatirSayisi2);
        ResultSet rs2=st.executeQuery(sql2);
        while (rs2.next()){
            System.out.println(rs2.getInt(1)+"--"+rs2.getString(2)+"--"+rs2.getInt(3));
        }
        con.close();
        st.close();
        rs1.close();
        rs2.close();
        pst1.close();




    }
}
