module gs.accesorios {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    requires mysql.connector.java;
    requires java.sql;
    requires TrayNotification;
    requires java.desktop;
    requires jasperreports;
    requires commons.beanutils;
    requires commons.digester;
    requires commons.javaflow;
    requires commons.logging;
    requires commons.collections;
    requires jasperreports.fonts;
    requires itextpdf;


    opens Menu;
    opens Style;
    opens Producto;
    opens Producto.DAO;
    opens img;
    opens Cliente;
    opens Libreria;
    opens Conexion;
    opens Informe;
    opens Informe.DAO;
    opens Colocacion;
    opens  Colocacion.DAO;
    opens Venta;
    opens Venta.DAO;
    opens Proveedor;
    opens Proveedor.DAO;
    opens ClassAux;


}