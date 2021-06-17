package Cliente.DAO;

import Conexion.Conexion;
import Producto.DAO.Producto;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Data {


    public ArrayList<Cliente> viewCliente(String accion){


        ArrayList<Cliente> lista=new ArrayList<>();
        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_cliente(?, ?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setInt(1,0);
            callableStatement.setString(2,"");
            callableStatement.setString(3,"");
            callableStatement.setInt(4,0);
            callableStatement.setInt(5,0);
            callableStatement.setInt(6,0);
            callableStatement.setString(7,"");
            callableStatement.setString(8,accion);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                Cliente cliente=new Cliente();
                cliente.setCodigo(resultSet.getInt("idcliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setTelefonoUno(resultSet.getInt("TelefonoUno"));
                cliente.setTelefonoDos(resultSet.getInt("TelefonoDos"));
                cliente.setIdtipo(resultSet.getInt("idtipo"));
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
            CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_cliente(?, ?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setInt(1,cliente.codigo);
            callableStatement.setString(2,cliente.nombre);
            callableStatement.setString(3,cliente.apellido);
            callableStatement.setInt(4,cliente.telefonoUno);
            callableStatement.setInt(5,cliente.telefonoDos);
            callableStatement.setInt(6,cliente.idtipo);
            callableStatement.setString(7,cliente.sexo);
            callableStatement.execute();
            System.out.println("Registrado con exito");

            callableStatement.close();
            conexion.con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
