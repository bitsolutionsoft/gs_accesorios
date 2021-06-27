package Venta.DAO;

import Conexion.Conexion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modelo_factura {
    int cantidad;
    String descripcion, idProducto;
    float subtotal;
    float precio;

    public  Modelo_factura(){ }

    public  Modelo_factura(int cantidad,String descripcion,float precio, float subtotal){
        this.cantidad=cantidad;
        this.descripcion=descripcion;
        this.precio=precio;
        this.subtotal=subtotal;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }


    public ObservableList<Modelo_factura> datosFactura(ObservableList<DetalleFactura> detalleFacturas){
        ObservableList<Modelo_factura> datos=FXCollections.observableArrayList();
        for (int i=0; i<detalleFacturas.size(); i++){
            Modelo_factura modelo=new Modelo_factura();
            modelo.setCantidad(detalleFacturas.get(i).getCantidad());
            modelo.setDescripcion(detalleFacturas.get(i).getDescripcion());
            modelo.setPrecio(detalleFacturas.get(i).getPrecio());
            modelo.setSubtotal(detalleFacturas.get(i).getSubtotal());
            datos.addAll(modelo);
        }
        return  datos;
    }

}
