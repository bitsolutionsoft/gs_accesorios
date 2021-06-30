package Venta;

import ClassAux.AlertDialog;
import ClassAux.ImprimirVale;
import ClassAux.SizeColumnTable;
import Cliente.DAO.Cliente;
import Cliente.DAO.DataCliente;
import Producto.DAO.DataLote;
import Producto.DAO.Lote;
import Venta.DAO.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentaController implements Initializable {
    public TextField txtFecha;
    public Label lblNoOrden;
    public ComboBox <Cliente> cbxCliente;
    public Button btnNuevoCliente;
    public TextField txtBuscar;
    public Label lblCambio;
    public Button btnLimpiar;
    public CheckBox chbxImprimir;
    public CheckBox chbxGuardar;
    AlertDialog alertDialog=new AlertDialog();
    public TableView <ProductoDisponible>tblProductoDisponible;
    public TableColumn<ProductoDisponible, String> codigoPro;
    public TableColumn<ProductoDisponible, String> nombrePro;
    public TableColumn<ProductoDisponible, String> stockPro;
    public TableColumn<ProductoDisponible, String> precioPro;
    public TableColumn<ProductoDisponible, String> colocacionPro;


    public TableView <DetalleFactura> tblProductoSeleccionado;
    public TableColumn<DetalleFactura, String> cellCodigo;
    public TableColumn<DetalleFactura, String> cellDescripcion;
    public TableColumn<DetalleFactura, Integer> cellCantidad;
    public TableColumn<DetalleFactura, String> cellSubtotal;

    public TableColumn<DetalleFactura, Float> cellPrecio;
    public Label lblNoArticulo;
    public Label lblSubtotal;
    public Label lblTotal;
    public TextField txtRecibido;
    public Button btnVender;

    ObservableList<ProductoDisponible> data = null;
    FilteredList<ProductoDisponible> filtrarDatos = null;
    ArrayList<ProductoDisponible> listaProducto = new ArrayList<>();
    ArrayList<DetalleFactura> proSelecionado = new ArrayList<>();
    ObservableList<DetalleFactura> proSeleccionados;
    DataLote dataLote=new DataLote();
    ObservableList<Lote> datosLote= FXCollections.observableArrayList(dataLote.viewLote("viewall"));
    SizeColumnTable ajustarColumna=new SizeColumnTable();
    private Boolean imprimirFact=false;
    private Boolean guardarFact=false;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       mostrarFecha();
       numeroOrder();
       iniciarColumnatTabla();
       llenarTablaDisponible();
       llenarComboCliente(false);
        EventoSeleccionarProducto();
       // chbxGuardar.setVisible(false);
       btnLimpiar.setVisible(false);

    }
    public void llenarComboCliente(boolean nuevo){
        cbxCliente.getItems().clear();
        DataCliente cliente = new DataCliente();
        ArrayList<Cliente> clientes = cliente.viewCliente("viewall");
        ObservableList<Cliente> lista = FXCollections.observableArrayList(clientes);
        cbxCliente.getItems().addAll(lista);
        if (!nuevo){
        cbxCliente.getSelectionModel().selectFirst();}
        else {
            cbxCliente.getSelectionModel().selectLast();
        }
    }
    public void EventoSeleccionarProducto(){
        txtRecibido.setOnKeyReleased((keyEvent -> {
            if (!txtRecibido.getText().isEmpty()){
                lblCambio.setText(String.valueOf(Float.parseFloat(txtRecibido.getText())-Float.parseFloat(lblTotal.getText())));
            }
        }));

        txtBuscar.setOnAction((event -> {
          if (!txtBuscar.getText().isEmpty()){
              int idproducto=Integer.parseInt(txtBuscar.getText());
              for (int i=0; i<tblProductoDisponible.getItems().size(); i++){
                  if (idproducto== tblProductoDisponible.getItems().get(i).getIdproducto()){
                      TableRow<ProductoDisponible> row =new TableRow<>();
                      row.setItem(tblProductoDisponible.getItems().get(i));
                      DescontarProducto(row.getItem(),1);
                  }
                  txtBuscar.setText("");
              }
          }


        }));

        tblProductoDisponible.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.isPrimaryButtonDown() && mouseEvent.getClickCount()==1){

                    Node node =((Node) mouseEvent.getTarget()).getParent();
                    TableRow<ProductoDisponible> row;
                    if (node instanceof  TableRow){
                        row=(TableRow<ProductoDisponible>) node;
                    }else{
                        row=(TableRow<ProductoDisponible>) node.getParent();
                    }
                    if(row.getItem()!=null) {
                        DescontarProducto(row.getItem(), 1);
                    }
                }
            }
        });


