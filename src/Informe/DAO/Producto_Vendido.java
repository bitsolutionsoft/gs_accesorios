package Informe.DAO;

public class Producto_Vendido {
    int codigo,  cantidad;
String nombre;

    public Producto_Vendido() {
    }

    public Producto_Vendido(int codigo, int cantidad, String nombre) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
