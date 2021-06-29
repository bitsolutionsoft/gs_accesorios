package ClassAux;

import Informe.DAO.ProductoInvetario;
import Venta.DAO.Modelo_factura;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.collections.map.HashedMap;

import javax.swing.*;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ImprimirVale {
    //private final String path=System.getProperty("user.home")+ File.separator+"gsaccesorios";
    private final String path="C:/"+ File.separator+"gsaccesorios";
    private final String Fac="/Informe/form/gsfactura.jasper";
    private final String Inv="/Informe/form/productos.jasper";


   public void LlenarFactura(List<Modelo_factura> lista, String orden, boolean guardar, boolean imprimir, String fecha, String cliente, String direccion, float total, String nit){
java.io.File nuevoDirectorio=new File(path);
nuevoDirectorio.mkdir();

       Map<String,Object> par= new HashMap<>();

        par.put("Orden",orden);
        par.put("fecha",fecha);
        par.put("cliente",cliente);
        par.put("direccion",direccion);
        par.put("nit",nit);
        par.put("total",total);

        try {
            JasperPrint jPrint = JasperFillManager.fillReport(this.getClass().getResourceAsStream(Fac), par,
                    new JRBeanCollectionDataSource(lista));
            if(imprimir){
                JasperPrintManager.printReport(jPrint, false);
            }
            JasperViewer jasperViewer = new JasperViewer(jPrint,false);
            jasperViewer.setTitle("FACTURA");
            jasperViewer.setVisible(true);
            if(guardar){
                JasperExportManager.exportReportToPdfFile(jPrint,  path+File.separator+getFecha()+"factura"+orden+".pdf");
            }
        } catch (JRException e) {
            e.printStackTrace();
            Util.Advertencia("",""+e);
            JOptionPane.showMessageDialog(null,""+e);
        }
    }

    public void InventarioProducto(List<ProductoInvetario> lista, int totalpro, float totalfinal, boolean imprimir){
        java.io.File nuevoDirectorio=new File(path);
        nuevoDirectorio.mkdir();

        Map<String,Object> par = new HashedMap();
        par.put("totalpro",totalpro);
        par.put("totalfinal",totalfinal);

        try {
            JasperPrint jPrint = JasperFillManager.fillReport(this.getClass().getResourceAsStream(Inv), par,
                    new JRBeanCollectionDataSource(lista));
            if(imprimir){
                JasperPrintManager.printReport(jPrint, false);
            }

            JasperViewer jasperViewer = new JasperViewer(jPrint,false);
            jasperViewer.setTitle("Productos en invetario");
            jasperViewer.setVisible(true);
            JasperExportManager.exportReportToPdfFile(jPrint,  path+File.separator+getFecha()+"invetario.pdf");
        } catch (JRException e) {
            e.printStackTrace();
            Util.Advertencia("",""+e);
        }

    }

    private String getFecha(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);

    }
}
