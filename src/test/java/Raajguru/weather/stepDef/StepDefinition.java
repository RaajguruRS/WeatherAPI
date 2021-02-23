package Raajguru.weather.stepDef;

import org.apache.http.HttpConnection;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONArray;
import org.json.JSONObject;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.JSONParser;
import gherkin.deps.com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseOptions;

public class StepDefinition {
	ResponseBody body;

	@SuppressWarnings("rawtypes")
	@When("^user performs \"([^\"]*)\" method on \"([^\"]*)\" successfully$")
	public void user_performs_GET(String methodName, String serviceName) throws Throwable {

		Response response = RestAssured
				.get("https://restcountries.eu/rest/v2/all?fields=name;capital;currencies;latlng");
		body = response.getBody();
		System.out.println(body.prettyPrint());

		int statusCode = response.getStatusCode();
		if (statusCode == 200) {
			System.out.println("Received response successfully with 200 OK ! ");
		} else {
			System.out.println("Response Failed...");
		}

	}

	@When("^user performs \"([^\"]*)\" action on the \"([^\"]*)\" weather forecast API successfully$")
	public void user_performs_action_on_the_weather_forecast_api_successfully(String service, String api)
			throws Throwable {
		Response response = null;
		if (api.equalsIgnoreCase("MercuryDip")) {
			response = RestAssured
					.get("https://api.openweathermap.org/data/2.5/weather?zip=94040,us&appid=3762478c2aa32cde1e77bd634821cb2b");

		} else if (api.equalsIgnoreCase("pushNotification")) {
			response = RestAssured.get(
					"https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&exclude=hourly,daily&appid=3762478c2aa32cde1e77bd634821cb2b");
		}

		body = response.getBody();

	}

	@Then("^user validates if the traveller is advised with \"([^\"]*)\" on weather before embarking on the trip$")
	public void user_validates_if_the_traveller_is_advised_with_on_weather_before_embarking_on_the_trip(
			String component) throws Throwable {

		System.out.println(body.prettyPrint());
		JSONObject obj = new JSONObject(body.asString());
		// JSONArray jsonArray = obj.getJSONArray("alerts[0].event");
		String[] names = obj.getNames("alerts[0].event");
		String notification = obj.getString("lat");
		JSONObject jsonObject = obj.getJSONObject("alerts[0]");
		String notification1 = jsonObject.getString("event");
		System.out.println(notification1);
		// System.out.println(jsonArray.toString());

	}

	@Then("^user validates \"([^\"]*)\" is \"([^\"]*)\" degree celcius leading to \"([^\"]*)\" weather condition$")
	public void user_validates_something_is_something_degree_celcius_leading_to_something_weather_condition(
			String component, String degree, String climate) throws Throwable {
		System.out.println(body.prettyPrint());
		if(component.equalsIgnoreCase("Mercury Dip")) {
			JSONObject obj = new JSONObject(body.asString());
		JSONObject jsonObject = obj.getJSONObject("weather");
		
			String weather = obj.getString("base");
			System.out.println(weather);
		}
	}

}
