package Colocacion.DAO;

import ClassAux.Util;
import Conexion.Conexion;
import Proveedor.DAO.Proveedor;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataColocacion {
    public ArrayList<Colocacion> viewColocacion(String accion){


        ArrayList<Colocacion> lista=new ArrayList<>();
        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_colocacion(?, ?, ?, ?)}");
            callableStatement.setInt(1,0);
            callableStatement.setString(2,"");
            callableStatement.setString(3,"");

            callableStatement.setString(4,accion);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                Colocacion colocacion=new Colocacion();
                colocacion.setIdColocacion(resultSet.getInt("idColocacion"));
                colocacion.setNombre(resultSet.getString("nombre"));
                colocacion.setEstado(resultSet.getString("estado"));

                lista.add(colocacion);
            }

            callableStatement.close();
            conexion.con.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lista;
    }

    public void crudColocacion(Colocacion colocacion, String accion){


        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_colocacion(?, ?, ?, ?)}");
            callableStatement.setInt(1,colocacion.getIdColocacion());
            callableStatement.setString(2,colocacion.getNombre());
            callableStatement.setString(3,colocacion.getEstado());


            callableStatement.setString(4,accion);

            callableStatement.execute();
            Util.Exito("Operacion","Realizado con exito:");

            callableStatement.close();
            conexion.con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

}
