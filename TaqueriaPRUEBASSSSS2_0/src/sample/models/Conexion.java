/*package sample.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static String server = "127.0.0.1";
    private static String user   = "gabosj";
    private static String pwd    = "852741";
    private static String bd     = "taqueriadb";

    public static Connection conexion;
    public static void crearConexion(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mariadb://"+server+":3306/"+bd,user,pwd);
            System.out.println("Conexion a la bd establecida....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/
package sample.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static String server = "127.0.0.1";
    private static String user   = "adminTacos2";
    private static String pwd    = "123";
    private static String bd     = "taqueria2";
    Connection con;
    public static Connection conexion;
    public static void crearConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://"+server+":3306/"+bd+
                            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=" +
                            "false&serverTimezone=UTC",user,pwd);
            System.out.println("Conexion a la bd establecida....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection(){
        try {
            String myBD =    "jdbc:mysql://localhost:3306/taqueria2?serverTimezone=UTC";
            // String myBD = "jdbc:mysql://localhost:3306/restaurante";
            con = DriverManager.getConnection(myBD, "admin", "123");
            return con;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }
}

