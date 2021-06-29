package Informe.DAO;

import Conexion.Conexion;
import Venta.DAO.ProductoDisponible;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataProductoInvetario {

    public ArrayList<ProductoInvetario> productoInventario() {
        Conexion conexion =new Conexion();
        Connection connection= conexion.Conexion();
        ArrayList<ProductoInvetario> datos = new ArrayList<>();
        String sql = "select * from vista_producto_invetario";

        try {
            conexion.Conexion();
            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);


            while (resultSet.next()) {
                ProductoInvetario pro=new ProductoInvetario();
                pro.setCodigo(resultSet.getInt("idproducto"));
                pro.setNombre(resultSet.getString("producto"));
                pro.setCantidad(resultSet.getInt("stock"));
                pro.setPrecio(resultSet.getFloat("precio_compra"));
                pro.setSubtotal(resultSet.getFloat("subtotal"));
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
}
