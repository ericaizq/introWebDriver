package tareas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SongsSinatraParent {
    static WebDriver driver;

    public static void navegar(String url) {
        //INICIALIZACION DE SYSTEM.SETPROPERTY()
        System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        //se espera 20 segs y  si se agrega en este metodo, se aplica en los demás
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.navigate().to(url); // usamos la url que se mandó arriba en el método
        driver.manage().window().maximize(); // maximiza la pág
    }


    public static void realizarLoginCorrecto(String user, String password) {
        //    HomePage:
//    linkLogin: txt="log in "
//
//    LoginPage:
//    usernameTxt: id="username"
//    passwordTxt: id="password"
//    loginBtn: value="Log In"
    }

    public static void validarHomePage() {
        WebElement imgSinatra = driver.findElement(By.cssSelector("src=\"/images/sinatra.jpg"));
        WebElement linkLogin = driver.findElement(By.linkText("log in"));


    }



    public static void validarMensageBienvenida(String msgBienvenida) {

        // HomePage:
        //mensajeBienvenida: id="flash"
        //linkLogout: href="/logout"
    }

    public static void cerrarBrowser() {

        driver.quit();
    }
}
