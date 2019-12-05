package com.ing.tech.devschool.testing;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
    plugin = {"pretty", "html:target/html", "json:target/cucumber.json"})
public class CucumberTest {
}
