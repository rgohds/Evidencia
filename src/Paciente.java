public class Paciente {
    private String IdentificadorPaciente;
    private String Nombre;
    public Paciente(String lidpaciente, String lnombre) {
        this.IdentificadorPaciente = lidpaciente;
        this.Nombre = lnombre;
    }
    public String GetNombre() {
        return this.Nombre;
    }
}
