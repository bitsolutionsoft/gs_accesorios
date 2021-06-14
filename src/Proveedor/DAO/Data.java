package Proveedor.DAO;

import Conexion.Conexion;


import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Data {
    public ArrayList<Proveedor> viewProveedor(String accion){


        ArrayList<Proveedor> lista=new ArrayList<>();
        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_proveedor(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);}");
            callableStatement.setInt(1,0);
            callableStatement.setString(2,"");
            callableStatement.setString(3,"");
            callableStatement.setInt(4,0);
            callableStatement.setInt(5,0);
            callableStatement.setString(6,"");
            callableStatement.setString(7,"");
            callableStatement.setString(8,"");
            callableStatement.setString(9,"");
            callableStatement.setString(10,accion);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                Proveedor proveedor=new Proveedor();
                proveedor.setIdProveedor(resultSet.getInt("idProveedor"));
                proveedor.setNombre(resultSet.getString("nombre"));
                proveedor.setApellido(resultSet.getString("apellido"));
                proveedor.setTelefonoUno(resultSet.getInt("telefonoUno"));
                proveedor.setTelefonoDos(resultSet.getInt("telefonoDos"));
                proveedor.setCompania(resultSet.getString("compania"));
                proveedor.setDireccion(resultSet.getString("direccion"));
                proveedor.setSexo(resultSet.getString("sexo"));
                proveedor.setEstado(resultSet.getString("estado"));
                lista.add(proveedor);
            }

            callableStatement.close();
            conexion.con.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lista;
    }
    public void crudProveedor(Proveedor proveedor, String accion){


        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_proveedor(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);}");
            callableStatement.setInt(1,proveedor.getIdProveedor());
            callableStatement.setString(2,proveedor.getNombre());
            callableStatement.setString(3,proveedor.getApellido());
            callableStatement.setInt(4,proveedor.getTelefonoUno());
            callableStatement.setInt(5,proveedor.getTelefonoDos());
            callableStatement.setString(6,proveedor.getCompania());
            callableStatement.setString(7,proveedor.getDireccion());
            callableStatement.setString(8, proveedor.getSexo());
            callableStatement.setString(9,proveedor.getEstado());

            callableStatement.setString(10,accion);

            callableStatement.execute();
            System.out.println("Registrado con exito");

            callableStatement.close();
            conexion.con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
