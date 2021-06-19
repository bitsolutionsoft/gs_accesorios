package Producto.DAO;

public class Lote {
    int idlote,idproducto,cantidad;
    float precio_compra,precio_mayorista,precio_mayor,precio_unidad;
    String estado;

    public Lote(){}
    public Lote(int idlote, int idproducto, int cantidad, float precio_compra, float precio_mayorista, float precio_mayor, float precio_unidad, String estado) {
        this.idlote = idlote;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.precio_compra = precio_compra;
        this.precio_mayorista = precio_mayorista;
        this.precio_mayor = precio_mayor;
        this.precio_unidad = precio_unidad;
        this.estado = estado;
    }

    public int getIdlote() {
        return idlote;
    }

    public void setIdlote(int idlote) {
        this.idlote = idlote;
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

    public float getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(float precio_compra) {
        this.precio_compra = precio_compra;
    }

    public float getPrecio_mayorista() {
        return precio_mayorista;
    }

    public void setPrecio_mayorista(float precio_mayorista) {
        this.precio_mayorista = precio_mayorista;
    }

    public float getPrecio_mayor() {
        return precio_mayor;
    }

    public void setPrecio_mayor(float precio_mayor) {
        this.precio_mayor = precio_mayor;
    }

    public float getPrecio_unidad() {
        return precio_unidad;
    }

    public void setPrecio_unidad(float precio_unidad) {
        this.precio_unidad = precio_unidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
