package Informe;

import ClassAux.SizeColumnTable;
import ClassAux.Util;
import Informe.DAO.Cuenta;
import Informe.DAO.DataVentas;
import Informe.DAO.ResumenVenta;
import Informe.DAO.Ventas;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InformeController implements Initializable {

    public RadioButton rDia;
    public RadioButton rSemana;
    public RadioButton rMes;
    public DatePicker fInicial;
    public DatePicker fFinal;
    public Button btnVer;
    public CheckBox ckbGuardar;
    public CheckBox ckbImprimir;
    public Label lblVentas;
    public Label lblCompras;
    public Label lblGanancia;
    public TextField txtBuscar;
    public TableView <ResumenVenta>tblVentas;
    public TableColumn<ResumenVenta,String> cellCod;
    public TableColumn<ResumenVenta,String> cellFecha;
    public TableColumn<ResumenVenta,String> cellCliente;
    public TableColumn<ResumenVenta,String> cellTotal;

    public LineChart<String, Float> lineChart;
    public ScrollPane scrollPane;
    public CategoryAxis xAxis;
    ObservableList<ResumenVenta> listResumenVenta= null;
    FilteredList<ResumenVenta> filteredListVenta=null;
    ObservableList<Cuenta> listCuenta= null;
    SizeColumnTable size_tabla=new SizeColumnTable();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarTabla();
        accionRadioButtons();


    }

    public void accionRadioButtons(){
        rDia.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VerCierreVenta(new Ventas(0,obtnerfechaHoy(),obtnerfechaHoy()),"dia","gdia");
                rSemana.setSelected(false);
                rMes.setSelected(false);
            }
        });
        rSemana.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VerCierreVenta(new Ventas(0,obtnerfechaHoy(),obtnerfechaHoy()),"semana","gsemana");
                rDia.setSelected(false);
                rMes.setSelected(false);
            }
        });
        rMes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VerCierreVenta(new Ventas(0,obtnerfechaHoy(),obtnerfechaHoy()),"mes","gmes");
                rSemana.setSelected(false);
                rDia.setSelected(false);
            }
        });
        btnVer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rSemana.setSelected(false);
                rDia.setSelected(false);
                rMes.setSelected(false);
                String fe_inicial=returnFechaSelect(fInicial,"Fecha Inicial");
                String fe_final=returnFechaSelect(fFinal,"Fecha Final");
                System.out.println(fe_inicial);
               VerCierreVenta  (new Ventas(0,fe_inicial,fe_final),"rango","grango");

            }

        });
    }
    public String returnFechaSelect(DatePicker txt, String nombre){

        if (txt.getValue()==null){
            Util.Advertencia("Advertencia", "Por favor la selecione " + nombre);
            return null;
        }else{
         return txt.getValue().toString();
        }

    }

    public void iniciarTabla(){
        cellCod=new TableColumn<>("Código");
        cellFecha=new TableColumn<>("Fecha");
        cellCliente=new TableColumn<>("Cliente");

        cellTotal=new TableColumn<>("Total");

        cellCod.setCellValueFactory(new PropertyValueFactory<ResumenVenta,String>("idfactura"));
        cellFecha.setCellValueFactory(new PropertyValueFactory<ResumenVenta,String>("fecha"));
        cellCliente.setCellValueFactory(new PropertyValueFactory<ResumenVenta,String>("cliente"));
        cellTotal.setCellValueFactory(new PropertyValueFactory<ResumenVenta,String>("total"));
        tblVentas.setEditable(true);
        tblVentas.getColumns().addAll(cellCod,cellFecha,cellCliente,cellTotal);
        Platform.runLater(()-> size_tabla.ajustarColumna(tblVentas));

    }
    public void  VerCierreVenta(Ventas ventas,String accion, String gaccion){
        DataVentas dataVentas=new DataVentas();
        ObservableList<ResumenVenta> list= FXCollections.observableArrayList(dataVentas.viewVentas(ventas,accion));
        if (!list.isEmpty()){
            llenarTabla(list);
            GraficarDatos(list);
        }
        ArrayList<Cuenta> datosCueta=new ArrayList<>(dataVentas.viewCuenta(ventas,gaccion));
        if (!datosCueta.isEmpty()){
            lblVentas.setText("Q "+datosCueta.get(0).getVentas());
            lblCompras.setText("Q "+datosCueta.get(0).getInversion());
            lblGanancia.setText("Q "+datosCueta.get(0).getGanancia());
        }
    }
    public void GraficarDatos(ObservableList<ResumenVenta> list){
        ObservableList<XYChart.Series<String,Float>> datosGrafica=FXCollections.observableArrayList();
        XYChart.Series<String,Float> series=new XYChart.Series<>();
         Axis<String> xAxis=lineChart.getXAxis();
         xAxis.setLabel("codigo factura");
         xAxis.isTickMarkVisible();
        xAxis.setAnimated(false);
         xAxis.setSide(Side.TOP);
         Axis<Float> yAxis=lineChart.getYAxis();
         yAxis.setLabel("Monto Factura");

        lineChart.setTitle("Ventas realizadas en gs-accesorios");
        series.setName("ventas");
        for (ResumenVenta venta: list){
            series.getData().add(new XYChart.Data<>(String.valueOf(venta.getFecha()),venta.getTotal()));

        }
        datosGrafica.add(series);
        lineChart.setData(datosGrafica);
    }
    public void llenarTabla(ObservableList<ResumenVenta> list){
        listResumenVenta=FXCollections.observableArrayList(list);
        filteredListVenta=new FilteredList<>(listResumenVenta, p-> true);
        SortedList<ResumenVenta>sortedList=new SortedList<>(filteredListVenta);
        sortedList.comparatorProperty().bind(tblVentas.comparatorProperty());
        tblVentas.setItems(sortedList);

        txtBuscar.textProperty().addListener((prop,old,text) ->{
            filteredListVenta.setPredicate(resumenVenta -> {
                if (text==null || text.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = text.toLowerCase();
                if (String.valueOf(resumenVenta.getIdfactura()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (resumenVenta.getCliente().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
    }
    public String obtnerfechaHoy(){
      return String.valueOf(LocalDate.now());
    }
}
