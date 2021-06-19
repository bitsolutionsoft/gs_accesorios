package Producto;

import ClassAux.Util;
import Producto.DAO.DataProducto;
import Producto.DAO.Producto;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;

import java.io.IOException;

public class ProCell extends ListCell<Producto> {
    //declaramos un nodo y un objeto de rowproducto
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
        //el evento del botono eliminar para eliminar productos
        rowProducto.btnEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Producto pro=new Producto(Integer.parseInt(rowProducto.codigo.getText()),"x","x","x",0,0,0,0,0,0,0,0,0,"No Activo");
                DataProducto datos=new DataProducto();
                datos.crudProducto(pro,"delete");
                ProductoController productoController=new ProductoController();
                productoController.initLista(getListView());
                getListView().refresh();
            }

        });
        
        rowProducto.btnEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Producto producto=new Producto();
                for (int i=0;i<getListView().getItems().size();i++){
                    if (Integer.parseInt(rowProducto.codigo.getText())==getListView().getItems().get(i).getCodigo()){
                        producto.setCodigo(getListView().getItems().get(i).getCodigo());
                        producto.setNombre(getListView().getItems().get(i).getNombre());
                        producto.setModelo(getListView().getItems().get(i).getModelo());
                        producto.setEspecificacion(getListView().getItems().get(i).getEspecificacion());
                        producto.setStock(getListView().getItems().get(i).getStock());
                        producto.setMaximo(getListView().getItems().get(i).getMaximo());
                        producto.setMinimo(getListView().getItems().get(i).getMinimo());
                        producto.setPrecio_compra(getListView().getItems().get(i).getPrecio_compra());
                        producto.setPrecio_mayorista(getListView().getItems().get(i).getPrecio_mayorista());
                        producto.setPrecio_mayor(getListView().getItems().get(i).getPrecio_mayor());
                        producto.setPrecio_unidad(getListView().getItems().get(i).getPrecio_unidad());
                        producto.setColocacion(getListView().getItems().get(i).getColocacion());
                        producto.setProveedor(getListView().getItems().get(i).getProveedor());
                        producto.setEstado(getListView().getItems().get(i).getEstado());

                    }
                }
                try {
                    FXMLLoader loader= new FXMLLoader(getClass().getClassLoader().getResource("Producto/FormProducto.fxml"));
                    Parent parent = loader.load();
                    Stage stage=new Stage();
                    stage.setTitle("Modificar producto");
                    stage.setScene(new Scene(parent));
                    FormProducto formProducto=loader.<FormProducto>getController();
                    formProducto.pasarRegistro(producto);
                    stage.show();
                    stage.setOnHiding((event ->{
                        ProductoController productoController=new ProductoController();
                        productoController.initLista(getListView());
                        getListView().refresh();
                    }));

                }catch (IOException e){
                    e.printStackTrace();

                }
            }
        });


        rowProducto.btnAgregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (rowProducto.estado.getText().equals("No Activo")) {
                    Util.Error("Producto", "Por favor Active el estado del producto para pode agregar al inventario");

                } else {
                    try {
                        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("Producto/FormProducto.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();

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
        rowProducto.setMinima(producto.getMinimo());
        rowProducto.setStock(producto.getStock());
        rowProducto.setProveedor(producto.getProveedor());

        setGraphic(graphic);
    }
}