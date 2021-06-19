package conduit.steps;

import config.TestConfig;
import io.cucumber.java.en.Given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static io.restassured.RestAssured.get;

public class ApiSteps {

    @Given("^I call api to verify filtering of \"([^\"]*)\" tag that is \"([^\"]*)\" with \"([^\"]*)\" limit$")
    public void getApiResponse (String tag, String validityCheck, String limit) throws Exception {
        String url = TestConfig.getResource("apiurl");

        // Setting API URL based on limit of a page or all entries
        if (limit.equals("no")) {
            url=url.concat("?tag="+tag);
        } else {
            url=url.concat("?limit="+limit+"&offset=0&tag="+tag);
        }

        System.out.println("API to be called:"+url);
        Response response = get(url);

        // Parsing response to verify that tags are present in each entry
        System.out.println(response.prettyPrint());
        JsonPath jsonPath = new JsonPath(response.asString());
        ArrayList articles = jsonPath.get("articles");
        int articleCount = jsonPath.get("articlesCount");
        System.out.println("article count:"+articleCount);

        // For calling API with invalid tag, check that there shouldn't be any entry
        if (validityCheck.equalsIgnoreCase("invalid")) {
            if (articleCount!=0 || articles.size()!=0) {
                Assert.fail(articleCount + " articles obtained for invalid tag:\n"+articles);
            }
        } else {
            if (articleCount==0 || articles.size()==0) {
                Assert.fail(articleCount + " articles obtained for valid tag:\n"+articles);
            }
        }

        // Looping through all entries in response to verify
        for (Object object : articles) {
            LinkedHashMap map = (LinkedHashMap) object;
            Object tagObj = map.get("tagList");
            ArrayList tagList = (ArrayList) tagObj;

            if (tagList.contains(tag)) {
                System.out.println(tag + " tag is present in list:"+tagList);
            } else {
                Assert.fail(tag + " tag is not present in list:"+tagList);
            }
        }
    }
}
