public class Registro {
    private final String codigo;

    public Registro(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCodigoNumerico() {
        return Integer.parseInt(codigo);
    }
}
    

