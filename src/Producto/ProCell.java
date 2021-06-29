package Producto;

import ClassAux.AlertDialog;
import ClassAux.Util;
import Producto.DAO.DataLote;
import Producto.DAO.DataProducto;
import Producto.DAO.Lote;
import Producto.DAO.Producto;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ProCell extends ListCell<Producto> {
    //declaramos un nodo y un objeto de rowproducto
    AlertDialog alertDialog=new AlertDialog();

    private Node graphic;
    private RowProducto rowProducto;
    // el constructor donde llamamos el el rowproducto
    public  ProCell(){
        FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("Producto/RowProducto.fxml"));
        try {
            graphic=loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        rowProducto=loader.getController();

        //el evento del boton eliminar para eliminar productos ____________________________________________________///
        rowProducto.btnEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Lote lote=new Lote(Integer.parseInt( rowProducto.idlote.getText()),Integer.parseInt(rowProducto.codigo.getText()),0,0,0,0,0,rowProducto.estado.getText());

                if (lote.getEstado().equals("Activo")) {
                    if (lote.getIdlote() > 0) {

                        if (alertDialog.alertConfirm("Lote", "Esta seguro de emilinar este lote")) {
                            DataLote dataLote = new DataLote();
                            dataLote.crudLote(lote, "delete");
                            ProductoController productoController = new ProductoController();
                            productoController.initLista(getListView());
                            getListView().refresh();
                        }
                    }
                else{
                    if (alertDialog.alertConfirm("Producto", "Está seguro de desactivar este producto.")) {
                        Producto pro = new Producto(Integer.parseInt(rowProducto.codigo.getText()), "x", "x", "x", 0, 0, 0, 0, 0, 0, 0, 0, 0, "No Activo", 0, 0, "", "");
                        DataProducto datos = new DataProducto();
                        datos.crudProducto(pro, "delete");
                        ProductoController productoController = new ProductoController();
                        productoController.initLista(getListView());
                        getListView().refresh();
                    }
                }
                }else {
                        if (alertDialog.alertConfirm("Producto", "Desea  activar este producto.")){
                        Producto pro=new Producto(Integer.parseInt(rowProducto.codigo.getText()),"x","x","x",0,0,0,0,0,0,0,0,0,"Activo",0,0,"","");
                        DataProducto datos=new DataProducto();
                        datos.crudProducto(pro,"delete");
                        ProductoController productoController=new ProductoController();
                        productoController.initLista(getListView());
                        getListView().refresh();
                    }
                }



            }

        });
        //final del evento de eliminar

        //evento del boton modificar _______________________________________________________________________________________//
        
        rowProducto.btnEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (rowProducto.estado.getText().equals("No Activo")) {
                    Util.Error("Producto", "Por favor Active  el producto para poder modificar");

                } else {
                Producto producto = new Producto();
                for (int i = 0; i < getListView().getItems().size(); i++) {
                    if (Integer.parseInt(rowProducto.codigo.getText()) == getListView().getItems().get(i).getCodigo()) {
                        producto.setCodigo(getListView().getItems().get(i).getCodigo());
                        producto.setNombre(getListView().getItems().get(i).getNombre());
                        producto.setModelo(getListView().getItems().get(i).getModelo());
                        producto.setEspecificacion(getListView().getItems().get(i).getEspecificacion());
                        producto.setStock(getListView().getItems().get(i).getCantidad());
                        producto.setMaximo(getListView().getItems().get(i).getMaximo());
                        producto.setMinimo(getListView().getItems().get(i).getMinimo());
                        producto.setPrecio_compra(getListView().getItems().get(i).getPrecio_compra());
                        producto.setPrecio_mayorista(getListView().getItems().get(i).getPrecio_mayorista());
                        producto.setPrecio_mayor(getListView().getItems().get(i).getPrecio_mayor());
                        producto.setPrecio_unidad(getListView().getItems().get(i).getPrecio_unidad());
                        producto.setIdcolocacion(getListView().getItems().get(i).getIdcolocacion());
                        producto.setIdproveedor(getListView().getItems().get(i).getIdproveedor());
                        producto.setEstado(getListView().getItems().get(i).getEstado());
                        producto.setColocacion(getListView().getItems().get(i).getColocacion());
                        producto.setProveedor(getListView().getItems().get(i).getProveedor());
                        producto.setIdlote(getListView().getItems().get(i).getIdlote());
                        producto.setCantidad(getListView().getItems().get(i).getCantidad());


                    }
                }
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Producto/FormProducto.fxml"));
                    Parent parent = loader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Modificar producto");
                    stage.getIcons().add(new Image("/img/icon.png"));
                    stage.setScene(new Scene(parent));
                    FormProducto formProducto = loader.<FormProducto>getController();
                    formProducto.pasarRegistro(producto);
                    stage.show();
                    stage.setOnHiding((event -> {
                        ProductoController productoController = new ProductoController();
                        productoController.initLista(getListView());
                        getListView().refresh();
                    }));

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
            }
        });
        //final del evento modificar


        rowProducto.btnAgregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (rowProducto.estado.getText().equals("No Activo")) {
                    Util.Error("Producto", "Por favor Active el estado del producto para pode agregar al inventario");

                } else {
                    Lote lote=new Lote();
                    for (int i=0;i<getListView().getItems().size();i++){
                        if (Integer.parseInt(rowProducto.codigo.getText())==getListView().getItems().get(i).getCodigo() && Integer.parseInt(rowProducto.idlote.getText())==getListView().getItems().get(i).getIdlote()){
                            lote.setPrecio_compra(getListView().getItems().get(i).getPrecio_compra());
                            lote.setIdproducto(getListView().getItems().get(i).getCodigo());
                            lote.setPrecio_mayorista(getListView().getItems().get(i).getPrecio_mayorista());
                            lote.setPrecio_mayor(getListView().getItems().get(i).getPrecio_mayor());
                            lote.setPrecio_unidad(getListView().getItems().get(i).getPrecio_unidad());
                            lote.setEstado(getListView().getItems().get(i).getEstado());
                            lote.setIdlote(getListView().getItems().get(i).getIdlote());
                            lote.setCantidad(getListView().getItems().get(i).getCantidad());
                        }
                    }
                    try {
                        FXMLLoader loader= new FXMLLoader(getClass().getClassLoader().getResource("Producto/NuevoLote.fxml"));
                        Parent parent = loader.load();
                        Stage stage=new Stage();
                        stage.setTitle("Modificar producto");
                        stage.setScene(new Scene(parent));
                        stage.getIcons().add(new Image("/img/icon.png"));
                        NuevoLoteController nuevoLoteController=loader.<NuevoLoteController>getController();
                        nuevoLoteController.pasarRegistro(lote);
                        stage.show();
                        stage.setOnHiding((event ->{
                            ProductoController productoController=new ProductoController();
                            productoController.initLista(getListView());
                            getListView().refresh();
                        }));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
    }
//aqui llenas la lista con  el rowProducto
    @Override
    protected void updateItem(Producto producto, boolean empty){
        super.updateItem(producto, empty);
        if (empty){
            clearContent();
        }else{
            addContent(producto);
          //  rowProducto.pasar(getItem());
        }
    }
    //para limpiar contenido
    private void clearContent(){
        setGraphic(null);
    }
    //agregamos contenido a cada label creado en el rowproducto
    private void addContent(Producto producto){
        setText(null);
      //  rowProducto.setAncho(getListView().getWidth()-16);
        rowProducto.setCodigo(producto.getCodigo());
        rowProducto.setNombre(producto.getNombre()+"  "+producto.getModelo()+"  "+producto.getEspecificacion());
        rowProducto.setEstado(producto.getEstado());
        rowProducto.setColocacion(producto.getColocacion());
        rowProducto.setPrecioCompra(producto.getPrecio_compra());
        rowProducto.setpMayorista(producto.getPrecio_mayorista());
        rowProducto.setpMayor(producto.getPrecio_mayor());
        rowProducto.setpUnidad(producto.getPrecio_unidad());
        rowProducto.setMaxima(producto.getMaximo());
        rowProducto.setMinima(producto.getMinimo(),producto.getStock());
        rowProducto.setStock(producto.getStock());
        rowProducto.setProveedor(producto.getProveedor());
        rowProducto.setCantidadlote(producto.getCantidad());
        rowProducto.setIdlote(producto.getIdlote());

        setGraphic(graphic);
    }
}