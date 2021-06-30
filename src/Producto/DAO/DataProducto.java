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
               CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_producto(?, ?, ?, ?,  ?, ?, ?, ?, ?, ?)}");
               callableStatement.setInt(1,0);
               callableStatement.setString(2,"");
               callableStatement.setString(3,"");
               callableStatement.setString(4,"");
               callableStatement.setInt(5,0);
               callableStatement.setInt(6,0);
               callableStatement.setInt(7,0);
               callableStatement.setInt(8,0);

               callableStatement.setString(9,"");
               callableStatement.setString(10,accion);

           ResultSet resultSet = callableStatement.executeQuery();
           while (resultSet.next()){
               Producto producto=new Producto();
               producto.setCodigo(resultSet.getInt("idproducto"));
               producto.setNombre(resultSet.getString("nombre"));
               producto.setModelo(resultSet.getString("modelo"));
               producto.setEspecificacion(resultSet.getString("especificacion"));
               producto.setStock(resultSet.getInt("stock"));
               producto.setPrecio_compra(resultSet.getFloat("precio_compra"));
               producto.setPrecio_mayorista(resultSet.getFloat("precio_mayorista"));
               producto.setPrecio_mayor(resultSet.getFloat("precio_mayor"));
               producto.setPrecio_unidad(resultSet.getFloat("precio_unidad"));
               producto.setMaximo(resultSet.getInt("cantidad_maxima"));
               producto.setMinimo(resultSet.getInt("cantidad_minima"));
               producto.setIdcolocacion(resultSet.getInt("id_colocacion"));
               producto.setIdproveedor(resultSet.getInt("id_proveedor"));
               producto.setEstado(resultSet.getString("estado"));
               producto.setProveedor(resultSet.getString("proveedor"));
               producto.setColocacion(resultSet.getString("colocacion"));
               producto.setIdlote(resultSet.getInt("idlote"));
               producto.setCantidad(resultSet.getInt("cantidad"));
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
            CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_producto(?, ?, ?, ?,  ?, ?, ?, ?, ?, ?)}");
            callableStatement.setInt(1,producto.getCodigo());
            callableStatement.setString(2,producto.getNombre());
            callableStatement.setString(3,producto.getModelo());
            callableStatement.setString(4,producto.getEspecificacion());
            callableStatement.setInt(5,producto.getMaximo());
            callableStatement.setInt(6,producto.getMinimo());
            callableStatement.setInt(7,producto.getIdcolocacion());
            callableStatement.setInt(8,producto.getIdproveedor());
            callableStatement.setString(9,producto.getEstado());
            callableStatement.setString(10,accion);
             callableStatement.execute();
            Util.Exito("Producto","El registro fue realizado con exito");

            callableStatement.close();
            conexion.con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Util.Error("Producto","Algo salio mal revise:"+ throwables);
        }


    }

    public ArrayList<Producto> viewProductoXCol(Producto pr,String accion){


        ArrayList<Producto> lista=new ArrayList<>();
        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_producto(?, ?, ?, ?,  ?, ?, ?, ?, ?, ?)}");
            callableStatement.setInt(1,0);
            callableStatement.setString(2,"");
            callableStatement.setString(3,"");
            callableStatement.setString(4,"");
            callableStatement.setInt(5,0);
            callableStatement.setInt(6,0);
            callableStatement.setInt(7,pr.getIdcolocacion());
            callableStatement.setInt(8,0);

            callableStatement.setString(9,"");
            callableStatement.setString(10,accion);


            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                Producto producto=new Producto();
                producto.setNombre(resultSet.getString("nombre"));
                producto.setModelo(resultSet.getString("modelo"));
                producto.setEspecificacion(resultSet.getString("especificacion"));
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
}
