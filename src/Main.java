import java.util.Scanner;

public class Main {

    private static Citas lCitas = null;


    public static void main(String[] args) {
        //Create(args[0]);
        lCitas = new Citas(args[0]);
        lCitas.loadAll();
        menu();
    }

    public static void menu() {
        int opcion = 0;
        lCitas.lUsuarios.Valida();
        do
        {
            System.out.println("------------------------------------------");
            System.out.println("Selecciona una de las siguientes opciones:");
            System.out.println("1 - Alta de doctores");
            System.out.println("2 - Lista de doctores");
            System.out.println("3 - Alta de pacientes");
            System.out.println("4 - Lista de pacientes");
            System.out.println("5 - Crear Cita");
            System.out.println("6 - Listar Citas");
            System.out.println("7 - Alta de Usuario");
            System.out.println("8 - Lista de Usuarios");
            System.out.println("9 - Salvar y salir");
            Scanner reader = new Scanner(System.in);
            opcion = reader.nextInt();
            reader.reset();
            switch (opcion) {
                case 1:
                    lCitas.lDoctores.Add();
                    break;
                case 2:
                    lCitas.lDoctores.list();
                    break;
                case 3:
                    lCitas.lPacientes.Add();
                    break;
                case 4:
                    lCitas.lPacientes.list();
                    break;
                case 5:
                    lCitas.Add();
                    break;
                case 6:
                    lCitas.list();
                    break;
                case 7:
                    lCitas.lUsuarios.Add();
                    break;
                case 8:
                    lCitas.lUsuarios.list();
                    break;
                case 9:
                    lCitas.lDoctores.Save();
                    lCitas.lPacientes.Save();
                    lCitas.lUsuarios.Save();
                    lCitas.Save();
            }
        } while (opcion!=9);
    }
}