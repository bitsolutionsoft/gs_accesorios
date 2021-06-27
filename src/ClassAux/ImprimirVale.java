package ClassAux;

import Venta.DAO.DetalleFactura;
import Venta.DAO.Modelo_factura;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ImprimirVale {
    private final String path=System.getProperty("user.home")+ File.separator+"gsaccesorios"+File.separator+"gsfactura.jasper";
    private final String rutaFactura=path+File.separator+"plantillas"+File.separator+"facturare.jasper";
   public void LlenarFactura(List<Modelo_factura> lista, String orden, boolean guardar, boolean imprimir, String fecha, String cliente, String direccion, float total, String nit){

       Map<String,Object> par= new HashMap<>();

        par.put("Orden",orden);
        par.put("fecha",fecha);
        par.put("cliente",cliente);
        par.put("direccion",direccion);
        par.put("nit",nit);
        par.put("total",total);

        // par.put("logo2", this.getClass().getResourceAsStream(logo2));
        try {
            JasperPrint jPrint = JasperFillManager.fillReport(path, par,
                    new JRBeanCollectionDataSource(lista));
            if(imprimir){
                JasperPrintManager.printReport(jPrint, false);
            }
            JasperViewer jasperViewer = new JasperViewer(jPrint,false);
            jasperViewer.setTitle("FACTURA");
            jasperViewer.setVisible(true);
            if(guardar){
                JasperExportManager.exportReportToPdfFile(jPrint,  path+File.separator+getFecha()+" Factura "+orden+".pdf");
                //  JasperExportManager.exportReportToPdfFile(jPrint,  path+File.separator+"constancia"+File.separator+"factura"+orden+".pdf");
            }
        } catch (JRException e) {
            e.printStackTrace();
            Util.Advertencia("",""+e);
            JOptionPane.showMessageDialog(null,""+e);
        }
    }

    private String getFecha(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);

    }
}
