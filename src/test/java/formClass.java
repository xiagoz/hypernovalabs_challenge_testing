import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

public class formClass {
    public static void main(String[] args) throws InterruptedException {
        // ---- Setup inicial ----
        System.out.println("Inicio de la prueba");
        String URL = "https://hypernovalabs.com/";
        String PATH = "C:\\testing-projects\\hypernovalabs\\formulario_test\\src\\test\\resources\\chrome driver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", PATH);
        WebDriver driver = new ChromeDriver();

        // ---- Comienzo de las pruebas ----
        driver.manage().window().maximize(); // apertura del navegador y mazimizacion de la ventana
        driver.get(URL); // inicio de la página
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"popmake-6710\"]/button")).click(); // Cerrar el popup
        driver.findElement(By.xpath("//*[@id=\"menu-1-1f0c373\"]/li[6]/a")).click(); // Llegar al módulo de Contact
        driver.findElement(By.xpath("//*[@id=\"wpcf7-f2805-p3937-o1\"]/form/p[2]/input")).click(); // Dar click al Botón de Send
        Thread.sleep(2000); //Tiempo de espera de 2s

        // ---- Validaciónes de mensajes ----
        // -- Validando el mensaje al final del form --
        String finalMessage = driver.findElement(By.xpath("//*[@id=\"wpcf7-f2805-p3937-o1\"]/form/div[2]")).getText();
        String expectFinalError = "One or more fields have an error. Please check and try again.";

        if(Objects.equals(finalMessage, expectFinalError)) {
            System.out.println("Mensaje final: Es correcto!");
        } else {
            System.out.println("Algo salío mal en el envió sin datos");
        }
        Thread.sleep(2000);


        // -- Validación del mensaje: Campo requerido --
        driver.navigate().refresh(); //Refrescamos la pagína
        Thread.sleep(2000);
        driver.findElement(By.name("first-name")).sendKeys("Christian"); //Enviamos el texto del nombre
        driver.findElement(By.name("last-name")).sendKeys("Gomez"); //Enviamos el texto del apellido
        driver.findElement(By.xpath("//*[@id=\"wpcf7-f2805-p3937-o1\"]/form/p[2]/input")).click();
        Thread.sleep(5000);

        String requiredField = driver.findElement(By.xpath("//*[@id=\"wpcf7-f2805-p3937-o1\"]/form/p[1]/span[4]/span")).getText();
        String expectRequiredField = "The field is required.";

        if(Objects.equals(requiredField, expectRequiredField)) {
            System.out.println("Campo requerido: El mensaje de notificación es correcto");
        } else {
            System.out.println("Algo salío mal en el campo requerido del correo");
        }
        Thread.sleep(2000);


        // -- Validación del mensaje: Correo Invalido --
        driver.navigate().refresh(); //Refrescamos la pagína
        Thread.sleep(2000);
        driver.findElement(By.name("first-name")).sendKeys("Christian"); //Enviamos el texto del nombre
        driver.findElement(By.name("last-name")).sendKeys("Gomez"); //Enviamos el texto del apellido
        driver.findElement(By.name("email")).sendKeys("chtiangogmail.com"); //Enviamos el texto del correo
        driver.findElement(By.xpath("//*[@id=\"wpcf7-f2805-p3937-o1\"]/form/p[2]/input")).click();
        Thread.sleep(5000);

        String emailInvalid = driver.findElement(By.xpath("//*[@id=\"wpcf7-f2805-p3937-o1\"]/form/p[1]/span[4]/span")).getText();
        String expectemailInvalid = "The e-mail address entered is invalid.";

        if(Objects.equals(emailInvalid, expectemailInvalid)) {
            System.out.println("Correo Inválido: El mensaje de notificación es correcto");
        } else {
            System.out.println("Algo salío mal en el campo del formato de correo");
        }
        Thread.sleep(2000);

        // -- Envío satisfactorio del formulario --
        driver.navigate().refresh(); //Refrescamos la pagína
        Thread.sleep(5000);
        driver.findElement(By.name("first-name")).sendKeys("Christian"); //Enviamos el texto del nombre
        driver.findElement(By.name("last-name")).sendKeys("Gomez"); //Enviamos el texto del apellido
        driver.findElement(By.name("company")).sendKeys("Texto de prueba"); //Enviamos el texto de Company
        driver.findElement(By.name("email")).sendKeys("chtiango@gmail.com"); //Enviamos el texto del correo
        driver.findElement(By.name("subject")).sendKeys("Prueba practica para el puesto de Tester"); //Enviamos el texto de Company
        driver.findElement(By.name("message")).sendKeys("Testing..."); //Enviamos el texto de Company
        driver.findElement(By.xpath("//*[@id=\"wpcf7-f2805-p3937-o1\"]/form/p[2]/input")).click();
        Thread.sleep(10000);

        String successful = driver.findElement(By.xpath("//*[@id=\"wpcf7-f2805-p3937-o1\"]/form/div[2]")).getText();
        String expectSuccessful = "Thank you for your message. It has been sent.";

        if(Objects.equals(successful, expectSuccessful)) {
            System.out.println("Envío del Formulario: Se ha enviado correctamente");
        } else {
            System.out.println("Algo salío mal al enviar el formulario con los datos correctos");
        }

        // -- Finalización de la prueba --
        System.out.println("Fin de la prueba");
        Thread.sleep(10000);
        driver.close();
    }
}
