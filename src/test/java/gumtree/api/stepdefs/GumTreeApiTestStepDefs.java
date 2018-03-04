package gumtree.api.stepdefs;

import static gumtree.api.RestClient.response;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gumtree.api.RestClient;
import gumtree.api.utils.Utility;
import java.io.IOException;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class GumTreeApiTestStepDefs {


  public static int userId;
  private static String randomString;
  private static Logger LOG = LogManager.getLogger(GumTreeApiTestStepDefs.class);

  private static String emailDomain = "@gmail.com";
  private static String BASE_URL = "https://jsonplaceholder.typicode.com";

  Utility util = new Utility();
  RestClient restClient = new RestClient();

  @Given("^I create a random userId$")
  public void i_create_a_random_userId() throws IOException {
    userId = util.getRandomNumber();
    randomString = util.generateRandomString();

    String email = randomString + userId + emailDomain;
    LOG.info("Email of newly created user :" + email);
  }

  @Given("^I set the (\\w+) api URL$")
  public void i_have_the_jsonPlacelaceHolder(String httpMethod) throws IOException {
    if (httpMethod.equalsIgnoreCase("POST")) {
      restClient.setRequestURL(BASE_URL + "/posts");
    } else {
      restClient.setRequestURL(BASE_URL + "/posts/" + userId);
    }

    restClient.setContentType("application/json");
  }

  @When("^I make a (\\w+) request to api$")
  public void send_the_post_request(String httpMethod) {
    if (httpMethod.equalsIgnoreCase("GET")) {
      restClient.performGetRequest();
    } else if ((httpMethod.equalsIgnoreCase("POST"))) {
      restClient.performPostRequest();
    }
  }

  @Then("^status code should be (\\d+)$")
  public void api_should_return_status_code(int expectedStatusCode) {
    LOG.info(" \n Status Code: " + response.getStatusCode());
    assertThat(response.getStatusCode(), equalTo(expectedStatusCode));
  }

  @Then("^response should contain newly generated id$")
  public void response_should_have_new_id() {
    int idInResponse = Integer.parseInt(restClient.getValueFromJsonResponse("id"));
    Assert.assertTrue("Ids don't match", idInResponse == userId);
  }

  @Then("^id in the the response should be between (\\d+) and (\\d+)")
  public void response_should_have_new_id(int lowerLevel, int upperLevel) {
    int userIdInResponse = Integer.parseInt(restClient.getValueFromJsonResponse("userId"));
    Assert.assertTrue("Ids don't match", userIdInResponse >= lowerLevel
        && userIdInResponse <= upperLevel);
  }

  @When("^I add the body for POST call$")
  public void send_data() {
    restClient.setRequestBody(
        "{\"title\":\"TestTitle\",\"body\":\"TestBody\",\"userId\": " + this.userId + " }");
  }

  @Then("^response should contain below items:$")
  public void response_contains(DataTable dataTable) {
    for (Map<String, String> userData : dataTable.asMaps(String.class, String.class)) {
      Assert.assertTrue("Expected Value does not exist in response",
          Integer.parseInt(restClient.getValueFromJsonResponse("userId")) == userId);
      Assert.assertTrue("Expected Value does not exist in response",
          restClient.getValueFromJsonResponse("body").equalsIgnoreCase(userData.get("body")));
      Assert.assertTrue("Expected Value does not exist in response",
          restClient.getValueFromJsonResponse("title").equalsIgnoreCase(userData.get("title")));
    }

  }
}
