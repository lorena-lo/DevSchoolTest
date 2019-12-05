package com.ing.tech.devschool.testing.tmp;

import com.ing.tech.devschool.testing.api.model.Account;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import lombok.AllArgsConstructor;
import org.junit.After;
import org.junit.Assert;

import static com.ing.tech.devschool.testing.tmp.TestConstants.ACCOUNT;
import static com.ing.tech.devschool.testing.tmp.TestConstants.CREATE_ACCOUNT_STATUS_CODE;
import static io.restassured.RestAssured.given;

@CucumberSteps
@AllArgsConstructor
public class GenericSteps {

    private TestCommon testCommon;

    @After
    public void cleanUp() {
        testCommon.clean();
    }

    @When("^an account has been created for (\\w+) with balance (\\d+.\\d+)$")
    public void createAccountForName(String name, double totalBalance) {
        ExtractableResponse extractableResponse = given().accept(ContentType.JSON).contentType(ContentType.JSON)
            .body(name).when().post("http://localhost:8081/accounts/add")
            .then().extract();
        Account account = extractableResponse.response().body().as(Account.class);
        account.setTotalBalance(totalBalance);

        int statusCode = extractableResponse.statusCode();
        testCommon.put(CREATE_ACCOUNT_STATUS_CODE, statusCode);
        testCommon.put(ACCOUNT, account);

        given().accept(ContentType.JSON).contentType(ContentType.JSON)
            .body(name).when()
            .put("http://localhost:8081/transactions/atm/" + account.getAccountNumber() + '/' + totalBalance);
    }

    @Then("^status code (\\d+) is received$")
    public void statusCodeIsReceived(int statusCode) {
        Assert.assertEquals(statusCode, testCommon.get(CREATE_ACCOUNT_STATUS_CODE));
    }

    @And("^the current balance for (\\w+) is (\\d+.\\d+)$")
    public void getBalanceForAccount(String clientName, double amount) {
        Account account = given().get("http://localhost:8081/accounts/name/" + clientName)
            .then().extract().response().body().as(Account.class);

        Assert.assertNotNull(account);
        Assert.assertEquals(clientName, account.getClientName());
        Assert.assertEquals(amount, account.getTotalBalance(), 0.01);
    }

}
