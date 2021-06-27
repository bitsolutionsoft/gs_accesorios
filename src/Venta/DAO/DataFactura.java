package Venta.DAO;

import ClassAux.Util;

import Conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataFactura {



    public ArrayList<Factura> viewFactura(String accion){


        ArrayList<Factura> lista=new ArrayList<>();
        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_factura(?, ?,?)}");
            callableStatement.setInt(1,0);
            callableStatement.setInt(2,0);
            callableStatement.setString(7,accion);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                Factura factura=new Factura();
                factura.setIdfactura(resultSet.getInt("idfactura"));
                factura.setIdfactura(resultSet.getInt("idfactura"));

                factura.setEstado(resultSet.getString("estado"));
                factura.setMotivo_anulacion(resultSet.getString("motivo_anulacion"));
                factura.setFecha(resultSet.getString("fecha"));
                factura.setTotal(resultSet.getFloat("total"));
                lista.add(factura);
            }

            callableStatement.close();
            conexion.con.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Util.Error("Factura","No fue posible ingresar algo salio mal: "+throwables);
        }

        return lista;
    }

    public void crudFactura(Factura factura, String accion){


        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_factura(?,?,?)}");
            callableStatement.setInt(1,factura.idfactura);
            callableStatement.setInt(2,factura.idcliente);
            callableStatement.setString(3,accion);

            callableStatement.execute();
            System.out.println("Registrado con exito");

            callableStatement.close();
            conexion.con.close();


        } catch (SQLException throwables) {
            // throwables.printStackTrace();
            if(accion.equals("delete")) {
                Util.Error("factura", "No fue posible realizar la opreacion algo salio mal: " + throwables);
            }
        }


    }


}
