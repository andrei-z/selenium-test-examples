/*
 * source: https://github.com/andrei-z/selenium-test-examples
 **/

package google.translate.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public abstract class Page {

    private String name = "";
    private String url = "";
    private WebDriver driver;
    private WebDriverWait explicitWait;

    Page(String name, String url, WebDriver driver){
        this.name = name;
        this.url = url;
        this.driver = driver;
        this.explicitWait = new WebDriverWait(driver, 5);
    }

    boolean isDisplayed(){
        boolean result;

        try{
            result = explicitWait().until(urlContains(url));
        }catch (Exception e){
            result = false;
        }
        return result;
    }

    String url(){
        return url;
    }

    String name(){
        return name;
    }

    protected WebDriver driver(){
        return driver;
    }

    WebDriverWait explicitWait(){
        return explicitWait;
    }
}