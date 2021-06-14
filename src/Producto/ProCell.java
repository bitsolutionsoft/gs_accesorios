package Producto;

import Producto.DAO.Data;
import Producto.DAO.Producto;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

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
                Data datos=new Data();
                datos.crudProducto(pro,"delete");


                for (int i=0;i<getListView().getItems().size();i++){
                    if (Integer.parseInt(rowProducto.codigo.getText())==getListView().getItems().get(i).getCodigo()){
                        getListView().getItems().get(i).setEstado("No Activo");
                        getListView().refresh();
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