package Venta.DAO;

import Conexion.Conexion;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataProDisponible {
    public ArrayList<ProductoDisponible> ProductoDisponible() {
        Conexion conexion =new Conexion();
        Connection connection= conexion.Conexion();
        ArrayList<ProductoDisponible> datos = new ArrayList<>();
        String sql = "select * from mostrar_producto";

        try {
            conexion.Conexion();
            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);


            while (resultSet.next()) {
                ProductoDisponible pro=new ProductoDisponible();
                pro.setIdproducto(resultSet.getInt("idproducto"));
                pro.setNombre(resultSet.getString("nombre") +" "+resultSet.getString("modelo")+" " +resultSet.getString("especificacion"));
                pro.setModelo(resultSet.getString("modelo"));
                pro.setEspecificacion(resultSet.getString("especificacion"));
                pro.setStock(resultSet.getInt("stock"));
                pro.setSector(resultSet.getString("sector"));
                pro.setPrecio(resultSet.getFloat("precio_unidad"));
                pro.setIdlote(resultSet.getInt("idlote"));
                datos.add(pro);
            }
            resultSet.close();

            connection.close();
            statement.close();
            conexion.Conexion().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }


    public int orden() {
        Conexion conexion =new Conexion();
        Connection connection= conexion.Conexion();
        int codigo=0;
        String sql = "select * from numero_orden";

        try {
            conexion.Conexion();
            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);


            while (resultSet.next()) {
              codigo=resultSet.getInt("numero_orden");
            }
            resultSet.close();

            connection.close();
            statement.close();
            conexion.Conexion().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codigo;
    }


}


