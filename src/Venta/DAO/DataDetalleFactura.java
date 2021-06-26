package Venta.DAO;

import ClassAux.Util;

import Conexion.Conexion;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataDetalleFactura {


    public ArrayList<DetalleFactura> viewDetalleFactura(String accion) {


        ArrayList<DetalleFactura> lista = new ArrayList<>();
        try {
            Conexion conexion = new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement = conexion.con.prepareCall("{call ingreso_detallefactura(?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setInt(1, 0);
            callableStatement.setInt(2, 0);
            callableStatement.setInt(3, 0);
            callableStatement.setInt(4, 0);
            callableStatement.setFloat(5, 0);
            callableStatement.setString(6, "");
            callableStatement.setString(7, accion);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                DetalleFactura DetalleFactura = new DetalleFactura();
                DetalleFactura.setIddetalle(resultSet.getInt("iddetalle"));
                DetalleFactura.setIdfatura(resultSet.getInt("id_factura"));
                DetalleFactura.setIdproducto(resultSet.getInt("id_producto"));
                DetalleFactura.setCantidad(resultSet.getInt("cantidad"));
                DetalleFactura.setPrecio(resultSet.getFloat("precio"));
                DetalleFactura.setSubtotal(resultSet.getFloat("subtotal"));
                DetalleFactura.setIdlote(resultSet.getInt("idlote"));

                lista.add(DetalleFactura);
            }

            callableStatement.close();
            conexion.con.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lista;
    }

    public void crudDetalleFactura(DetalleFactura detalleFactura, String accion) {


        try {
            Conexion conexion = new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement = conexion.con.prepareCall("{call ingreso_detallefactura(?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setInt(1, detalleFactura.iddetalle);
            callableStatement.setInt(2, detalleFactura.idfatura);
            callableStatement.setInt(3, detalleFactura.idproducto);
            callableStatement.setInt(4, detalleFactura.cantidad);
            callableStatement.setFloat(5, detalleFactura.precio);
            callableStatement.setFloat(6, detalleFactura.idlote);
            callableStatement.setString(7, accion);

            callableStatement.execute();
            Util.Exito("Venta ", "Operacion realizada con exito: " );

            callableStatement.close();
            conexion.con.close();

        } catch (SQLException throwables) {
            // throwables.printStackTrace();
            if (accion.equals("delete")) {
                Util.Error("DetalleFactura", "No se puede eliminar por que esta relacionado aotro registro:" + throwables);
            }
        }


    }


}
