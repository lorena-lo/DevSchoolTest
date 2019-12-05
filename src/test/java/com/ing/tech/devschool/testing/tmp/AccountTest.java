package com.ing.tech.devschool.testing.tmp;

import com.ing.tech.devschool.testing.api.model.Account;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import lombok.AllArgsConstructor;
import org.junit.After;
import org.junit.Assert;

import static com.ing.tech.devschool.testing.tmp.TestConstants.ACCOUNT;
import static io.restassured.RestAssured.given;

@CucumberSteps
@AllArgsConstructor
public class AccountTest {

    private TestCommon testCommon;

    @After
    public void cleanUp() {
        testCommon.clean();
    }

    @Given("^an account for name (\\w+) does not exist")
    public void findAccountForName(String name) {
        ExtractableResponse extractableResponse = given().accept(ContentType.JSON).contentType(ContentType.JSON)
            .when().get("http://localhost:8081/accounts/name/" + name)
            .then().extract();
        int statusCode = extractableResponse.statusCode();

        if (statusCode != 200) {
            Assert.assertEquals(400, statusCode);
            return;
        }
        Account account = extractableResponse.response().body().as(Account.class);
        given().accept(ContentType.JSON).contentType(ContentType.JSON)
            .when().delete("http://localhost:8081/accounts/" + account.getAccountNumber())
            .then().extract();
    }

    @And("^the account should be created for (\\w+)$$")
    public void theAccountShouldBeCreatedForName(String name) {
        Account account = (Account) testCommon.get(ACCOUNT);

        Assert.assertNotNull(account);
        Assert.assertEquals(name, account.getClientName());
    }

}
