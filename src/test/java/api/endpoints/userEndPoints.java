package api.endpoints;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class userEndPoints {

	public static Response createUser(user Payload) {
		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(Payload).when()
				.post(Routes.postUrl);

		return response;

	}

	public static Response getUser(String userName) {
		Response response = given().accept(ContentType.JSON).pathParam("username", userName)

				.when().get(Routes.get_url);

		return response;
	}

	public static Response updateUser(String userName, user payload) {
		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
				.pathParam("username", userName).body(payload)

				.when().put(Routes.put_url);

		return response;
	}

	public static Response deleteUser(String userName) {
		Response response = given().accept(ContentType.JSON).pathParam("username", userName).when()
				.delete(Routes.del_url);
		return response;

	}
}
