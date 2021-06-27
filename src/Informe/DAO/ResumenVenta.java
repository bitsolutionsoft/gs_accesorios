package Informe.DAO;

public class ResumenVenta {
    int idfactura;
    String fecha,cliente;
    float total;

    public ResumenVenta() {
    }

    public ResumenVenta(int idfactura, String fecha, String cliente, float total) {
        this.idfactura = idfactura;
        this.fecha = fecha;
        this.cliente = cliente;
        this.total = total;
    }

    public int getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(int idfactura) {
        this.idfactura = idfactura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
