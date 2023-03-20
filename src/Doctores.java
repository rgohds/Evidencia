import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Doctores {
    private HashMap<String, Doctor> Doctores;
    private String path;

    public Doctores(String lpath) {
        this.Doctores = new HashMap<>();
        path = lpath;
    };

    public void Add()  {
        String liddoctor;
        String lnombre;
        String lespecialidad;
        Scanner reader;
        boolean salir = false;
        do {
            System.out.println("Introduzca el identificador del doctor");
            reader = new Scanner(System.in);
            liddoctor = reader.nextLine();
            if (!this.Existe(liddoctor)) {
                System.out.println("Introduzca el nombre del doctor");
                reader = new Scanner(System.in);
                lnombre = reader.nextLine();
                System.out.println("Introduzca la especialidad del doctor");
                reader = new Scanner(System.in);
                lespecialidad = reader.nextLine();
                Doctor newDoc = new Doctor(liddoctor,lnombre,lespecialidad);
                this.Doctores.put(liddoctor,newDoc);
                System.out.println("Cantidad de Doctores: " + Doctores.size());
                salir = true;
            } else {
                System.out.println("El identificador " + liddoctor + "ya existe");
            }
        } while (!salir);
    }

    public boolean Existe(String liddoctor2) {
        String liddoctor;
        boolean lreturn = false;
        for (Map.Entry<String, Doctor> entry : this.Doctores.entrySet()) {
            liddoctor = entry.getKey();
            if (liddoctor2.equals(liddoctor)) {
                lreturn = true;
            }
        }
        return lreturn;
    }

    public void Save() {
        String liddoctor;
        String lnombre;
        String lespecialidad;
        String Archivo = path + "Doctores.txt";

        BufferedWriter fileWrite = null;

        try {
            fileWrite = new BufferedWriter(new FileWriter(Archivo));
            System.out.println ("Total Doctores : " + this.Doctores.size());
            for (Map.Entry<String, Doctor> entry : this.Doctores.entrySet()) {
                liddoctor = entry.getKey();
                Doctor iDoc = entry.getValue();
                lnombre = iDoc.GetNombre();
                lespecialidad = iDoc.GetEspecialidad();
                fileWrite.write(liddoctor + ";" + lnombre + ";" + lespecialidad + "\n" );
            }
            System.out.println ("Doctores guardados");
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
        String liddoctor;
        String lnombre;
        String lespecialidad;
        String llinea;
        String Archivo = path + "Doctores.txt";
        FileReader mifile = null;

        try {
            mifile = new FileReader(Archivo);
            Scanner scanner = new Scanner(mifile);
            scanner.useDelimiter("\n");

            while (scanner.hasNext()) {
                llinea = scanner.next();
                String[] datos = llinea.split(";");
                liddoctor = datos[0];
                lnombre = datos[1];
                lespecialidad = datos[2];
                Doctor newDoc = new Doctor(liddoctor,lnombre,lespecialidad);
                this.Doctores.put(liddoctor,newDoc);
            }
            System.out.println ("Total Doctores : " + this.Doctores.size());
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
        String liddoctor;
        String lnombre;
        String lespecialidad;
        System.out.println("Doctores:");
        for (Map.Entry<String, Doctor> entry : this.Doctores.entrySet()) {
            liddoctor = entry.getKey();
            Doctor iDoc = entry.getValue();
            lnombre = iDoc.GetNombre();
            lespecialidad = iDoc.GetEspecialidad();
            String output = String.format("%s : %s : %s", liddoctor, lnombre, lespecialidad);
            System.out.println(output);
        }
    }
}