tblProductoSeleccionado.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent event) {
        if (event.isSecondaryButtonDown() && event.getClickCount()==2){
            DetalleFactura detalleFactura=tblProductoSeleccionado.getSelectionModel().getSelectedItem();
            devolver(detalleFactura, detalleFactura.getCantidad(), true);
        }
    }
});

     //modificar cantidad desde la celda cantidad enla tabla ventas
        cellCantidad.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<DetalleFactura, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<DetalleFactura, Integer> event) {
                DetalleFactura detalle=tblProductoSeleccionado.getSelectionModel().getSelectedItem();
                int NoArtAnterior = detalle.getCantidad();
                int nuevo = event.getNewValue();
                devolver(detalle,NoArtAnterior,false);
                DescontarProducto(new ProductoDisponible(detalle.getIdproducto(),detalle.getCantidad(),detalle.getIdlote(),detalle.getDescripcion(),"","","",detalle.getPrecio()),nuevo);

            }
        });
cellPrecio.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<DetalleFactura, Float>>() {
    @Override
    public void handle(TableColumn.CellEditEvent<DetalleFactura, Float> event) {
        DetalleFactura detalle=tblProductoSeleccionado.getSelectionModel().getSelectedItem();
        Float nuevo=event.getNewValue();
        for (int i=0; i<proSelecionado.size(); i++){
            if (proSelecionado.get(i).getIdlote()==detalle.getIdlote() && proSelecionado.get(i).getIdproducto()==detalle.getIdproducto()){
                proSelecionado.get(i).setPrecio(nuevo);
                refresacarLista();
            }
        }
    }
});








    }

