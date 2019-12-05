package com.ing.tech.devschool.testing.tmp;

import com.ing.tech.devschool.testing.api.model.Account;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import lombok.AllArgsConstructor;

import static io.restassured.RestAssured.given;

@CucumberSteps
@AllArgsConstructor
public class TransactionTest {

    @When("^(\\w+) wants to transfer to (\\w+) the amount of (\\d+.\\d+)$")
    public void transferMoneyBetweenTwoAccounts(String senderName, String receiverName, double amount) {
        Account senderAccount = given().get("http://localhost:8081/accounts/name/" + senderName)
            .then().extract().response().body().as(Account.class);
        Account receiverAccount = given().get("http://localhost:8081/accounts/name/" + receiverName)
            .then().extract().response().body().as(Account.class);

        given().accept(ContentType.JSON).contentType(ContentType.JSON)
            .when().put("http://localhost:8081/transactions/" + senderAccount.getAccountNumber() + '/' +
            receiverAccount.getAccountNumber() + '/' + amount);
    }

}
