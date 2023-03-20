import java.util.HashMap;
import java.util.Scanner;

public class Doctor {

    private String IdentificadorDoctor;
    private String Nombre;
    private String Especialidad;

    public Doctor(String liddoctor, String lnombre, String lespecialidad) {
        this.IdentificadorDoctor = liddoctor;
        this.Nombre = lnombre;
        this.Especialidad = lespecialidad;
    }

    public String GetNombre() {
        return this.Nombre;
    }
    public String GetEspecialidad() {
        return this.Especialidad;
    }
}
