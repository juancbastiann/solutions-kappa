package Sistema.Ordenes.Dominio;

public class Cliente {
    private int codigo;
    private String cedula;
    private String nombres;

    public Cliente(int codigo, String cedula, String nombres) {
        this.codigo = codigo;
        this.cedula = cedula;
        this.nombres = nombres;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Override
    public String toString() {
        return "Cliente [codigo=" + codigo + ", cedula=" + cedula + ", nombres=" + nombres + "]";
    }

}
