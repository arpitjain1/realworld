package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Feeds {
    public Feeds(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//a[starts-with(@class,'tag-default')]")
    private List<WebElement> tag_Names;

    @FindBy(how = How.CSS, using = "div.col-md-9 > div > ul")
    private WebElement list_of_articles;

    @FindBy(how = How.XPATH, using = "//ul[contains(@class, 'tag-list')]")
    private List<WebElement> list_of_feeds;

    public List<String> verify_tag_present_on_feed (WebDriver driver, String tag) {
        WebElement webElement = list_of_articles;
        List<WebElement> children = webElement.findElements(By.xpath("./child::*"));
        List<String> tags_on_feed = new ArrayList<>();

        for (WebElement element : children) {
            String text = element.getText();
            tags_on_feed.add(text);

            if (text.trim().equals(tag)) {
                JavascriptExecutor js = (JavascriptExecutor)driver;
                Object output = js.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", element);
                String result = output.toString();
                tags_on_feed.add(result);
            }
        }
        return tags_on_feed;
    }

    public List<String> list_all_elements (WebDriver driver) throws Exception {
        List<String> elements_tags = new ArrayList<>();
        List<WebElement> webElements = list_of_feeds;

        int timer = 1;
        while (webElements.size()==0 && timer<5) {
            Thread.sleep(3000);
            webElements = driver.findElements(By.xpath("//ul[contains(@class, 'tag-list')]"));
            timer++;
        }

        if (webElements.size()==0) {
            System.out.println("no list obtained after retry");
            return elements_tags;
        }

        for (WebElement element : webElements) {
            String text = element.getText();
            elements_tags.add(text);
        }
        return elements_tags;
    }

    public List<WebElement> find_Tag_Elements (WebDriver driver, String tagName) throws Exception {
        List<WebElement> elementList = new ArrayList<>();

        int timer = 1;
        while (elementList.isEmpty() && timer <= 5) {
            timer++;
            for (WebElement element : tag_Names) {
                String text = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element)).getText();
                if (text.equals(tagName)) {
                    elementList.add(element);
                }
            }
            if (elementList.isEmpty()) {
                Thread.sleep(3000);
            }
        }
        return elementList;
    }

    public List<WebElement> find_Tag_Elements (WebDriver driver) throws Exception {
        List<WebElement> elementList = new ArrayList<>();

        int timer = 1;
        while (elementList.isEmpty() && timer <= 5) {
            timer++;
            for (WebElement element : tag_Names) {
                String text = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element)).getText();

                if (text != null) {
                    text = text.trim();
                    if (!text.equals("")) {
                        System.out.println("text:" + text + ":with length:" + text.length());
                        elementList.add(element);
                    }
                }
            }
            if (elementList.isEmpty()) {
                Thread.sleep(3000);
            }
        }
        return elementList;
    }

    public void clickOnTag (WebDriver driver, WebElement element) throws Exception {
        int timeout = 10;
        new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(element)).click();
        waitForLoad(driver,timeout);
        Thread.sleep(2000);
    }

    public void waitForLoad(WebDriver driver, int timeout) {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(pageLoadCondition);
    }
}
