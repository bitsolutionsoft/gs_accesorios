package Informe;

import ClassAux.SizeColumnTable;
import Informe.DAO.Producto_Vendido;
import Informe.DAO.ResumenVenta;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Producto_mas_vendido implements Initializable {
    public TextField txtBuscar;

    public TableView <Producto_Vendido>tblVentas;
    public TableColumn<Producto_Vendido,String> cellCod;
    public TableColumn<Producto_Vendido,String> cellcant;
    public TableColumn<Producto_Vendido,String> cellCliente;

    ObservableList<Producto_Vendido> listVenta= null;
    FilteredList<Producto_Vendido> filteredListVenta=null;
    SizeColumnTable size_tabla=new SizeColumnTable();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       iniciarTabla();
    }
    public void iniciarTabla(){
        cellCod=new TableColumn<>("Código");
        cellcant=new TableColumn<>("Cantidad");
        cellCliente=new TableColumn<>("Nombre");



        cellCod.setCellValueFactory(new PropertyValueFactory<Producto_Vendido,String>("codigo"));
        cellcant.setCellValueFactory(new PropertyValueFactory<Producto_Vendido,String>("cantidad"));
        cellCliente.setCellValueFactory(new PropertyValueFactory<Producto_Vendido,String>("nombre"));

        tblVentas.setEditable(true);
        tblVentas.getColumns().addAll(cellCod,cellcant,cellCliente);
        Platform.runLater(()-> size_tabla.ajustarColumna(tblVentas));

    }
    public void llenarTabla(ObservableList<Producto_Vendido> list){
        listVenta= FXCollections.observableArrayList(list);
        filteredListVenta=new FilteredList<>(list, p-> true);
        SortedList<Producto_Vendido> sortedList=new SortedList<>(filteredListVenta);
        sortedList.comparatorProperty().bind(tblVentas.comparatorProperty());
        tblVentas.setItems(sortedList);

        txtBuscar.textProperty().addListener((prop,old,text) ->{
            filteredListVenta.setPredicate(resumenVenta -> {
                if (text==null || text.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = text.toLowerCase();
                if (String.valueOf(resumenVenta.getCodigo()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (resumenVenta.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
    }




}
