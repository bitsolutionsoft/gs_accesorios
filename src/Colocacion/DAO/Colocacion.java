package Colocacion.DAO;

public class Colocacion {
    int idColocacion;
    String nombre, estado;

    public  Colocacion(){}

    public Colocacion(int idcolocacion, String nombre, String estado){
        this.idColocacion = idcolocacion;
        this.nombre = nombre;
        this.estado = estado;
    }

    public int getIdColocacion() {
        return idColocacion;
    }

    public void setIdColocacion(int idColocacion) {
        this.idColocacion = idColocacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
