/*
 * source: https://github.com/andrei-z/selenium-test-examples
 **/

package google.translate.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class StartPage extends Page {

    @FindBy(css = "textarea[id='source']")
    @CacheLookup
    private WebElement inputArea;

    @FindBy(css = "div[class='sl-more tlid-open-source-language-list']")
    @CacheLookup
    private WebElement fromDropdownBtn;

    @FindBy(xpath = "//div[@class='language-list-unfiltered-langs-sl_list']/div[@class='language_list_section']/div[contains(@class, 'language_list_item_wrapper')]")
    @CacheLookup
    private List<WebElement> sourceLanguages;

    @FindBy(css = "div[class='tl-more tlid-open-target-language-list']")
    @CacheLookup
    private WebElement toDropdownBtn;

    @FindBy(xpath = "//div[@class='language-list-unfiltered-langs-tl_list']/div[@class='language_list_section']/div[contains(@class, 'language_list_item_wrapper')]")
    @CacheLookup
    private List<WebElement> targetLanguages;


    public StartPage(WebDriver driver){
        super("Start PageObjects.Page", "https://translate.google.com/?hl=en", driver);

        PageFactory.initElements(driver(), this);

        driver().get(url());

        if(!isDisplayed())
            throw new RuntimeException(name() + " is not displayed. \nExpected url: " + url() + "\nActual url: " + driver.getCurrentUrl());
    }

    public void selectFrom(String sourceLang){
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

    public void selectTo(String targetLang){
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