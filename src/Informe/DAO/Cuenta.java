package Informe.DAO;

public class Cuenta {
    float ventas, inversion,ganancia;

    public Cuenta() {
    }

    public Cuenta(float ventas, float inversion, float ganancia) {
        this.ventas = ventas;
        this.inversion = inversion;
        this.ganancia = ganancia;
    }

    public float getVentas() {
        return ventas;
    }

    public void setVentas(float ventas) {
        this.ventas = ventas;
    }

    public float getInversion() {
        return inversion;
    }

    public void setInversion(float inversion) {
        this.inversion = inversion;
    }

    public float getGanancia() {
        return ganancia;
    }

    public void setGanancia(float ganancia) {
        this.ganancia = ganancia;
    }
}
