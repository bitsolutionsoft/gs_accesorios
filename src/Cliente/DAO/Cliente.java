package Cliente.DAO;

public class Cliente {
    int codigo, telefonoUno, telefonoDos;
    String nombre, apellido, sexo, nit;

    public Cliente(){}

    public Cliente(int codigo, String nombre, String apellido, int telefonoUno, int telefonoDos, String sexo, String nit){

        this.codigo=codigo;
        this.nombre=nombre;
        this.apellido=apellido;
        this.telefonoUno=telefonoUno;
        this.telefonoDos=telefonoDos;

        this.sexo=sexo;
        this.nit=nit;

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getTelefonoUno() {
        return telefonoUno;
    }

    public void setTelefonoUno(int telefonoUno) {
        this.telefonoUno = telefonoUno;
    }

    public int getTelefonoDos() {
        return telefonoDos;
    }

    public void setTelefonoDos(int telefonoDos) {
        this.telefonoDos = telefonoDos;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    @Override
    public String toString() {
        return
                 codigo +
                " " + nombre +
                " " + apellido+

                         " " + nit
                ;
    }
}
