package Producto.DAO;


import ClassAux.Util;
import Conexion.Conexion;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataLote {

    public ArrayList<Lote> viewLote( String accion){


        ArrayList<Lote> lista=new ArrayList<>();
        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_lote(?, ?, ?,?, ?, ?, ?, ?, ?)}");
            callableStatement.setInt(1,0);
            callableStatement.setInt(2,0);
            callableStatement.setInt(3,0);
            callableStatement.setFloat(4,0);
            callableStatement.setFloat(5,0);
            callableStatement.setFloat(6,0);
            callableStatement.setFloat(7,0);
            callableStatement.setString(8,"");
            callableStatement.setString(9,accion);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                Lote lote=new Lote();
                lote.setIdlote(resultSet.getInt("idlote"));
                lote.setIdproducto(resultSet.getInt("idproducto"));
                lote.setCantidad(resultSet.getInt("cantidad"));
                lote.setPrecio_compra(resultSet.getFloat("precio_compra"));
                lote.setPrecio_mayorista(resultSet.getFloat("precio_mayorista"));
                lote.setPrecio_mayor(resultSet.getFloat("precio_mayor"));
                lote.setPrecio_unidad(resultSet.getFloat("precio_unidad"));
                lote.setEstado(resultSet.getString("estado"));
                lista.add(lote);
            }

            callableStatement.close();
            conexion.con.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Util.Error("Lote", "Algo salio mal no se pudo consultar: "+throwables);
        }

        return lista;
    }

    public ArrayList<Lote> returnLote( int id,String accion){


        ArrayList<Lote> lista=new ArrayList<>();
        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_lote(?, ?, ?,?, ?, ?, ?, ?, ?)}");
            callableStatement.setInt(1,id);
            callableStatement.setInt(2,0);
            callableStatement.setInt(3,0);
            callableStatement.setFloat(4,0);
            callableStatement.setFloat(5,0);
            callableStatement.setFloat(6,0);
            callableStatement.setFloat(7,0);
            callableStatement.setString(8,"");
            callableStatement.setString(9,accion);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                Lote lote=new Lote();
                lote.setIdlote(resultSet.getInt("idlote"));
                lote.setIdproducto(resultSet.getInt("idproducto"));
                lote.setCantidad(resultSet.getInt("cantidad"));
                lote.setPrecio_compra(resultSet.getFloat("precio_compra"));
                lote.setPrecio_mayorista(resultSet.getFloat("precio_mayorista"));
                lote.setPrecio_mayor(resultSet.getFloat("precio_mayor"));
                lote.setPrecio_unidad(resultSet.getFloat("precio_unidad"));
                lote.setEstado(resultSet.getString("estado"));
                lista.add(lote);
            }

            callableStatement.close();
            conexion.con.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Util.Error("Lote", "Algo salio mal no se pudo consultar: "+throwables);
        }

        return lista;
    }
    public void crudLote(Lote lote, String accion){


        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_lote(?, ?, ?,?, ?, ?, ?, ?, ?)}");
            callableStatement.setInt(1,lote.getIdlote());
            callableStatement.setInt(2,lote.getIdproducto());
            callableStatement.setInt(3,lote.getCantidad());
            callableStatement.setFloat(4,lote.getPrecio_compra());
            callableStatement.setFloat(5,lote.getPrecio_mayorista());
            callableStatement.setFloat(6,lote.getPrecio_mayor());
            callableStatement.setFloat(7,lote.getPrecio_unidad());
            callableStatement.setString(8,lote.getEstado());
            callableStatement.setString(9,accion);
            callableStatement.execute();

            Util.Exito("Lote","Se ingreso correctamenta");

            callableStatement.close();
            conexion.con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Util.Error("Lote", "Algo salio mal no se pudo registrar: "+throwables);
        }


    }
}
