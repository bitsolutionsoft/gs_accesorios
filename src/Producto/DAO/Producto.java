package Producto.DAO;

public class Producto {
    int codigo, stock, minimo,maximo, idcolocacion,idproveedor,idlote,cantidad;
    String nombre, modelo,especificacion,estado,proveedor,colocacion;
    Float precio_compra, precio_mayorista,precio_mayor,precio_unidad;

    public  Producto(){}


    public Producto(int codigo,String nombre, String modelo, String especificacion, int stock, float precio_compra, float precio_mayorista,
                    float precio_mayor, float precio_unidad, int maximo, int minimo, int idcolocacion, int idproveedor,String estado,int idlote,
                    int cantidad, String proveedor, String colocacion
                    ){
        this.codigo=codigo;
        this.nombre=nombre;
        this.modelo=modelo;
        this.especificacion=especificacion;
        this.stock=stock;
        this.precio_compra=precio_compra;
        this.precio_mayorista=precio_mayorista;
        this.precio_mayor=precio_mayor;
        this.precio_unidad=precio_unidad;
        this.maximo=maximo;
        this.minimo=minimo;
        this.idcolocacion=idcolocacion;
        this.idproveedor=idproveedor;
        this.estado=estado;
        this.idlote=idlote;
        this.cantidad=cantidad;
        this.proveedor=proveedor;
        this.colocacion=colocacion;


    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Float getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(Float precio_compra) {
        this.precio_compra = precio_compra;
    }

    public Float getPrecio_mayorista() {
        return precio_mayorista;
    }

    public void setPrecio_mayorista(Float precio_mayorista) {
        this.precio_mayorista = precio_mayorista;
    }

    public Float getPrecio_mayor() {
        return precio_mayor;
    }

    public void setPrecio_mayor(Float precio_mayor) {
        this.precio_mayor = precio_mayor;
    }

    public Float getPrecio_unidad() {
        return precio_unidad;
    }

    public void setPrecio_unidad(Float precio_unidad) {
        this.precio_unidad = precio_unidad;
    }

    public int getIdcolocacion() {
        return idcolocacion;
    }

    public void setIdcolocacion(int idcolocacion) {
        this.idcolocacion = idcolocacion;
    }

    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    public int getIdlote() {
        return idlote;
    }

    public void setIdlote(int idlote) {
        this.idlote = idlote;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getColocacion() {
        return colocacion;
    }

    public void setColocacion(String colocacion) {
        this.colocacion = colocacion;
    }

    @Override
    public String toString() {
        return
                "nombre: " + nombre+";\t"  +
                " modelo: " + modelo + ";\t" +
                "especificacion: " + especificacion + ";\t";
    }
}
