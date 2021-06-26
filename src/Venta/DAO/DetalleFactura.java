package Venta.DAO;

public class DetalleFactura {
    int iddetalle, idfatura, idproducto, cantidad, idlote;
String descripcion;
    Float precio, subtotal;

    public DetalleFactura() {
    }

    public DetalleFactura(int iddetalle, int idfatura, int idproducto, String descripcion, int cantidad, int idlote, Float precio, Float subtotal) {
        this.iddetalle = iddetalle;
        this.idfatura = idfatura;
        this.idproducto = idproducto;
        this.descripcion=descripcion;
        this.cantidad = cantidad;
        this.idlote = idlote;
        this.precio = precio;
        this.subtotal = subtotal;
    }

    public int getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(int iddetalle) {
        this.iddetalle = iddetalle;
    }

    public int getIdfatura() {
        return idfatura;
    }

    public void setIdfatura(int idfatura) {
        this.idfatura = idfatura;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdlote() {
        return idlote;
    }

    public void setIdlote(int idlote) {
        this.idlote = idlote;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}