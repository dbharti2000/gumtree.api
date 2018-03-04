package gumtree.api.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import gumtree.api.RestClient;
import gumtree.api.utils.Utility;
import java.io.IOException;

public class GumTreeApiTestStepDefs {


  public static int userId;
  private static String randomString;
  private static String emailDomain = "@gmail.com";
  private static String BASE_URL = "https://jsonplaceholder.typicode.com";

  Utility util = new Utility();
  RestClient restClient = new RestClient();

  @Given("^I create a random userId$")
  public void i_create_a_random_userId() throws IOException {
    userId = util.getRandomNumber();
    randomString = util.generateRandomString();

    String email = randomString + userId + emailDomain;
    System.out.println("Email of newly created user :" + email);
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


}
