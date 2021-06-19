package conduit.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Teardown {

    // Executes after every scenario for filtering by tag
    @After("@filterbytag")
    public void afterFilterByTag(Scenario scenario) {
        System.out.println("scenario "+ scenario.getName() + " ended");
        if (Setup.driver!=null) {
            Setup.driver.quit();
        }
    }
}
