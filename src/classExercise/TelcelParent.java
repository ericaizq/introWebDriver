package classExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TelcelParent {

    static WebDriver driver;
    static WebDriverWait wait;


    public static void navegarSitio(String url) {
        //INICIALIZACION DE SYSTEM.SETPROPERTY()
        System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        //se espera 20 segs y  si se agrega en este metodo, se aplica en los demás
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.navigate().to(url); // usamos la url que se mandó arriba en el método
        driver.manage().window().maximize(); // maximiza la pág
        wait = new WebDriverWait(driver, 10);
    }

    public static void verificarLandingPage() {



        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[src='/content/dam/htmls/img/icons/logo-telcel.svg']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-nombreboton='Tienda en linea superior']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#buscador-menu-input")));


    }


    public static void listarTelefonos() {

        WebElement tiendaEnLinea = driver.findElement(By.cssSelector("[data-nombreboton='Tienda en linea superior']"));
        tiendaEnLinea.click();
        WebElement linkTelefonosCelulares = driver.findElement(By.cssSelector(".shortcut-container [data-nombreboton='Telefonos y smartphones']"));
        linkTelefonosCelulares.click();


    }

    public static void seleccionarEstado(String nombreEstado) {


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("marca_nocliente_chosen"))).click();

        WebElement searchEstado = driver.findElement(By.cssSelector(".chosen-search input"));
        searchEstado.sendKeys(nombreEstado);
        WebElement resultado = driver.findElement(By.cssSelector(".chosen-results li"));
        resultado.click();

        //  Actions action = new Actions(driver);
        // action.moveToElement(searchEstado).perform();

        WebElement entrar = driver.findElement(By.id("entrarPerfilador"));
        entrar.click();

    }


    public static void verificarPagResultados() {
        //.owl-pagination

        //List<WebElement> celulares = driver.findElement(By.cssSelector(".comp-telcel-mosaico-equipos"));
        List<WebElement> celulares = driver.findElements(By.cssSelector(".comp-telcel-mosaico-equipos"));
        System.out.println(celulares.size());

        if (celulares.size() > 1){

            System.out.println("La lista se desplegó correctamente");

        }

    }

    public static Celular capturarDatosCelular(int i) {


        String mm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".telcel-mosaico-equipos-marca"))).getText();
        String nombreEquipo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".telcel-mosaico-equipos-nombre-equipo"))).getText();

        String precioEquipo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".telcel-mosaico-equipos-precio")))
                            .getText().replace(",", "").replace("$", "");

        double pe = Double.parseDouble(precioEquipo);


        WebElement textoCapacidad = driver.findElement(By.cssSelector(".telcel-mosaico-equipos-capacidad-numero"));
        String capacidadEquipo = textoCapacidad.getText();
        String[] datos = capacidadEquipo.split(" ");
        String capacidadString = datos[0];
        int numGigas = Integer.parseInt(capacidadString);

        return new Celular(mm, nombreEquipo, pe, numGigas);
    }

    public static void seleccionarCelular(int numCelular) {
        List<WebElement> celulares = driver.findElements(By.cssSelector(".comp-telcel-mosaico-equipos li"));
        System.out.println(celulares.size());
        WebElement celular = celulares.get(numCelular - 1);
        celular.click();

    }

    public static void validarDatosCelular(Celular primerCelular) {

        //.ecommerce-ficha-tecnica-opciones-compra-titulo
        WebElement textoMarcaModelo = driver.findElement(By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra #ecommerce-ficha-tecnica-modelo"));
        String mm = textoMarcaModelo.getText();

        if(primerCelular.getMarcaModelo().equals(mm))
            System.out.println("La marca y modelo coinciden");
        else
            System.out.println("La marca y modelo no coinciden");

        WebElement textoNombre = driver.findElement(By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra #ecommerce-ficha-tecnica-nombre"));
        String nombreEquipo = textoNombre.getText();

        if(primerCelular.getNombre().equals(nombreEquipo))
            System.out.println("El nombre coincide");
        else
            System.out.println("El nombre no  coincide");

        WebElement textoPrecio = driver.findElement(By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra #ecommerce-ficha-tecnica-precio-obj"));
        String precioEquipo = textoPrecio.getText();
        precioEquipo = precioEquipo.replace(",", "");
        precioEquipo = precioEquipo.replace("$", "");
        double pe = Double.parseDouble(precioEquipo);

        if(primerCelular.getPrecio() == pe)
            System.out.println("El precio coincide");
        else
            System.out.println("El precio no coincide");


        WebElement textoCapacidad = driver.findElement(By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra .ecommerce-ficha-tecnica-opciones-compra-caracteristicas-etiqueta"));

        String capacidadEquipo = textoCapacidad.getText();
        String[] datos = capacidadEquipo.split(" ");
        String capacidadString = datos[0];
        int numGigas = Integer.parseInt(capacidadString);

        if(primerCelular.capacidadGB == numGigas)
            System.out.println("La capacidad coincide");
        else
            System.out.println("La capacidad no coincide");
    }

    //tarea que compare con cualquier celular
}
