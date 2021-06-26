package Venta.DAO;


public class ProductoDisponible {
    int idproducto, stock, idlote;
    String nombre, modelo, especificacion, sector;
    Float precio;

    public ProductoDisponible() {
    }

    public ProductoDisponible(int idproducto, int stock, int idlote, String nombre, String modelo, String especificacion, String sector, Float precio) {
        this.idproducto = idproducto;
        this.stock = stock;
        this.idlote = idlote;
        this.nombre = nombre;
        this.modelo = modelo;
        this.especificacion = especificacion;
        this.sector = sector;
        this.precio = precio;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdlote() {
        return idlote;
    }

    public void setIdlote(int idlote) {
        this.idlote = idlote;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}