public void devolver(DetalleFactura detalleFactura, int cantidad,boolean eliminar){
    for (int i = 0; i < listaProducto.size(); i++) {
        if (detalleFactura.getIdproducto()== listaProducto.get(i).getIdproducto() && detalleFactura.getIdlote()== listaProducto.get(i).getIdlote()) {
            int cant = listaProducto.get(i).getStock();
            listaProducto.get(i).setStock(cant + cantidad);
            tblProductoDisponible.refresh();

        }
    }
    for (int i=0;i<proSelecionado.size();i++){
        if (detalleFactura.getIdproducto()== proSelecionado.get(i).getIdproducto() && detalleFactura.getIdlote()== proSelecionado.get(i).getIdlote()) {
            proSelecionado.get(i).setCantidad(0);
            tblProductoDisponible.refresh();
        }
    }
    if (eliminar) {

        for (int i = 0; i < proSelecionado.size(); i++) {
            if (detalleFactura.getIdproducto() == proSelecionado.get(i).getIdproducto() && detalleFactura.getIdlote() == proSelecionado.get(i).getIdlote()) {
                    proSelecionado.remove(i);
                refresacarLista();
            }
        }
    }

}
public  boolean validarSeleccin(int lote, int id){
   boolean result =false;
    for (int i=0;i<proSelecionado.size();i++){
        if (id==proSelecionado.get(i).getIdproducto() && lote==proSelecionado.get(i).getIdlote()){
           result= true;
        }
    }
    return result;
    }
    public void AgregarSeleccion(ProductoDisponible pro, int cantidad){

        if (proSelecionado.size()>0){

           if (validarSeleccin(pro.getIdlote(),pro.getIdproducto())) {
               for (int i=0;i<proSelecionado.size();i++)
               if (pro.getIdproducto() == proSelecionado.get(i).getIdproducto() && pro.getIdlote() == proSelecionado.get(i).getIdlote()) {
                   int anterior = proSelecionado.get(i).getCantidad();
                   proSelecionado.get(i).setCantidad(anterior + cantidad);
               }
           }
           else{
                   DetalleFactura detalleFactura = new DetalleFactura();
                   detalleFactura.setCantidad(cantidad);
                   detalleFactura.setIdlote(pro.getIdlote());
                   detalleFactura.setDescripcion(pro.getNombre());
                   detalleFactura.setIdproducto(pro.getIdproducto());
                   detalleFactura.setPrecio(pro.getPrecio());
                   proSelecionado.add(detalleFactura);

               }


        }else{
            DetalleFactura detalleFactura=new DetalleFactura();
            detalleFactura.setCantidad(cantidad);
            detalleFactura.setDescripcion(pro.getNombre());
            detalleFactura.setIdlote(pro.getIdlote());
            detalleFactura.setIdproducto(pro.getIdproducto());
            detalleFactura.setPrecio(pro.getPrecio());
            proSelecionado.add(detalleFactura);


        }


        refresacarLista();

    }
    public void DescontarProducto(ProductoDisponible pro, int cantidad){

        for (int i=0;i<listaProducto.size();i++){


            if (pro.getIdproducto()==listaProducto.get(i).getIdproducto() && pro.getIdlote()==listaProducto.get(i).getIdlote()){

                if (listaProducto.get(i).getStock()>cantidad){
                 listaProducto.get(i).setStock(listaProducto.get(i).getStock()-cantidad);
                 AgregarSeleccion(pro,cantidad);
                }else {
                    cantidad=listaProducto.get(i).getStock();
                    listaProducto.get(i).setStock(   listaProducto.get(i).getStock() - cantidad);
                    AgregarSeleccion(pro,cantidad);
                }
            }
        }
    }
    public void refresacarLista(){
        int cantidadArticulo=0;
        Float Total = Float.parseFloat("0");

        if (proSelecionado.size()>0){
            for (int i=0; i<proSelecionado.size();i++){
                proSelecionado.get(i).setSubtotal(proSelecionado.get(i).getPrecio()*proSelecionado.get(i).getCantidad());
               cantidadArticulo=cantidadArticulo+proSelecionado.get(i).getCantidad();
                Total=Total+proSelecionado.get(i).getSubtotal();
            }
        }


        proSeleccionados=FXCollections.observableArrayList(proSelecionado);
        tblProductoSeleccionado.setItems(proSeleccionados);
        tblProductoSeleccionado.refresh();

        data=FXCollections.observableArrayList(listaProducto);
        tblProductoDisponible.setItems(data);
        tblProductoDisponible.refresh();

        lblSubtotal.setText(String.valueOf(Total));
        lblNoArticulo.setText(String.valueOf(cantidadArticulo));
        lblTotal.setText(String.valueOf(Total));


    }
