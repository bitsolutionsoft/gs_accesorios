package Informe.DAO;

import Conexion.Conexion;
import Proveedor.DAO.Proveedor;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataVentas {
    public ArrayList<ResumenVenta> viewVentas(Ventas ventas,String accion){


        ArrayList<ResumenVenta> lista=new ArrayList<>();
        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call consulta_ventas(?, ?, ?, ?)}");
            callableStatement.setInt(1,ventas.getIndventa());
            callableStatement.setString(2, ventas.getfInicial());
            callableStatement.setString(3,ventas.getfFinal());
            callableStatement.setString(4,accion);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                ResumenVenta resumenVenta=new ResumenVenta();
                resumenVenta.setIdfactura(resultSet.getInt("idfactura"));
                resumenVenta.setFecha(resultSet.getString("fecha"));
                resumenVenta.setCliente(resultSet.getString("cliente"));
                resumenVenta.setTotal(resultSet.getInt("total"));

                lista.add(resumenVenta);
            }

            callableStatement.close();
            conexion.con.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lista;
    }

    public ArrayList<Cuenta> viewCuenta(Ventas ventas, String accion){


        ArrayList<Cuenta> lista=new ArrayList<>();
        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call consulta_ventas(?, ?, ?, ?)}");
            callableStatement.setInt(1,ventas.getIndventa());
            callableStatement.setString(2, ventas.getfInicial());
            callableStatement.setString(3,ventas.getfFinal());
            callableStatement.setString(4,accion);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                Cuenta cuenta=new Cuenta();
                cuenta.setVentas(resultSet.getFloat("ventas"));
                cuenta.setInversion(resultSet.getFloat("compra"));
                cuenta.setGanancia(resultSet.getFloat("ganancia"));
                lista.add(cuenta);
            }

            callableStatement.close();
            conexion.con.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lista;
    }

}

