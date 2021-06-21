package Cliente.DAO;

import ClassAux.Util;
import Conexion.Conexion;
import Producto.DAO.Producto;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataCliente {


    public ArrayList<Cliente> viewCliente(String accion){


        ArrayList<Cliente> lista=new ArrayList<>();
        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_cliente(?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setInt(1,0);
            callableStatement.setString(2,"");
            callableStatement.setString(3,"");
            callableStatement.setInt(4,0);
            callableStatement.setInt(5,0);
            callableStatement.setString(6,"");
            callableStatement.setString(7,accion);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                Cliente cliente=new Cliente();
                cliente.setCodigo(resultSet.getInt("idcliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setTelefonoUno(resultSet.getInt("TelefonoUno"));
                cliente.setTelefonoDos(resultSet.getInt("TelefonoDos"));
                cliente.setSexo(resultSet.getString("sexo"));
                lista.add(cliente);
            }

            callableStatement.close();
            conexion.con.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lista;
    }
    public void crudCliente(Cliente cliente, String accion){


        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_cliente(?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setInt(1,cliente.codigo);
            callableStatement.setString(2,cliente.nombre);
            callableStatement.setString(3,cliente.apellido);
            callableStatement.setInt(4,cliente.telefonoUno);
            callableStatement.setInt(5,cliente.telefonoDos);
            callableStatement.setString(6,cliente.sexo);
            callableStatement.setString(7,accion);

            callableStatement.execute();
            System.out.println("Registrado con exito");

            callableStatement.close();
            conexion.con.close();

        } catch (SQLException throwables) {
           // throwables.printStackTrace();
            if(accion.equals("delete")) {
                Util.Error("Cliente", "No se puede eliminar por que esta relacionado aotro registro:" + throwables);
            }
        }


    }
}
