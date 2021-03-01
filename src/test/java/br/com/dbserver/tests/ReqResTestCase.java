package br.com.dbserver.tests;

import br.com.dbserver.Rest;
import br.com.dbserver.datafactory.UserDataFactory;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ExtentITestListenerClassAdapter.class})
public class ReqResTestCase {
    @Test
    public void validSingleUserStatus200AndSchema() {
        Rest rest = new Rest("https://reqres.in");
        rest.get("/api/users/2", HttpStatus.SC_OK)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/SingleUser.json"))
                .body("data.id", Matchers.is(2))
                .body("data.email", Matchers.is("janet.weaver@reqres.in"))
                .body("data.first_name", Matchers.is("Janet"))
                .body("data.last_name", Matchers.is("Weaver"))
                .body("data.avatar", Matchers.is("https://reqres.in/img/faces/2-image.jpg"));
    }

    @Test
    public void validSingleUserStatus201() {
        Rest rest = new Rest("https://reqres.in");
        rest.post("/api/users", HttpStatus.SC_CREATED, UserDataFactory.create().getClass());
    }
}
