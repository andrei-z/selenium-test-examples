/*
 * source: https://github.com/andrei-z/selenium-test-examples
 **/

package google.translate;

import google.translate.PageObjects.StartPage;
import google.translate.PageObjects.TranslatedPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class SampleTest {

    private WebDriver driver;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void translateFromEnglishToWelsh(){
        StartPage clean = new StartPage(driver);
        clean.selectFrom("English");
        clean.selectTo("Welsh");

        TranslatedPage translated = clean.translateText("- What's your favorite season?\n- Early Spring is a wonderful time.");

        assertTrue("Translation is empty", translated.translationExists());

        translated.printOut(); // print out the translation (System.out)

    }
}