public  void iniciarColumnatTabla(){

    //iniciar tablas
    tblProductoDisponible.setEditable(true);
    tblProductoDisponible.getSelectionModel().setCellSelectionEnabled(false);

    codigoPro = new TableColumn("Codigo");
    nombrePro = new TableColumn("Nombre");
    stockPro = new TableColumn<>("Existencia");
    precioPro = new TableColumn<>("Precio");
    colocacionPro = new TableColumn("Ubicación");
    tblProductoDisponible.getColumns().setAll(codigoPro, nombrePro, stockPro, precioPro, colocacionPro);


    codigoPro.setCellValueFactory(new PropertyValueFactory<ProductoDisponible, String>("idproducto"));
    nombrePro.setCellValueFactory(new PropertyValueFactory<ProductoDisponible, String>("nombre"));
    stockPro.setCellValueFactory(new PropertyValueFactory<ProductoDisponible, String>("stock"));
    precioPro.setCellValueFactory(new PropertyValueFactory<ProductoDisponible, String>("precio"));
    colocacionPro.setCellValueFactory(new PropertyValueFactory<ProductoDisponible, String>("sector"));
    tblProductoDisponible.setColumnResizePolicy(param -> true);
    Platform.runLater(()-> ajustarColumna.ajustarColumna(tblProductoDisponible));




    ///tabla de venta___________________________________________________________________________________________________________
    tblProductoSeleccionado.setEditable(true);
    tblProductoSeleccionado.getSelectionModel().setCellSelectionEnabled(false);

    cellCodigo = new TableColumn("Codigo");
    cellDescripcion = new TableColumn("Descripcion");
    cellCantidad = new TableColumn<>("cantidad");
    cellPrecio = new TableColumn<>("Precio");
    cellSubtotal = new TableColumn<>("subtotal");


    cellCodigo.setCellValueFactory(new PropertyValueFactory<DetalleFactura, String>("idproducto"));
    cellDescripcion.setCellValueFactory(new PropertyValueFactory<DetalleFactura, String>("descripcion"));
    cellCantidad.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Integer>("cantidad"));
    cellPrecio.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Float>("precio"));
    cellSubtotal.setCellValueFactory(new PropertyValueFactory<DetalleFactura, String>("subtotal"));
    tblProductoSeleccionado.setColumnResizePolicy(param -> true);
    tblProductoSeleccionado.getColumns().setAll(cellCodigo, cellDescripcion, cellPrecio, cellCantidad, cellSubtotal);
    Platform.runLater(()-> ajustarColumna.ajustarColumna(tblProductoSeleccionado));
    tblProductoSeleccionado.setEditable(true);
    tblProductoSeleccionado.getSelectionModel().setCellSelectionEnabled(false);

 //cell factory cantidad y precio
   cellCantidad.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
       @Override
       public String toString(Integer integer) {
           return integer.toString();
       }

       @Override
       public Integer fromString(String s) {
           return Integer.parseInt(s);
       }
   }));


   cellPrecio.setCellFactory(TextFieldTableCell.forTableColumn( new StringConverter<Float>() {
       @Override
       public String toString(Float aFloat) {
           return aFloat.toString();
       }

       @Override
       public Float fromString(String s) {
           return Float.parseFloat(s);
       }
   }));

   ContextMenu contextMenu=new ContextMenu();
