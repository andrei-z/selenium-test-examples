/*
 * source: https://github.com/andrei-z/selenium-test-examples
 **/

package google.translate.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TranslatedPage extends Page {

    @FindBy(css = "span[class='tlid-translation translation'] > span")
    @CacheLookup
    private List<WebElement> translation;

    TranslatedPage(WebDriver driver){
        super("Translated PageObjects.Page", "https://translate.google.com/?hl=en#view=home&op=translate", driver);

        PageFactory.initElements(driver, this);

        if(!isDisplayed())
            throw new RuntimeException(name() + " is not displayed. \nExpected url: " + url() + "\nActual url: " + driver.getCurrentUrl());
    }

    public Boolean translationExists(){
        explicitWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("span[class='tlid-translation translation'] > span")));
        return (translation.size() > 0);
    }

    public void printOut(){
        System.out.println("Translation:" +"\n");
        for(WebElement t : translation){
            System.out.print(t.getText() +"\n");
        }
    }
}