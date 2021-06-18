package Producto;

import Colocacion.DAO.Colocacion;
import Colocacion.DAO.DataColocacion;
import Producto.DAO.DataProducto;
import Producto.DAO.Producto;
import Proveedor.DAO.DataProveedor;
import Proveedor.DAO.Proveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class FormProducto implements Initializable {
    public Button btnIngresarProducto;
    public Button btnNuevaColocacion;
    public Button btnNuevoProveedor;
    public CheckBox chbActivo;
    public CheckBox chbNoActivo;
    public ComboBox <Colocacion>cbColocacion;
    public ComboBox <Proveedor>cbProveedor;
    public TextField txtPrecioUnidad;
    public TextField txtPrecioMayor;
    public TextField txtPrecioMayorista;
    public TextField txtPrecioCompra;
    public TextField txtMinima;
    public TextField txtMaxima;
    public TextField txtCantidad;
    public TextField txtEspecificacion;
    public TextField txtModelo;
    public TextField txtNombre;
    public TextField txtCodigo;
    private   String accion="new";
    private String estado="Activo";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
estado("Activo");
        iniciar_combo_pro_col(String.valueOf(""),String.valueOf(""));
    }

    public void pasarRegistro(Producto producto){
        if (producto !=null){
            accion="update";
            txtCodigo.setText(String.valueOf(producto.getCodigo()));
            txtCodigo.setEditable(false);
            txtNombre.setText(producto.getNombre());
            txtModelo.setText(producto.getModelo());
            txtEspecificacion.setText(producto.getEspecificacion());
            txtCantidad.setText(String.valueOf(producto.getStock()));
            txtMaxima.setText(String.valueOf(producto.getMaximo()));
            txtMinima.setText(String.valueOf(producto.getMinimo()));
            txtPrecioCompra.setText(String.valueOf(producto.getPrecio_compra()));
            txtPrecioMayorista.setText(String.valueOf(producto.getPrecio_mayorista()));
            txtPrecioMayor.setText(String.valueOf(producto.getPrecio_mayor()));
            txtPrecioUnidad.setText(String.valueOf(producto.getPrecio_unidad()));
            iniciar_combo_pro_col(String.valueOf(producto.getProveedor()),String.valueOf(producto.getColocacion()));

            estado(producto.getEstado());
            btnIngresarProducto.setText("Actualizar Producto");

        }
    }
    public  void  estado(String estado){
        if (estado.equals("Activo")){
          chbActivo.setSelected(true);
          chbNoActivo.setSelected(false);
        }else {
            chbNoActivo.setSelected(true);
            chbActivo.setSelected(false);
        }

    }
    public void RegistrarProducto(ActionEvent actionEvent) {
        switch (accion){
            case "new":
               guardarProducto("new");
                break;
            case "update":
                guardarProducto("update");
                break;

        }
    }
    public  void guardarProducto(String accion){
        Producto producto= new Producto(Integer.parseInt(txtCodigo.getText()),txtNombre.getText(),txtModelo.getText(),txtEspecificacion.getText(),Integer.parseInt(txtCantidad.getText()),
                Integer.parseInt(txtPrecioCompra.getText()),Integer.parseInt(txtPrecioMayorista.getText()),Integer.parseInt(txtPrecioMayor.getText()),Integer.parseInt(txtPrecioUnidad.getText()),
                Integer.parseInt(txtMaxima.getText()),Integer.parseInt(txtMinima.getText()),
                cbColocacion.getSelectionModel().getSelectedItem().getIdColocacion(),
                cbProveedor.getSelectionModel().getSelectedItem().getIdProveedor(),estado
        );
        DataProducto dataProducto=new DataProducto();
        dataProducto.crudProducto(producto,accion);

    }

    public void IngresarNuevaColocacion(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("/Colocacion/FormProducto.fxml"));
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
            stage.setOnHiding((event ->{
                initLista();
                listProducto.refresh();
            }));

        }catch (IOException e){
            e.printStackTrace();

        }
    }

    public void ingresarNuevoProveedor(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Producto/FormProducto.fxml"));
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
            stage.setOnHiding((event ->{
                initLista();
                listProducto.refresh();
            }));

        }catch (IOException e){
            e.printStackTrace();

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

    public  void iniciar_combo_pro_col(String proveedor, String colocacion){
        DataProveedor dataProveedor=new DataProveedor();
        ArrayList<Proveedor> listProveedor= dataProveedor.viewProveedor("viewlast");
        ObservableList<Proveedor> obListProveedor= FXCollections.observableArrayList();
      /*  for (int i=0; i<listProveedor.size(); i++){
            ArrayList <String> item=new ArrayList<>();
            item.add(listProveedor.get(i).getIdProveedor()+" "+ listProveedor.get(i).getNombre()+ " "+ listProveedor.get(i).getCompania());
            obListProveedor.addAll(item);
        }**/
        obListProveedor.addAll(listProveedor);
        cbProveedor.setItems(obListProveedor);

        DataColocacion dataColocacion=new DataColocacion();
        ArrayList<Colocacion> listColocacion=dataColocacion.viewColocacion("viewlast");
        ObservableList<Colocacion> obListColocacion=FXCollections.observableArrayList();
      /*  for (int i=0;i<listColocacion.size(); i++ ){
            ArrayList <String> item=new ArrayList<>();
            item.add(listColocacion.get(i).getIdColocacion()+ " "+ listColocacion.get(i).getNombre());
            obListColocacion.addAll(item);
        }*/
        obListColocacion.addAll(listColocacion);
        cbColocacion.setItems(obListColocacion);

        if (!proveedor.isEmpty()){
       //   cbProveedor.getSelectionModel().select(obListProveedor.);
        }
        if (!colocacion.isEmpty()){
         //   cbColocacion.getSelectionModel().select(colocacion);
        }
    }


}
