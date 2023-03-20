import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Pacientes {

    private HashMap<String, Paciente> Pacientes;
    private String path;

    public Pacientes(String lpath) {
        this.Pacientes = new HashMap<>();
        path = lpath;
    };

    public void Add()  {
        String lidpaciente;
        String lnombre;
        Scanner reader;
        boolean salir = false;
        do {
            System.out.println("Introduzca el identificador del paciente");
            reader = new Scanner(System.in);
            lidpaciente = reader.nextLine();
            if (!this.Existe(lidpaciente)) {
                System.out.println("Introduzca el nombre del paciente");
                reader = new Scanner(System.in);
                lnombre = reader.nextLine();
                Paciente newPac = new Paciente(lidpaciente,lnombre);
                Pacientes.put(lidpaciente,newPac);
                System.out.println("Cantidad de Pacientes: " + Pacientes.size());
                salir = true;
            } else {
                System.out.println("El identificador " + lidpaciente + "ya existe");
            }
        } while (!salir);
    }

    public boolean Existe(String lidpaciente2) {
        String lidpaciente;
        boolean lreturn = false;
        for (Map.Entry<String, Paciente> entry : Pacientes.entrySet()) {
            lidpaciente = entry.getKey();
            if (lidpaciente2.equals(lidpaciente)) {
                lreturn = true;
            }
        }
        return lreturn;
    }

    public void Save() {
        String lidpaciente;
        String lnombre;
        String Archivo = path + "Pacientes.txt";

        BufferedWriter fileWrite = null;

        try {
            fileWrite = new BufferedWriter(new FileWriter(Archivo));
            System.out.println ("Total Pacientes : " + this.Pacientes.size());
            for (Map.Entry<String, Paciente> entry : this.Pacientes.entrySet()) {
                lidpaciente = entry.getKey();
                Paciente iPac = entry.getValue();
                lnombre = iPac.GetNombre();
                fileWrite.write(lidpaciente + ";" + lnombre + "\n" );
            }
            System.out.println ("Pacientes guardados");
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
        String lidpaciente;
        String lnombre;
        String llinea;
        String Archivo = path + "Pacientes.txt";

        FileReader mifile = null;

        try {
            mifile = new FileReader(Archivo);
            Scanner scanner = new Scanner(mifile);
            scanner.useDelimiter("\n");

            while (scanner.hasNext()) {
                llinea = scanner.next();
                String[] datos = llinea.split(";");
                lidpaciente = datos[0];
                lnombre = datos[1];
                Paciente newPaciente = new Paciente(lidpaciente,lnombre);
                this.Pacientes.put(lidpaciente,newPaciente);
            }
            System.out.println ("Total Pacientes : " + this.Pacientes.size());
        } catch(FileNotFoundException e) {
            //System.out.println("IOException catched while reading: " + e.getMessage());
        }
        catch(IOException e) {
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
        String lidpaciente;
        String lnombre;
        System.out.println("Pacientes:");
        for (Map.Entry<String, Paciente> entry : this.Pacientes.entrySet()) {
            lidpaciente = entry.getKey();
            Paciente lPaciente = entry.getValue();
            lnombre = lPaciente.GetNombre();
            String output = String.format("%s : %s ", lidpaciente, lnombre);
            System.out.println(output);
        }
    }
}
