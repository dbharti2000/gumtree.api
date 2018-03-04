package gumtree.api;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class RestClient {


  static Response response;
  private static String requestURL = "";
  private static Map<String, String> requestHeaders = new HashMap<String, String>();
  private static String requestBody = "";
  private static String responseString = "";

  public void setRequestURL(String url) {
    requestURL = url;
  }

  public void setRequestBody(String body) {

    requestBody = body;
  }

  public void performGetRequest() {
    response = given().log().all().headers(requestHeaders)
        .expect().get(requestURL);

    responseString = response.asString();

  }

  public String getResponseAsString() {
    return responseString;
  }

  public void setRequestHeader(String key, String value) {
    requestHeaders.put(key, value);
  }

  public void setContentType(String contentType) {
    requestHeaders.put("Accept", contentType);
    requestHeaders.put("Content-Type", contentType);

  }

  public void performPostRequest() {
    response = given().log().all().headers(requestHeaders).body(requestBody)
        .expect().post(requestURL);

    responseString = response.asString();

  }

}
