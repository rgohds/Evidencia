public class Cita {
    private String IdentificadorCita;
    private String IdentificadorDoctor;
    private String IdentificadorPaciente;
    private String Fecha;
    private String Hora;
    private String MotivoVisita;

    public Cita(String liddoctor, String lidPaciente, String lFecha, String lHora, String lMotivo) {
        this.IdentificadorCita = liddoctor + lidPaciente + lFecha + lHora ;
        this.IdentificadorDoctor = liddoctor;
        this.IdentificadorPaciente = lidPaciente;
        this.Fecha = lFecha;
        this.Hora = lHora;
        this.MotivoVisita = lMotivo;
    }

    public String GetId() {
        String sreturn;
        sreturn = this.IdentificadorDoctor;
        sreturn += this.IdentificadorPaciente;
        sreturn += this.Fecha;
        sreturn += this.Hora;
        return sreturn;
    }

    public String GetCitaToArchivo() {
        String sreturn;
        sreturn = this.IdentificadorDoctor + ";";
        sreturn += this.IdentificadorPaciente + ";";
        sreturn += this.Fecha + ";";
        sreturn += this.Hora + ";";
        sreturn += this.MotivoVisita;
        return sreturn;
    }

    public String GetCitaToPantalla() {
        String sreturn;
        sreturn = this.IdentificadorDoctor + ":";

        sreturn += this.IdentificadorPaciente + ":";
        sreturn += this.Fecha + ":";
        sreturn += this.Hora + ":";
        sreturn += this.MotivoVisita;
        return sreturn;
    }
}

