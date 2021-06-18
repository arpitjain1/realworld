package conduit.steps;

import io.cucumber.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.Feeds;

import io.cucumber.java.en.Given;

import java.util.List;
import java.util.Random;

import conduit.hooks.Setup;

public class TagsSteps {
    private WebDriver driverToUse;
    Feeds feeds;

    public TagsSteps () {
        driverToUse=Setup.driver;
    }

    @Given("^I filter the page by \"([^\"]*)\" tag that is \"([^\"]*)\"$")
    public void i_filter_by_tag (String tag, String validityCheck) throws Exception {
        feeds = new Feeds(driverToUse);
        List<WebElement> elementList = feeds.find_Tag_Elements(driverToUse, tag);

        if (elementList==null || elementList.size()==0) {
            if (validityCheck.equals("invalid")) {
                System.out.println("Expected to be no tags present for " + tag);
                return;
            }
            Assert.fail("Specified tag are taking too much time to load, failing the test");
        }

        Random random = new Random();
        int num = random.ints(0,elementList.size()).findFirst().getAsInt();
        WebElement element = elementList.get(num);
        feeds.clickOnTag(driverToUse, element);
        System.out.println("clicked on tag for element:"+element);
    }

    @And("^I verify for all occurrences of \"([^\"]*)\" tag on the page$")
    public void i_verify_for_all_occurrences_of_tag (String tag) throws Exception {
        List<String> results = feeds.list_all_elements(driverToUse);

        if (results==null || results.size()==0) {
            Assert.fail(tag + " tag found on the page");
        }

        for (String result : results) {
            if (result.contains(tag)) {
                System.out.println("tag " + tag + " is present in list of tags:"+result);
            } else {
                Assert.fail("tag " + tag + " is not present in list of tags:"+result);
            }
        }
    }

    @And("^I verify for \"([^\"]*)\" tag on the feed title$")
    public void i_verify_for_title (String tag) {
        List<String> feed_titles = feeds.verify_tag_present_on_feed(driverToUse,tag);
        boolean isVisible = false;

        if (feed_titles==null || feed_titles.size()==0) {
            Assert.fail("none of the tags are present on the feed");
        }

        if (feed_titles.contains(tag)) {
            for (String title : feed_titles) {
                if (title.contains("ng-show") && !title.contains("ng-hide")) {
                    isVisible=true;
                }
            }
        } else {
            Assert.fail("tag " + tag + " is not present in feed headings");
        }

        if (!isVisible) {
            Assert.fail("tag " + tag + " is not visible on the page feed heading");
        }
    }

    @Given("^I filter the page by \"([^\"]*)\" tags and verify for all occurrences$")
    public void i_filter_by_tags_and_verify (String tag) throws Exception {
        feeds = new Feeds(driverToUse);
        List<WebElement> elementList = feeds.find_Tag_Elements(driverToUse, tag);

        if (elementList==null || elementList.size()==0) {
            Assert.fail("Specified tag taking too much time to load, failing the test");
        }

        for (WebElement element : elementList) {
            feeds.clickOnTag(driverToUse, element);
            System.out.println("clicked on tag for element:"+element);
            i_verify_for_all_occurrences_of_tag(tag);
            i_verify_for_title(tag);
        }
    }

    @Given("^I filter the page by all tags and verify for all occurrences$")
    public void i_filter_by_all_tags_and_verify () throws Exception {
        feeds = new Feeds(driverToUse);
        List<WebElement> elementList = feeds.find_Tag_Elements(driverToUse);

        if (elementList==null || elementList.size()==0) {
            Assert.fail("Tags are not loading, please check");
        }

        for (WebElement element : elementList) {
            String tag = element.getText();
            feeds.clickOnTag(driverToUse, element);
            System.out.println("clicked on tag for element:"+element);
            i_verify_for_all_occurrences_of_tag(tag);
            i_verify_for_title(tag);
        }
    }
}
