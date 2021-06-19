package Producto.DAO;

import ClassAux.Util;
import Conexion.Conexion;
import Producto.DAO.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataProducto {

   public ArrayList<Producto> viewProducto(String accion){


       ArrayList<Producto> lista=new ArrayList<>();
       try {
           Conexion conexion =new Conexion();

               conexion.Conexion();
               CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_producto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
               callableStatement.setInt(1,0);
               callableStatement.setString(2,"");
               callableStatement.setString(3,"");
               callableStatement.setString(4,"");
               callableStatement.setInt(5,0);
               callableStatement.setFloat(6,0);
               callableStatement.setFloat(7,0);
               callableStatement.setFloat(8,0);
               callableStatement.setFloat(9,0);
               callableStatement.setInt(10,0);
               callableStatement.setInt(11,0);
               callableStatement.setInt(12,0);
               callableStatement.setInt(13,0);
               callableStatement.setString(14,"");
               callableStatement.setString(15,accion);

           ResultSet resultSet = callableStatement.executeQuery();
           while (resultSet.next()){
               Producto producto=new Producto();
               producto.setCodigo(resultSet.getInt("idproducto"));
               producto.setNombre(resultSet.getString("nombre"));
               producto.setModelo(resultSet.getString("modelo"));
               producto.setEspecificacion(resultSet.getString("especificacion"));
               producto.setStock(resultSet.getInt("stock"));
               producto.setPrecio_compra(resultSet.getFloat("precio_compra"));
               producto.setPrecio_mayorista(resultSet.getFloat("precio_v_x_mayorista"));
               producto.setPrecio_mayor(resultSet.getFloat("precio_v_x_mayor"));
               producto.setPrecio_unidad(resultSet.getFloat("precio_v_x_unidad"));
               producto.setMaximo(resultSet.getInt("cantidad_maxima"));
               producto.setMinimo(resultSet.getInt("cantidad_minima"));
               producto.setColocacion(resultSet.getInt("id_colocacion"));
               producto.setProveedor(resultSet.getInt("id_proveedor"));
               producto.setEstado(resultSet.getString("estado"));
               lista.add(producto);
           }

           callableStatement.close();
           conexion.con.close();
           resultSet.close();

       } catch (SQLException throwables) {
           throwables.printStackTrace();
           Util.Error("Producto","Algo salio mal revise:"+ throwables);

       }

       return lista;
   }
    public void crudProducto(Producto producto, String accion){


        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_producto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setInt(1,producto.codigo);
            callableStatement.setString(2,producto.nombre);
            callableStatement.setString(3,producto.modelo);
            callableStatement.setString(4,producto.especificacion);
            callableStatement.setInt(5,producto.stock);
            callableStatement.setFloat(6,producto.precio_compra);
            callableStatement.setFloat(7,producto.precio_mayorista);
            callableStatement.setFloat(8,producto.precio_mayor);
            callableStatement.setFloat(9,producto.precio_unidad);
            callableStatement.setInt(10,producto.maximo);
            callableStatement.setInt(11,producto.minimo);
            callableStatement.setInt(12,producto.colocacion);
            callableStatement.setInt(13,producto.proveedor);
            callableStatement.setString(14,producto.estado);
            callableStatement.setString(15,accion);
             callableStatement.execute();
            Util.Exito("Producto","El registro fue realizado con exito");

            callableStatement.close();
            conexion.con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Util.Error("Producto","Algo salio mal revise:"+ throwables);
        }


    }
}
