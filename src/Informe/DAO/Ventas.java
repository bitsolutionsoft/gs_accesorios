package Informe.DAO;

public class Ventas {
    int indventa;
    String fFinal,fInicial;

    public Ventas() {
    }

    public Ventas(int indventa, String fInicial, String fFinal) {
        this.indventa = indventa;
        this.fFinal = fFinal;
        this.fInicial = fInicial;
    }

    public int getIndventa() {
        return indventa;
    }

    public void setIndventa(int indventa) {
        this.indventa = indventa;
    }

    public String getfFinal() {
        return fFinal;
    }

    public void setfFinal(String fFinal) {
        this.fFinal = fFinal;
    }

    public String getfInicial() {
        return fInicial;
    }

    public void setfInicial(String fInicial) {
        this.fInicial = fInicial;
    }
}