MenuItem itemPrecio=new MenuItem("Seleccionar precio");
    MenuItem itemEliminar=new MenuItem("Eliminar de la venta");
    itemEliminar.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            DetalleFactura detalleFactura=tblProductoSeleccionado.getSelectionModel().getSelectedItem();
            if (detalleFactura!=null) {
                devolver(detalleFactura, detalleFactura.getCantidad(), true);
            }
        }
    });

    itemPrecio.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            DetalleFactura detalle = tblProductoSeleccionado.getSelectionModel().getSelectedItem();
            ArrayList<Float> lis = new ArrayList<>(retornarPrecio(detalle));
            if (lis != null) {
                Float nuevo = alertDialog.SelectPrecion(lis);

                if (nuevo != null) {
                    for (int i = 0; i < proSelecionado.size(); i++) {
                        if (proSelecionado.get(i).getIdlote() == detalle.getIdlote() && proSelecionado.get(i).getIdproducto() == detalle.getIdproducto()) {
                            proSelecionado.get(i).setPrecio(nuevo);
                            refresacarLista();
                        }
                    }
                }

            }
        }
    });
    contextMenu.getItems().add(itemPrecio);
    contextMenu.getItems().add(itemEliminar);
    tblProductoSeleccionado.setContextMenu(contextMenu);



}
public void llenarTablaDisponible (){
    DataProDisponible pro=new DataProDisponible();
    listaProducto.clear();
    listaProducto.addAll(pro.ProductoDisponible());


    data = FXCollections.observableArrayList(listaProducto);
    filtrarDatos = new FilteredList<>(data, p -> true);
    SortedList<ProductoDisponible> datosOrdenados = new SortedList<>(filtrarDatos);
    datosOrdenados.comparatorProperty().bind(tblProductoDisponible.comparatorProperty());
    tblProductoDisponible.setItems(datosOrdenados);
    txtBuscar.textProperty().addListener((prop, old, text) -> {
        filtrarDatos.setPredicate(producto -> {
            if (text == null || text.isEmpty()) {
                return true;
            }
            //String name = producto.getNombre().toLowerCase();
            String lowerCaseFilter = text.toLowerCase();
            if (String.valueOf(producto.getIdproducto()).toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (producto.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (producto.getModelo().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (producto.getEspecificacion().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
            return false;

        });
    });
}

    public void mostrarFecha(){
        String fecha = String.valueOf(LocalDate.now());
        txtFecha.setText(fecha);
        txtFecha.setEditable(false);
    }
    public  void numeroOrder(){
DataProDisponible dataProDisponible=new DataProDisponible();
lblNoOrden.setText(String.valueOf(dataProDisponible.orden()));
    }

    public ArrayList<Float>   retornarPrecio(DetalleFactura detalleFactura){
        ArrayList<Float> precio=new ArrayList();
        for (int i=0;i<datosLote.size();i++){
            if (detalleFactura.getIdproducto()==datosLote.get(i).getIdproducto() && detalleFactura.getIdlote()==datosLote.get(i).getIdlote()){

                precio.add(datosLote.get(i).getPrecio_unidad());
                precio.add(datosLote.get(i).getPrecio_mayor());
                precio.add(datosLote.get(i).getPrecio_mayorista());
                return  precio;
            }
        }
        return precio;
    }

    public void ingresarNuevocliente(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Cliente/FormCliente.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            stage.setOnHiding((event -> {
                llenarComboCliente(true);
            }));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void RealizarVenta(ActionEvent actionEvent) {
        DataFactura factura=new DataFactura();
        DataDetalleFactura detalleFactura=new DataDetalleFactura();
        if (!cbxCliente.getSelectionModel().isEmpty()) {
            Factura datosFactura=new Factura(0, cbxCliente.getSelectionModel().getSelectedItem().getCodigo(),"","","",0);
            factura.crudFactura(datosFactura,"new");
            for (int i=0;i<proSelecionado.size();i++){
                DetalleFactura datosDetalle=new DetalleFactura(0,Integer.parseInt(lblNoOrden.getText()),proSelecionado.get(i).getIdproducto(),proSelecionado.get(i).getDescripcion(),proSelecionado.get(i).getCantidad(),proSelecionado.get(i).getIdlote(),proSelecionado.get(i).getPrecio(),proSelecionado.get(i).getSubtotal());
                detalleFactura.crudDetalleFactura(datosDetalle, "new");
            }
            ImprimirVale imprimir=new ImprimirVale();
            Modelo_factura modelo=new Modelo_factura();
            imprimir.LlenarFactura(modelo.datosFactura(proSeleccionados),lblNoOrden.getText(),guardarFact,imprimirFact,txtFecha.getText(),cbxCliente.getSelectionModel().getSelectedItem().getNombre()+" "+cbxCliente.getSelectionModel().getSelectedItem().getApellido(),"Ciudad",Float.parseFloat(lblTotal.getText()),cbxCliente.getSelectionModel().getSelectedItem().getNit());
        }
        btnLimpiar.setVisible(true);
    }


    public void NuevaVenta(ActionEvent actionEvent) {
        btnLimpiar.setVisible(false);
        proSelecionado.clear();
        tblProductoSeleccionado.getItems().clear();
        tblProductoSeleccionado.refresh();
        llenarTablaDisponible();
        tblProductoDisponible.refresh();
        numeroOrder();
    }

    public void confirmarImpresion(ActionEvent actionEvent) {
        if (chbxImprimir.isSelected()){
            imprimirFact=true;
        }else {
            imprimirFact=false;
        }
    }

    public void confirmarGuardar(ActionEvent actionEvent) {
        if ((chbxGuardar.isSelected())){
            guardarFact=true;
        }else{
            guardarFact=false;
        }
    }
}
