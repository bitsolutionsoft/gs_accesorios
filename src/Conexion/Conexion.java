package Conexion;
import  java.sql.*;

public class Conexion {
    public Connection con =null;
    public  Connection Conexion(){
        try {
            String server="localhost:3306";
            String db="gsaccesorios";
            String user="root";
            String pass="49390508";
            //String pass="root12345";
            String horario="useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String Sll="&useSSL=false";
            String conexionstr=String.format("jdbc:mysql://%s/%s?%s%s",server,db,horario,Sll);
            con=DriverManager.getConnection(conexionstr,user,pass);
            System.out.println("conexion establecida");
        }catch (SQLException e){
            System.out.println("error de conexion"+e);
            e.printStackTrace();
        }
        return con;
    }

}
