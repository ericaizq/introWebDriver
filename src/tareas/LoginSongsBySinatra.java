package tareas;

public class LoginSongsBySinatra extends SongsSinatraParent {
    public static void main(String[] args) {

        navegar("https://evening-bastion-49392.herokuapp.com/");
        validarHomePage();
        realizarLoginCorrecto("Frank", "Sinatra");
        validarHomePage();
        validarMensageBienvenida("");
        cerrarBrowser();

    }




}
