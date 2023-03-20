public class Usuario {
    private String Usuario;
    private String Password;

    public Usuario(String lUsuario, String lPassword) {
        this.Usuario = lUsuario;
        this.Password = lPassword;
    }

    public String GetUsuario() {
        return this.Usuario;
    }
    public String GetPassword() {
        return this.Password;
    }
}
