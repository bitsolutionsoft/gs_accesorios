package Venta.DAO;

public class Factura {
    int idfactura, idcliente;
    String estado, motivo_anulacion,fecha;
    float total;

    public Factura(){}

    public Factura(int idfactura, int idcliente, String fecha, String estado, String motivo_anulacion, float total){
        this.idfactura=idfactura;
        this.idcliente=idcliente;
        this.fecha=fecha;
        this.estado=estado;
        this.motivo_anulacion=  motivo_anulacion;
        this.total=total;
    }

    public int getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(int idfactura) {
        this.idfactura = idfactura;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivo_anulacion() {
        return motivo_anulacion;
    }

    public void setMotivo_anulacion(String motivo_anulacion) {
        this.motivo_anulacion = motivo_anulacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
