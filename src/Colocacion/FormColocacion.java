package Colocacion;

import ClassAux.Formato;

import ClassAux.Util;
import Colocacion.DAO.Colocacion;
import Colocacion.DAO.DataColocacion;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FormColocacion implements Initializable {


    public TextField txtCodigo;
    public TextField txtNombre;


    public Label labelTitulo;


    public Button btnIngresarColoccin;
    public CheckBox chbActivo;
    public CheckBox chbNoActivo;
    private String accion = "new"; // por default es new
    private String estado = "Activo";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        labelTitulo.setText("Ingresar Cliente");
estado("Activo");
        validarFormato();
    }
    public  void validarFormato(){

        txtCodigo.setEditable(false);

    }

    public void pasarRegistro(Colocacion colocacion) {
        if (colocacion != null) {
            accion = "update";
            labelTitulo.setText("Modificar colocacion");
            txtCodigo.setText(String.valueOf(colocacion.getIdColocacion()));
            txtCodigo.setEditable(false); ///
            txtNombre.setText(colocacion.getNombre());
            estado(colocacion.getEstado());
            btnIngresarColoccin.setText("Actualizar Colocación"); //no has cambiado el id del boton

        }
    }


    public void RegistrarColocacion(ActionEvent actionEvent) {
        switch (accion) {
            case "new":
                guardarColocacion("new");
                break;
            case "update":
                guardarColocacion("update");
                break;

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

    public void guardarColocacion(String accion) {
        if (returnColocacion() != null) {
            DataColocacion dataColocacion = new DataColocacion();
            dataColocacion.crudColocacion(returnColocacion(), accion);
            limpiar();
        }


    }




    public Colocacion returnColocacion() {
        Colocacion colocacion = new Colocacion();
        if (txtCodigo.getText().isEmpty()) {
            colocacion.setIdColocacion(0);
            colocacion.setEstado(estado);
        }
        else{
            colocacion.setIdColocacion(Integer.parseInt(txtCodigo.getText()));
            colocacion.setEstado(estado);

        }
        if (txtNombre.getText().isEmpty())
        {
            Util.Error("Error","Llene al campo colocaion");
            return  null;
        }else{
            colocacion.setNombre(txtNombre.getText());
            return colocacion;
        }


    }
    public  void limpiar() {
        txtCodigo.setText("");
        txtNombre.setText("");
        estado("Activo");


// VOS Y ESTADO COMO LE HAGO
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



}
