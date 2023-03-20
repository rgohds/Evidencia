import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Usuarios {
    private HashMap<String, Usuario> Usuarios;
    private String path;

    public Usuarios(String lpath) {
        this.Usuarios = new HashMap<>();
        path = lpath;
    };

    public void Add()  {
        String lUsuario;
        String lPassword;
        Scanner reader;
        boolean salir = false;
        do {
            System.out.println("Introduzca el Usuario");
            reader = new Scanner(System.in);
            lUsuario = reader.nextLine();
            if (!this.Existe(lUsuario)) {
                System.out.println("Introduzca el password");
                reader = new Scanner(System.in);
                lPassword = reader.nextLine();
                Usuario newUsuario = new Usuario(lUsuario,lPassword);
                this.Usuarios.put(lUsuario,newUsuario);
                System.out.println("Cantidad de Usuarios: " + this.Usuarios.size());
                salir = true;

            } else {
                System.out.println("El usuario " + lUsuario + "ya existe");
            }
        } while (!salir);
    }

    public void AddAdm()  {
        String lUsuario = "admin";
        String lPassword = "pwd";
        Usuario newUsuario = new Usuario(lUsuario,lPassword);
        this.Usuarios.put(lUsuario,newUsuario);
    }

    public boolean Existe(String lUsuario2) {
        String lUsuario;
        boolean lreturn = false;
        for (Map.Entry<String, Usuario> entry : this.Usuarios.entrySet()) {
            lUsuario = entry.getKey();
            if (lUsuario2.equals(lUsuario)) {
                lreturn = true;
            }
        }
        return lreturn;
    }

    public boolean Valida() {
        String lUsuario;
        String lUsuario2;
        String lPassword2;
        String lPassword;

        Scanner reader;
        boolean salir = false;
        boolean lencontrado = false;
        boolean lusuarioval = false;
        do {
            System.out.println("---------------------");
            System.out.println("Entrada al sistema");
            System.out.println("---------------------");
            System.out.println("Introduzca el Usuario");
            reader = new Scanner(System.in);
            lUsuario = reader.nextLine();

            System.out.println("Introduzca el Password");
            reader = new Scanner(System.in);
            lPassword = reader.nextLine();

            System.out.println("---------------------");

            lencontrado = false;
            for (Map.Entry<String, Usuario> entry : this.Usuarios.entrySet()) {
                lUsuario2 = entry.getKey();
                Usuario iUsu = entry.getValue();
                lPassword2 = iUsu.GetPassword();
                if (lUsuario2.equals(lUsuario)) {
                    lencontrado = true;
                    if (lPassword2.equals(lPassword)) {
                      lusuarioval = true;
                      salir = true;
                    }
                }
            }
            if (!lencontrado) {
                System.out.println ("Usuario no encontrado o valido");
            } else {
                if (!lusuarioval) {
                    System.out.println ("Password incorrecto");
                }
            }
        } while (!salir);
        return salir;
    }

    public void Save() {
        String lusuario;
        String lpassword;
        String Archivo = path + "Usuarios.txt";

        BufferedWriter fileWrite = null;

        try {
            fileWrite = new BufferedWriter(new FileWriter(Archivo));
            System.out.println ("Total Usuarios : " + this.Usuarios.size());
            for (Map.Entry<String, Usuario> entry : this.Usuarios.entrySet()) {
                lusuario = entry.getKey();
                Usuario iUsu = entry.getValue();
                lpassword = iUsu.GetPassword();
                fileWrite.write(lusuario + ";" + lpassword + "\n" );
            }
            System.out.println ("Usuarios guardados");
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

    public void list() {
        String lusuario;
        System.out.println("Usuarios:");
        for (Map.Entry<String, Usuario> entry : this.Usuarios.entrySet()) {
            lusuario = entry.getKey();
            String output = String.format("%s", lusuario);
            System.out.println(output);
        }
    }

    public void load() {
        String lusuario;
        String lpassword;
        String llinea;
        String Archivo = path + "Usuarios.txt";
        FileReader mifile = null;

        try {
            mifile = new FileReader(Archivo);
            Scanner scanner = new Scanner(mifile);
            scanner.useDelimiter("\n");

            while (scanner.hasNext()) {
                llinea = scanner.next();
                String[] datos = llinea.split(";");
                lusuario = datos[0];
                lpassword = datos[1];
                Usuario newUser = new Usuario(lusuario,lpassword);
                this.Usuarios.put(lusuario,newUser);
            }
            System.out.println ("Total Usuarios : " + this.Usuarios.size());
        } catch(FileNotFoundException e) {
            this.AddAdm();
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
}
