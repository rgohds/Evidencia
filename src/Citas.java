import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Citas {

    private HashMap<String, Cita> Citas;
    private String path;
    public Doctores lDoctores = null;
    public Pacientes lPacientes = null;
    public Usuarios lUsuarios = null;

    public Citas(String lpath) {
        this.Citas = new HashMap<>();
        lDoctores = new Doctores(lpath);
        lPacientes = new Pacientes(lpath);
        lUsuarios = new Usuarios(lpath);
        path = lpath;
    };

    public void Add()  {
        String lidCita;
        String liddoctor;
        String lnombreDoctor;
        String lidPaciente;
        String lnombrePaciente;
        String lFecha;
        String lHora;
        String lMotivo;
        Scanner reader;
        boolean salir = false;
        do {
            System.out.println("Introduzca el identificador del doctor");
            this.lDoctores.list();
            System.out.println("--------------------------------------");
            reader = new Scanner(System.in);
            liddoctor = reader.nextLine();
            if (this.lDoctores.Existe(liddoctor)) {
                System.out.println("Introduzca el identificador del paciente");
                this.lPacientes.list();
                System.out.println("----------------------------------------");
                reader = new Scanner(System.in);
                lidPaciente = reader.nextLine();
                if (this.lPacientes.Existe(lidPaciente)) {
                    System.out.println("Introduzca la Fecha de la cita");
                    lFecha = reader.nextLine();
                    System.out.println("Introduzca la Hora de la cita");
                    reader = new Scanner(System.in);
                    lHora = reader.nextLine();
                    if (!this.Existe(liddoctor,lidPaciente,lFecha,lHora))
                        {
                            System.out.println("Introduzca el motivo de la cita");
                            reader = new Scanner(System.in);
                            lMotivo = reader.nextLine();

                            Cita newCita = new Cita(liddoctor,lidPaciente,lFecha,lHora,lMotivo);
                            lidCita = newCita.GetId();
                            this.Citas.put(lidCita,newCita);
                            System.out.println("Cantidad de Citas: " + this.Citas.size());
                            salir = true;
                        }
                    else {
                        System.out.println("La cita con el doctor " + liddoctor + "en este horario ya existe");
                    }
                } else {
                    System.out.println("El identificador " + lidPaciente + "del paciente no existe");
                }
            } else {
                System.out.println("El identificador " + liddoctor + "del doctor no existe");
            }
        } while (!salir);
    }

    public boolean Existe(String liddoctor, String lidPaciente, String lFecha, String lHora) {
        String liid;
        liid = liddoctor + lidPaciente + lFecha + lHora;
        boolean lreturn = false;
        for (Map.Entry<String, Cita> entry : this.Citas.entrySet()) {
            liddoctor = entry.getKey();
            if (liid.equals(liddoctor)) {
                lreturn = true;
            }
        }
        return lreturn;
    }

    public void Save() {
        String lidCita;
        String liddoctor;
        String lnombreDoctor;
        String lidPaciente;
        String lnombrePaciente;
        String lFecha;
        String lHora;
        String lMotivo;
        String Archivo = path + "Citas.txt";

        BufferedWriter fileWrite = null;

        try {
            fileWrite = new BufferedWriter(new FileWriter(Archivo));
            System.out.println ("Total Citas : " + this.Citas.size());
            for (Map.Entry<String, Cita> entry : this.Citas.entrySet()) {
                lidCita = entry.getKey();
                Cita iCita = entry.getValue();
                String lline = iCita.GetCitaToArchivo();
                fileWrite.write(lline + "\n" );
            }
            System.out.println ("Citas guardados");
        } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } finally {
            try {
                if (fileWrite != null) {
                    fileWrite.close();
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }

    public void load() {
        String lidCita;
        String liddoctor;
        String lnombreDoctor;
        String lidPaciente;
        String lnombrePaciente;
        String lFecha;
        String lHora;
        String lMotivo;
        String llinea;
        String Archivo = path + "Citas.txt";
        FileReader mifile = null;

        try {
            mifile = new FileReader(Archivo);
            Scanner scanner = new Scanner(mifile);
            scanner.useDelimiter("\n");

            while (scanner.hasNext()) {
                llinea = scanner.next();
                String[] datos = llinea.split(";");
                liddoctor = datos[0];
                lidPaciente = datos[1];
                lFecha = datos[2];
                lHora = datos[3];
                lMotivo = datos[4];
                Cita newCita = new Cita(liddoctor,lidPaciente,lFecha,lHora,lMotivo);
                lidCita = newCita.GetId();
                this.Citas.put(lidCita,newCita);
            }
            System.out.println ("Total Citas : " + this.Citas.size());
        } catch(FileNotFoundException e) {
            //System.out.println("IOException catched while reading: " + e.getMessage());
        } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } finally {
            try {
                if (mifile != null) {
                    mifile.close();
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }

    public void list() {
        String lidCita;
        String llinea;
        System.out.println("Citas:");
        for (Map.Entry<String, Cita> entry : this.Citas.entrySet()) {
            lidCita = entry.getKey();
            Cita iCita = entry.getValue();
            llinea = iCita.GetCitaToPantalla();
            System.out.println(llinea);
        }
    }
    public void loadAll() {
        this.lDoctores.load();
        this.lPacientes.load();
        this.lUsuarios.load();
        this.load();
    }
}
