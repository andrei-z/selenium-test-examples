/*
* source: https://github.com/andrei-z/selenium-test-examples
**/
package google.translate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GoogleTranslateTest {

    private String testUrl = "https://translate.google.com/";
    private static WebDriver driver;

    @BeforeClass
    public static void startDriver(){
        driver = new ChromeDriver();
    }

    @Before
    public void gotoUrl(){
        driver.navigate().to(testUrl);
    }

    @Test
    public void translateAThing(){

        // Source objects
        String sourceLang = "English"; // provide language to translate from
        WebElement sLang = null;
        WebElement sInput = driver.findElement(By.cssSelector("textarea[id='source']"));
        String textToTranslate = "- What's your favorite season?\n- Early Spring is a wonderful time.";

        // Target objects
        String targetLang = "Welsh"; // provide language to translate to
        WebElement tLang = null;

        // Expand source / "from" languages
        WebElement fromDropdown = driver.findElement(By.cssSelector("div[class='sl-more tlid-open-source-language-list']"));
        fromDropdown.click();

        // Collect source languages in a List
        List<WebElement> sLangs = driver.
                findElements(By.xpath("//div[@class='language-list-unfiltered-langs-sl_list']/div[@class='language_list_section']/div[contains(@class, 'language_list_item_wrapper')]"));

        // Locate sourceLang and store it as a WebElement (sLang)
        for(WebElement e : sLangs){
            if(e.getText().equals(sourceLang)){
                sLang = e;
                break;
            }
        }

            Assert.assertNotNull("Source language \""+sourceLang+"\" not found", sLang);

        // Select source language
        sLang.click();

        // Expand target / "to" languages
        WebElement toDropdown = driver.findElement(By.cssSelector("div[class='tl-more tlid-open-target-language-list']"));
        toDropdown.click();

        // Collect target languages in a list
        List<WebElement> tLangs = driver.
                findElements(By.xpath("//div[@class='language-list-unfiltered-langs-tl_list']/div[@class='language_list_section']/div[contains(@class, 'language_list_item_wrapper')]"));

        // Locate targetLang and store it as a WebElement (tLang)
        for(WebElement e : tLangs){
            if(e.getText().equals(targetLang)){
                tLang = e;
                break;
            }
        }

            Assert.assertNotNull("Target language \""+targetLang+"\" not found", tLang);

        // Select target language
        tLang.click();

        // Send text to translate
        sInput.sendKeys(textToTranslate);

        // Wait until the translation appears
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='tlid-translation translation'] > span")));

        // Collect the translation spans
        List<WebElement> translation = driver.findElements(By.cssSelector("span[class='tlid-translation translation'] > span"));

            Assert.assertTrue("Translation was empty", translation.size() != 0);

        // print out the translation
        System.out.printf("%s <-- from %s to %s: \n\n", textToTranslate, sourceLang, targetLang);
        for(WebElement t : translation){
            System.out.print(t.getText() +"\n");
        }
    }
}
