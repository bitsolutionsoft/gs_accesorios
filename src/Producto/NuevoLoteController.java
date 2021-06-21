package Producto;

import ClassAux.Util;
import Producto.DAO.DataLote;
import Producto.DAO.Lote;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NuevoLoteController implements Initializable {
    public CheckBox chbMismoLot;
    public CheckBox chbNuevoLote;
    public TextField txtidLote;
    public TextField txtidproducto;
    public TextField txtcantidad;
    public TextField txtpreciocompra;
    public TextField txtpreciomayorista;
    public TextField txtpreciomayor;
    public TextField txtpreciounidad;
    public Button btnNuevoLote;
    public CheckBox chbActivo;
    public CheckBox chbNoActivo;
    Lote datosLote=new Lote();
    String accion="new";
    String estado="Activo";



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Estado(estado);

    }
    public void AgragarLote(ActionEvent actionEvent) {

        if (returnDatos()!=null) {
            DataLote dataLote=new DataLote();
            switch (accion) {
                case "new":
                    dataLote.crudLote(returnDatos(), "new");
                    limpiar();
                    break;
                case "add":
                    dataLote.crudLote(returnDatos(), "add");
                    txtcantidad.setText("");
                   // limpiar();
                    break;

            }
        }
    }

    public  void AccionLote(String accion){
        if (accion.equals("add")){
            chbMismoLot.setSelected(true);
            chbNuevoLote.setSelected(false);
        }else{
            chbMismoLot.setSelected(false);
            chbNuevoLote.setSelected(true);
        }
    }
    public  void  Estado(String estado){
        if (estado.equals("Activo")){
            chbActivo.setSelected(true);
            chbNoActivo.setSelected(false);
        }else {
            chbNoActivo.setSelected(true);
            chbActivo.setSelected(false);
        }

    }
    public void  llenardatos(Lote lote){
        accion="add";
        txtidLote.setText(String.valueOf(lote.getIdlote()));
        txtidproducto.setText(String.valueOf(lote.getIdproducto()));
       // txtcantidad.setText(String.valueOf(lote.getCantidad()));
        txtpreciocompra.setText(String.valueOf(lote.getPrecio_compra()));
        txtpreciomayorista.setText(String.valueOf(lote.getPrecio_mayorista()));
        txtpreciomayor.setText(String.valueOf(lote.getPrecio_mayor()));
        txtpreciounidad.setText(String.valueOf(lote.getPrecio_unidad()));
        Estado(lote.getEstado());

        txtidLote.setEditable(false);
        txtidproducto.setEditable(false);
        txtpreciocompra.setEditable(false);
        txtpreciomayorista.setEditable(false);
        txtpreciomayor.setEditable(false);
        txtpreciounidad.setEditable(false);
    }
    public void  llenarNuevoLote(Lote lote){
        accion="new";
        txtidLote.setText("");
        txtidproducto.setText(String.valueOf(lote.getIdproducto()));
        txtidproducto.setEditable(false);
        txtidLote.setEditable(false);
        txtpreciocompra.setText("");
        txtpreciomayorista.setText("");
        txtpreciomayor.setText("");
        txtpreciounidad.setText("");
        Estado("Activo");

        txtidproducto.setEditable(true);
        txtpreciocompra.setEditable(true);
        txtpreciomayorista.setEditable(true);
        txtpreciomayor.setEditable(true);
        txtpreciounidad.setEditable(true);

    }

    public void pasarRegistro(Lote lote){
        if (lote !=null){
           datosLote=lote;
           if (datosLote.getIdlote()>0){
            AccionLote("add");
           llenardatos(datosLote);

           }else{
               AccionLote("new");
               llenarNuevoLote(datosLote);

           }

        }
    }

    public void estadoActivo(ActionEvent actionEvent) {
        if (chbActivo.isSelected()){
            estado="Activo";
            chbNoActivo.setSelected(false);
        }
    }

    public void estadoNoActivo(ActionEvent actionEvent) {
        if (chbNoActivo.isSelected()){
            estado="No Activo";
            chbActivo.setSelected(false);
        }
    }

    public void mismoLote(ActionEvent actionEvent) {
        if (chbMismoLot.isSelected()){
            accion="add";
            chbNuevoLote.setSelected(false);
            llenardatos(datosLote);
        }
    }

    public void nuevoLote(ActionEvent actionEvent) {
        if (chbNuevoLote.isSelected()){
            accion="new";
            chbMismoLot.setSelected(false);
            llenarNuevoLote(datosLote);

        }
    }
    public  Lote returnDatos(){
        Lote lote=new Lote();

        if (txtidLote.getText().isEmpty()){
            lote.setIdlote(0);
        }else {
            lote.setIdlote(Integer.parseInt(txtidLote.getText()));
        }
            if (txtidproducto.getText().isEmpty()){
                Util.Error("Lote","El campo codigo producto se encuentra vacía");
                return  null;
            }else{
                lote.setIdproducto(Integer.parseInt(txtidproducto.getText()));
                if (txtcantidad.getText().isEmpty()){
                    Util.Error("Lote","El campo cantidad  se encuentra vacía");
                    return  null;
                }else{
                    lote.setCantidad(Integer.parseInt(txtcantidad.getText()));
                    if (txtpreciocompra.getText().isEmpty()){
                        Util.Error("Lote","El campo precio compra se encuentra vacía");
                        return  null;
                    }else{
                        lote.setPrecio_compra(Float.parseFloat(txtpreciocompra.getText()));
                        if (txtpreciomayorista.getText().isEmpty()){
                            Util.Error("Lote","El campo precio a mayoristas se encuentra vacía");
                            return  null;
                        }else{
                            lote.setPrecio_mayorista(Float.parseFloat(txtpreciomayorista.getText()));
                            if (txtpreciomayor.getText().isEmpty()){
                                Util.Error("Lote","El campo precio por amyor se encuentra vacía");
                                return  null;
                            }else{
                                lote.setPrecio_mayor(Float.parseFloat(txtpreciomayor.getText()));
                                if (txtpreciounidad.getText().isEmpty()){
                                    Util.Error("Lote","El campo precio por unidad se encuentra vacía");
                                    return  null;
                                }else{
                                    lote.setPrecio_unidad(Float.parseFloat(txtpreciounidad.getText()));
                                    if (estado.equals("")){
                                        Util.Error("Lote","Seleccione un estado");
                                        return  null;
                                    }else{
                                        lote.setEstado(estado);
                                        return lote;
                                    }
                                }
                            }
                        }
                    }
                }

        }
    }

    public  void limpiar(){
        txtcantidad.setText("");
        txtpreciocompra.setText("");
        txtpreciomayorista.setText("");
        txtpreciomayor.setText("");
        txtpreciounidad.setText("");
        Estado("Activo");
    }
}
