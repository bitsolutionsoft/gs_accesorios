package Proveedor;

import ClassAux.Util;
import Cliente.DAO.Cliente;
import Cliente.DAO.DataCliente;
import Colocacion.DAO.Colocacion;
import Producto.DAO.DataLote;
import Producto.DAO.DataProducto;
import Producto.DAO.Lote;
import Producto.DAO.Producto;
import Proveedor.DAO.DataProveedor;
import Proveedor.DAO.Proveedor;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class FormProveedor implements Initializable {

    public Button btnIngresarProveedor;

    public CheckBox chbActivo;
    public CheckBox chbNoActivo;
    public TextField txtCodigo;
    public TextField txtNombre;
    public TextField txtApellido;
    public TextField txtTelefonouno;
    public TextField txtTelefonodos;
    public TextField txtCompania;
    public TextField txtDireccion;


    public Label labelTitulo;
    public CheckBox chboxHombre;
    public CheckBox chboxMujer;
    private String accion = "new"; // por default es new
    private String estado = "Activo";
private  String sexo="Hombre";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        estado("Activo");
        labelTitulo.setText("Ingresar Producto");
definirSexo();
        //iniciar_combo_pro_col(0,0);
/*
        txtPrecioMayorista.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(!txtPrecioCompra.getText().isEmpty() && !txtPrecioMayorista.getText().isEmpty()
                ){
                    if (Float.parseFloat( txtPrecioCompra.getText()) > Float.parseFloat(txtPrecioMayorista.getText())){
                        Util.Error("precio", "El precio ingresado debe ser mayor que el precio de compra");
                    }
                }
            }
        });*/
    }

    public void pasarRegistro(Proveedor proveedor) {
        if (proveedor != null) {
            accion = "update";
            labelTitulo.setText("Modificar datos del proveedor");
            txtCodigo.setText(String.valueOf(proveedor.getIdProveedor()));
            txtCodigo.setEditable(false); ///
            txtNombre.setText(proveedor.getNombre());
            txtApellido.setText(proveedor.getApellido());
            txtTelefonouno.setText(String.valueOf(proveedor.getTelefonoUno()));
            txtTelefonodos.setText(String.valueOf(proveedor.getTelefonoDos()));
            txtCompania.setText(String.valueOf(proveedor.getCompania()));
            txtDireccion.setText(proveedor.getDireccion());
            sexo=proveedor.getSexo();
            definirSexo();

            estado(proveedor.getEstado()); // ya esta jajaja
            btnIngresarProveedor.setText("Actualizar Proveedor"); //no has cambiado el id del boton

        }
    }

    public void estado(String estado) {
        if (estado.equals("Activo")) {
            chbActivo.setSelected(true);
            chbNoActivo.setSelected(false);
        } else {
            chbNoActivo.setSelected(true);
            chbActivo.setSelected(false);
        }

    }

    public void RegistrarProveedor(ActionEvent actionEvent) {
        switch (accion) {
            case "new":
                guardarProveedor("new");
                break;
            case "update":
                guardarProveedor("update");
                break;

        }
    }

    public void guardarProveedor(String accion) {
        if (returnProveedor() != null) {
            DataProveedor dataProveedor = new DataProveedor();
            dataProveedor.crudProveedor(returnProveedor(), accion);
            limpiar();
        }


    }


    public void estadoActivo(ActionEvent actionEvent) {
        if (chbActivo.isSelected()) {
            estado = "Activo";
            chbNoActivo.setSelected(false);
        }
    }

    public void estadoNoActivo(ActionEvent actionEvent) {
        if (chbNoActivo.isSelected()) {
            estado = "No Activo";
            chbActivo.setSelected(false);
        }
    }

    public Proveedor returnProveedor() {
        Proveedor proveedor = new Proveedor();
        if (txtCodigo.getText().isEmpty()) {
            proveedor.setIdProveedor(0);
            proveedor.setSexo(sexo);
            proveedor.setEstado(estado);
        } else {
            proveedor.setIdProveedor(Integer.parseInt(txtCodigo.getText()));
            proveedor.setSexo(sexo);
            proveedor.setEstado(estado);
        }
        if (!txtCompania.getText().isEmpty()){ proveedor.setCompania(txtCompania.getText()); }else{proveedor.setCompania("");} //hace para los campos no obligatorio asi com esta este
        if (!txtDireccion.getText().isEmpty()){ proveedor.setDireccion(txtDireccion.getText()); }else{proveedor.setDireccion("");}
//jajaja proba otara vez no dio
        ////campos obligatorios
        if (txtNombre.getText().isEmpty()) {
            Util.Error("Error", "El campo  nombre esta vacío");
            return null;
        } else {
            proveedor.setNombre(txtNombre.getText());

            if (txtApellido.getText().isEmpty()) {
                Util.Error("Error", "El campo  apellido esta vacío");
                return null;
            } else {
                proveedor.setApellido(txtApellido.getText());

                if(txtTelefonouno.getText().isEmpty()){
                    Util.Error("Error","El cambo telefono uno esta vacio");
                    return null;
                } else {
                    proveedor.setTelefonoUno(Integer.parseInt(txtTelefonouno.getText()));
                    return  proveedor;

                }

            }

        }


    }
    public  void limpiar() {
        txtCodigo.setText("");
            txtNombre.setText("");
        txtApellido.setText("");
        txtTelefonouno.setText("");
        txtTelefonodos.setText("");
        txtDireccion.setText("");
    sexo="Hombre";
    definirSexo();
        txtCompania.setText("");

        estado("Activo");// VOS Y ESTADO COMO LE HAGO
    }

    public void SeleccionarHombre(ActionEvent actionEvent) {
        if (chboxHombre.isSelected()){
            chboxMujer.setSelected(false);
            sexo="Hombre";
        }
    }

    public void SeleccionarMujer(ActionEvent actionEvent) {
        if (chboxMujer.isSelected()){
            chboxHombre.setSelected(false);
            sexo="Mujer";
        }
    }
    public void definirSexo(){
        if (sexo.equals("Hombre")){
            chboxHombre.setSelected(true);
            chboxMujer.setSelected(false);
        }else {

            chboxHombre.setSelected(false);
            chboxMujer.setSelected(true);
        }

    }
}
