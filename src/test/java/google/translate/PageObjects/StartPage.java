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

import static org.junit.Assert.assertNotNull;

public class StartPage extends Page {

    @FindBy(css = "textarea[class='er8xn']")
    @CacheLookup
    private WebElement inputArea;

    @FindBy(xpath = "(//span[@class='zQ0atf'])[1]")
    @CacheLookup
    private WebElement fromDropdownBtn;

    @FindBy(css = "div[class='ordo2']")
    @CacheLookup
    private List<WebElement> sourceLanguages;

    @FindBy(xpath = "(//span[@class='zQ0atf'])[2]")
    @CacheLookup
    private WebElement toDropdownBtn;

    @FindBy(css = "div[class='ordo2']")
    @CacheLookup
    private List<WebElement> targetLanguages;

    @FindBy(css = "div[class='SL5JTc']")
    @CacheLookup
    private List<WebElement> langsList;


    public StartPage(WebDriver driver){
        super("Start PageObjects.Page", "https://translate.google.com/?hl=en", driver);

        PageFactory.initElements(driver(), this);

        driver().get(url());

        if(!isDisplayed("translate.google.com"))
            throw new RuntimeException(name()
                    + " is not displayed. \nExpected url: " + url() + "\nActual url: " + driver.getCurrentUrl());
    }

    public void selectFrom(String sourceLang) {
        fromDropdownBtn.click();

        WebElement sLang = null;
        for(WebElement e : sourceLanguages){
            if(e.getText().equals(sourceLang)){
                sLang = e;
                break;
            }
        }
        assertNotNull("Source language \""+sourceLang+"\" not found", sLang);
        sLang.click();
    }

    public void selectTo(String targetLang) {
        // wait for "from" languages list to collapse
        explicitWait().until(ExpectedConditions.invisibilityOfAllElements(langsList));

        toDropdownBtn.click();

        WebElement tLang = null;
        for(WebElement e : targetLanguages){
            if(e.getText().equals(targetLang)){
                tLang = e;
                break;
            }
        }
        assertNotNull("Target language \""+targetLang+"\" not found", tLang);
        tLang.click();
    }

    public TranslatedPage translateText(String textToTranslate){
        inputArea.sendKeys(textToTranslate);

        return new TranslatedPage(driver());
    }
}