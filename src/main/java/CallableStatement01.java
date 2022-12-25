import java.sql.*;

public class CallableStatement01 {
    /*
    Java da methodlar return type sahibi olsada olmasada method olarak adlandirilir.
    SQL de ise data return ediyorsa "function" denir. Return yapmiyorsa "procedure" olarak adlandirilir.
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sam","postgres","Cam25264244");
        Statement st = con.createStatement();

        //CallableStatement ile function cagirmayi parametrelendirecegiz.
        //1.Adim: function kodunu yaz
        String sql1="create or replace function  toplamaF(x NUMERIC,y NUMERIC)\n" +
                "returns NUMERIC\n" +
                "language plpgsql\n" +
                "as \n" +
                "$$\n" +
                "begin\n" +
                "return x+y;\n" +
                "end\n" +
                "$$";
        //2.Adim: Function i calistir
        st.execute(sql1);
        //3.Adim: FunFunction i cagir
        CallableStatement cst1= con.prepareCall("{?= call toplamaF(?,?)}");//Ilk parametre (ilk soru isareti ilk parametre)return type
        //4.Adim: Return icin registerOutParameter() methodunu, parametreler icin ise set() methodlarini uygula
        cst1.registerOutParameter(1,Types.NUMERIC);
        cst1.setInt(2,6);
        cst1.setInt(3,4);
        //5.Adim: execute() methodu ile callableStatement i calistir
        cst1.execute();
        //6.Adim: Sonucu cagirmak icin return data type tipine gore
        System.out.println(cst1.getBigDecimal(1));

        System.out.println("-------------------------------------");
        //2. Örnek: Koninin hacmini hesaplayan bir function yazın.
        String sql2="create or replace function  konininHacmiF(r NUMERIC,h NUMERIC)\n" +
                "returns NUMERIC\n" +
                "language plpgsql\n" +
                "as \n" +
                "$$\n" +
                "begin\n" +
                "return 3.14*r*r*h/3;\n" +
                "end\n" +
                "$$";
        st.execute(sql2);
        CallableStatement cst2= con.prepareCall("{?= call konininHacmiF(?,?)}");
        cst2.registerOutParameter(1,Types.NUMERIC);
        cst2.setInt(2,1);
        cst2.setInt(3,6);
        cst2.execute();
        System.out.printf("%.2f",cst2.getBigDecimal(1));//printf-->"%.2f" istedigimiz sayidan sonra 2 basamak gelir


    }
}
