package Proveedor.DAO;

public class Proveedor {
    int idProveedor, telefonoUno, telefonoDos;
    String nombre, apellido, compania, direccion, sexo, estado;

    public  Proveedor(){}

    public Proveedor (int idproveedor, String nombre, String apellido, int telefonouno, int telefonodos, String compania, String direccion, String sexo, String estado){
        this.idProveedor = idproveedor;
        this.nombre=nombre;
        this.apellido=apellido;
        this.telefonoUno=telefonouno;
        this.telefonoDos=telefonodos;
        this.compania=compania;
        this.direccion=direccion;
        this.sexo =sexo;
        this.estado =estado;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
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

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return //"Proveedor{" +
               // "idProveedor=" + idProveedor +
                //", telefonoUno=" + telefonoUno +
                //", telefonoDos=" + telefonoDos +
                " " + nombre +
                " " + apellido +
                ";  " + compania +
                "; " + direccion
               // ", sexo='" + sexo + '\'' +
               // ", estado='" + estado + '\'' +
               // '}'
                ;
    }
}
