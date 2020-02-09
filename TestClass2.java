import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class TestClass2 {
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "d:\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.w3schools.com/html/html_tables.asp");

        WebElement tableElement = driver.findElement(By.xpath("//table[@id=\"customers\"]"));

        Table table = new Table(tableElement, driver);



        System.out.println(table.getValueFromCell(1,2));
        System.out.println("Strok v tablice " + table.getRows().size());
        System.out.println("Zagolovki tablici " + table.getHeadings().size());
        System.out.println(table.getRowsWithColumns().size());


        driver.quit();



    }

}
